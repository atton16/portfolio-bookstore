package com.sixppl.dao.support;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.json.simple.JSONObject;

import com.sixppl.dao.AdminUserBanDAO;
import com.sixppl.main.Application;

public class AdminUserBanDAOImpl implements AdminUserBanDAO{
	
	public boolean isBanned(Integer userID){
		Connection con = null;
		Boolean status = false;
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();
 
			PreparedStatement stmt = con.prepareStatement(
		       "SELECT * FROM Ban WHERE UserID=?");
		     stmt.setInt(1, userID);
		     ResultSet rs=stmt.executeQuery();
		     if(rs.next()){
		    	 status=true;
		     }
		   } 
		catch (SQLException se) {
			    se.printStackTrace();
		   } 
		catch (Exception e) {
		   } 
		
		
		return status;
	}
	
	public boolean userBan(Integer userID){
		boolean status = false;
		Connection con = null;
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();
		     java.sql.Timestamp  banTime = new java.sql.Timestamp(new java.util.Date().getTime());
			PreparedStatement stmt = con.prepareStatement("INSERT INTO Ban (UserID,timestamp) VALUES (?,?)");
		     stmt.setInt(1, userID);
		     stmt.setTimestamp(2, banTime);
		     stmt.execute();
		     status = true;
		  
		   } catch (SQLException se) {
			    se.printStackTrace();
		   } catch (Exception e) {
		   } 
		
		
		return status;
	}
	
	public boolean userUnban(Integer userID){
		boolean status = false;
		Connection con = null;
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM Ban WHERE UserID=?");
		     stmt.setInt(1, userID);
		     stmt.execute();
		     status = true;
		  
		   } catch (SQLException se) {
			    se.printStackTrace();
		   } catch (Exception e) {
		   } 
		
		
		return status;
	}
}
