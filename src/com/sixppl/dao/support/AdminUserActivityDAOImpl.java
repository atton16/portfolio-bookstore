package com.sixppl.dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import com.sixppl.dto.*;
import com.sixppl.dao.AdminUserActivityDAO;
import com.sixppl.main.Application;

public class AdminUserActivityDAOImpl implements AdminUserActivityDAO{
	public UserDTO getUserDetails(Integer userID){
		UserDTO temp = new UserDTO();
		Connection con = null;
		
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();
  
			PreparedStatement stmt = con.prepareStatement(
		       "SELECT * FROM Listing WHERE UserID=?");
		     stmt.setInt(1, userID);
		     ResultSet rs= stmt.executeQuery();
		     while(rs.next()){
		    	 String UserID=rs.getString("UserID");
		    	 String Username = rs.getString("Username");
		    	 String Nickname = rs.getString("Nickname");
		    	 String Firstname = rs.getString("Firstname");
		    	 String Lastname = rs.getString("Lastname");
		    	 String Email = rs.getString("Email");
		    	 String Birthyear = Integer.toString(rs.getInt("Birthyear"));
		    	 String Address = rs.getString("Address");
		    	 String Cardnumber = Integer.toString(rs.getInt("Cardnumber"));
		    	 temp.setUserID(UserID);
		    	 temp.setUsername(Username);
		    	 temp.setNickname(Nickname);
		    	 temp.setFirstname(Firstname);
		    	 temp.setLastname(Lastname);
		    	 temp.setEmail(Email);
		    	 temp.setBirthyear(Birthyear);
		    	 temp.setAddr(Address);
		    	 temp.setCardno(Cardnumber);
		     }
		     
			
		   } catch (SQLException se) {
			    se.printStackTrace();
		   } catch (Exception e) {
		   } 
		return temp;
	}
	
	public List<CustomerActivityDTO> getBuyingHistory(Integer userID){
		List<CustomerActivityDTO> bhlist = new ArrayList<CustomerActivityDTO>();
		Connection con = null;
		
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();
  
		     PreparedStatement stmt = con.prepareStatement("SELECT M.Title, M.Timestamp, M.Price ,U.Nickname FROM(SELECT T.PubID as 'pID', T.SellerID as 'sID', T.SellingPrice as 'Price', L.Title as 'Title', T.PurchaseTime as 'Timestamp' FROM Transaction T INNER JOIN Listing L ON T.PubID=L.PubID) M INNER JOIN User U ON U.UserID=sID WHERE U.UserID=?");
		     stmt.setInt(1, userID);
		     ResultSet rs= stmt.executeQuery();
		     while(rs.next()){
		    	 CustomerActivityDTO ca = new CustomerActivityDTO();
		    	 String Title = rs.getString("Title");
		    	 String Timestamp = rs.getString("Timestamp");
		    	 String Nickname = rs.getString("Nickname");
		    	 Integer Price = rs.getInt("Price");
		    	 ca.setTitle(Title);
		    	 ca.setTimestamp(Timestamp);
		    	 ca.setNickname(Nickname);
		    	 ca.setPrice(Price);
		    	 bhlist.add(ca);
		     }
		     
			
		   } catch (SQLException se) {
			    se.printStackTrace();
		   } catch (Exception e) {
		   } 
		
		return bhlist;
	}

	@Override
	public List<AdminCartDTO> getCartHistory(Integer UserID) {
		List<AdminCartDTO> chlist = new ArrayList<AdminCartDTO>();
		Connection con = null;
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();
 
		     PreparedStatement stmt = con.prepareStatement("SELECT L.Title, C.UserID as 'UserID', C.AddTime as 'AddTimestamp', C.RemoveTime as 'RemoveTimestamp' FROM Cart C INNER JOIN Listing L ON C.PubID=L.PubID WHERE UserID=? AND RemoveTime<>''");
		     stmt.setInt(1, UserID);
		     ResultSet rs= stmt.executeQuery();
		     while(rs.next()){
		    	 AdminCartDTO ca = new AdminCartDTO();
		    	 String Title = rs.getString("Title");
		    	 int userID = rs.getInt("UserID");
		    	 long addtimestamp = rs.getLong("AddTimestamp");
		    	 long removetimestamp = rs.getLong("RemoveTimestamp");
		    	 ca.setTitle(Title);
		    	 ca.setUserID(userID);
		    	 ca.setAddTime(addtimestamp);
		    	 ca.setRemoveTime(removetimestamp);
		    	 chlist.add(ca);
		     }
		     
			
		   } catch (SQLException se) {
			    se.printStackTrace();
		   } catch (Exception e) {
		   } 
		
		return chlist;
	}
}
