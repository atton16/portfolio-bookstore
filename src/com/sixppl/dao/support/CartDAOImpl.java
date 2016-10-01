package com.sixppl.dao.support;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import com.sixppl.dao.CartDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.dto.CartDTO;
import com.sixppl.dto.ListingDTO;
import com.sixppl.main.Application;

public class CartDAOImpl implements CartDAO {

	private Connection conn;
	private UserDAO userDao;

	public CartDAOImpl(){
		conn = Application.getSharedInstance().getDAOSupport().getConnection();
		userDao = new UserDAOImpl();
	}
	
	public ArrayList<ListingDTO> viewCart(int userID){
		ArrayList<ListingDTO> cartInfoList = new ArrayList<ListingDTO>();
		PreparedStatement stmt = null;
		String sql = "select * from Listing where PubID in (select PubID from Cart where RemoveTime IS NULL AND UserID = ?);";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				ListingDTO pub = new ListingDTO();
				pub.setAttributes(rs.getInt("PubID"), rs.getString("Title"), rs.getString("Authors"), rs.getString("Editors"), rs.getString("Type"), 
						rs.getInt("Year"), rs.getString("Venue"), rs.getInt("SellerID"), rs.getString("Picture"), rs.getInt("Price"), rs.getBoolean("Status"), 
						rs.getInt("SoldCount"), rs.getTimestamp("timestamp"));
				pub.setSellerNickname(userDao.findUserByUserID(rs.getInt("SellerID")).getNickname());
				cartInfoList.add(pub);
			}
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try
		return cartInfoList;
	}
	
	public int addCart(int pubID,int userID){
		PreparedStatement stmt = null;
		int cartCount = 0;
		String sql = "DELETE FROM `Cart` WHERE `UserID` = ? AND `PubID` = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, userID);
			stmt.setLong(2, pubID);
			try{
				stmt.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
			stmt.close();
 
			sql = "INSERT INTO `Cart` (`UserID`,`PubID`) VALUES (?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, userID);
			stmt.setLong(2, pubID);
			stmt.executeUpdate();
			System.out.println("Insert userID :" + userID+", PubID :" + pubID);



			
			sql = "SELECT COUNT(*) AS Total FROM Cart WHERE UserID = ? AND RemoveTime IS NULL";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, userID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				cartCount = rs.getInt("Total");
				System.out.println("cartCount :" + cartCount);
			}
			//STEP 6: Clean-up environment
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try
		return cartCount;
	}
	public void removeCart(int userID,ArrayList<Integer> pubIDs){
		PreparedStatement stmt = null;
		try {
			for(int pubID:pubIDs){
				String sql = "UPDATE Cart SET RemoveTime = ? WHERE UserID = ? and PubID = ?;";
				stmt = conn.prepareStatement(sql);
				stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
				stmt.setInt(2, userID);
				stmt.setInt(3, pubID);
				stmt.executeUpdate();
			}
			
			//STEP 6: Clean-up environment
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try
	}

	@Override
	public Boolean isInCart(int pubID, int userID) {
		PreparedStatement stmt = null;
		int cartCount = 0;
		String sql = "SELECT COUNT(*) AS Total FROM Cart WHERE PubID = ? AND UserID = ? AND RemoveTime IS NULL";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, pubID);
			stmt.setLong(2, userID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				cartCount = rs.getInt("Total");
			}
			//STEP 6: Clean-up environment
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try
		return (cartCount > 0) ? true : false;
	}

	@Override
	public void removeAll(int userID) {
		//Get OrderNumber
		PreparedStatement stmt = null;
		long orderNumber = -1;
		String sql = "SELECT Value FROM Variable WHERE Name = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "OrderNumber");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				orderNumber = rs.getInt("Value");
				orderNumber++;
			}
			//STEP 6: Clean-up environment
			stmt.close();
			// increase OrderNumber
			sql = "UPDATE Variable SET Value = ? WHERE Name = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, orderNumber);
			stmt.setString(2, "OrderNumber");
			stmt.executeUpdate();
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
		}//end try
		
		//Add Checkout items to transaction
		ArrayList<ListingDTO> checkoutItems = viewCart(userID);
		for(ListingDTO item: checkoutItems){
			sql = "INSERT INTO `Transaction` (`BuyerID`,`SellerID`,`PubID`,`OrderNumber`,`SellingPrice`) VALUES (?,?,?,?,?)";
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, userID);
				stmt.setLong(2, item.sellerID);
				stmt.setLong(3, item.pubID);
				stmt.setLong(4, orderNumber);
				stmt.setLong(5, item.price);
				stmt.executeUpdate();
				stmt.close();
				
				//Add Sold Count
				sql = "UPDATE Listing SET SoldCount = SoldCount + 1 WHERE PubID = ?";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, item.pubID);
				stmt.executeUpdate();
				stmt.close();
				
				//Remove item from cart
				sql = "DELETE FROM Cart WHERE UserID = ? and PubID = ?;";
				stmt = conn.prepareStatement(sql);
				stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
				stmt.setInt(2, userID);
				stmt.setInt(3, item.pubID);
				stmt.executeUpdate();
				
				//STEP 6: Clean-up environment
				stmt.close();
			}catch(SQLException se){
				//Handle errors for JDBC
				se.printStackTrace();
			}catch(Exception e){
				//Handle errors for Class.forName
				e.printStackTrace();
			}finally{
				//finally block used to close resources
				try{
					if(stmt!=null)
						stmt.close();
				}catch(SQLException se2){
				}// nothing we can do
			}//end try
		}
	}

	@Override
	public List<CartDTO> getByUserID(int userID) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<CartDTO> cart = new ArrayList<CartDTO>();
		String sql = "SELECT * FROM `Cart` WHERE `UserID` = ?";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userID);
			rs = stmt.executeQuery();
			while(rs.next()){
				CartDTO item = new CartDTO();
				item.setAttributes(
						rs.getInt("PubID"),
						rs.getInt("UserID"),
						rs.getTimestamp("AddTime"),
						rs.getTimestamp("RemoveTime")
						);
				cart.add(item);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		try {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		} catch (Exception E) {}

		return cart;
	}
}
