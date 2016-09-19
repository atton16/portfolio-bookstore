package com.sixppl.dao.support;

import java.util.ArrayList;

import java.sql.*;
import com.sixppl.dao.CartDAO;
import com.sixppl.dto.ListingDTO;
import com.sixppl.main.Application;

public class CartDAOImpl implements CartDAO {

//	private ArrayList<ListingDTO> cartInfoList;
//	private ArrayList<CartDTO> cartList;
	
	public ArrayList<ListingDTO> viewCart(int userID){
		ArrayList<ListingDTO> cartInfoList = new ArrayList<ListingDTO>();
		Connection conn = null;
		Statement stmt = null;
		String sql = "select * from Listing where PubID in (select PubID from Cart where UserID = "+ userID + ");";
		try {
			conn = Application.getSharedInstance().getDAOSupport().getConnection();
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
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
			conn.close();
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
			if(conn!=null)
				Application.getSharedInstance().getDAOSupport().destroy();	//This statement is normally executed by servlet
		}//end try
		return cartInfoList;
	}
}
