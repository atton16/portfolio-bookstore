package com.sixppl.dao.support;

import java.util.ArrayList;

import java.sql.*;
import com.sixppl.dao.CartDAO;
import com.sixppl.dto.ListingDTO;
import com.sixppl.main.Application;

public class CartDAOImpl implements CartDAO {

	private Connection conn;
	public CartDAOImpl(){
		conn = Application.getSharedInstance().getDAOSupport().getConnection();
	}
	
	public ArrayList<ListingDTO> viewCart(int userID){
		ArrayList<ListingDTO> cartInfoList = new ArrayList<ListingDTO>();
		PreparedStatement stmt = null;
		String sql = "select * from Listing where PubID in (select PubID from Cart where RemoveTime IS NULL AND UserID = ?;";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userID);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				ListingDTO pub = new ListingDTO();
				pub.setAttributes(rs.getInt("PubID"), rs.getString("Title"), rs.getString("Authors"), rs.getString("Editors"), rs.getString("Type"), 
						rs.getInt("Year"), rs.getString("Venue"), rs.getInt("SellerID"), rs.getString("Picture"), rs.getInt("Price"), rs.getBoolean("Status"), 
						rs.getInt("SoldCount"), rs.getLong("timestamp"));
				
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
		String sql = "INSERT INTO `Cart` (`UserID`,`PubID`) VALUES (?,?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, userID);
			stmt.setInt(2, pubID);
			stmt.executeUpdate();
			
			sql = "SELECT count(*) as Count from Cart;";
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()){
				cartCount = rs.getInt("Count");
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
			long removeTime = System.currentTimeMillis();
			for(int pubID:pubIDs){
				String sql = "UPDATE Cart SET RemoveTime = ? WHERE UserID = ? and PubID = ?;";
				stmt = conn.prepareStatement(sql);
				stmt.setLong(1, removeTime);
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
}
