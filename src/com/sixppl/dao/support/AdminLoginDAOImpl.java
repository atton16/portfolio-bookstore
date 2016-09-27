package com.sixppl.dao.support;

import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sixppl.dao.*;
import com.sixppl.dto.AdminLoginDTO;
import com.sixppl.main.Application;

public class AdminLoginDAOImpl implements AdminLoginDAO{

	/*@Override
	public AdminLoginDTO findAdmin(Integer userID) {
		Connection con = null;
		AdminLoginDTO temp = new AdminLoginDTO();
		try {
		     con = Application.getSharedInstance().getDAOSupport().getConnection();

			 PreparedStatement stmt = con.prepareStatement("SELECT * FROM Admin WHERE UserID=?");
		     stmt.setInt(1, userID);
		     ResultSet rs=stmt.executeQuery();
		     rs.next();
		     Integer UserID = rs.getInt("UserID");
		     Integer Level = rs.getInt("Level");
		     Timestamp Timestamp = rs.getTimestamp("Timestamp");
		   } 
		catch (SQLException se) {
			    se.printStackTrace();
		   } 
		catch (Exception e) {
		   } 


		return temp;
	}*/

	@Override
	public AdminLoginDTO getAdmin(Integer userID) {
		Connection con = null;
		AdminLoginDTO temp = null;
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Admin WHERE UserID=?");
			stmt.setInt(1, userID);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				Integer UserID = rs.getInt("UserID");
				Integer Level = rs.getInt("Level");
				Timestamp Timestamp = rs.getTimestamp("Timestamp");
				temp=new AdminLoginDTO();
				temp.setUserID(UserID);
				temp.setLevel(Level);
				temp.setTimestamp(Timestamp);
			}
		} 
		catch (SQLException se) {
			se.printStackTrace();
		} 
		catch (Exception e) {
		} 
		return temp;
	}

	@Override
	public boolean isLoggedIn(Integer userID) {
		Connection con = null;
		boolean status = false;
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT UserID FROM AdminLoginSessions WHERE UserID=?");
			stmt.setInt(1,userID);
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

	@Override
	public boolean login(Integer UserID, String SessionID) {
		Connection con = null;
		boolean status = false;
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement stmt = con.prepareStatement("INSERT INTO AdminLoginSessions (ID, timestamp, UserID, JSESSIONID) VALUE(NULL,?,?,? )");
			java.sql.Timestamp  banTime = new java.sql.Timestamp(new java.util.Date().getTime());
			stmt.setTimestamp(1, banTime);
			stmt.setInt(2, UserID);
			stmt.setString(3, SessionID);;

			stmt.execute();
			status=true;
		} 
		catch (SQLException se) {
			se.printStackTrace();
		} 
		catch (Exception e) {
		} 
		return status;
	}

	@Override
	public boolean logout(String SessionID) {
		Connection con = null;
		boolean status = false;
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement stmt = con.prepareStatement("DELETE FROM AdminLoginSessions WHERE JSESSIONID=?");
			stmt.setString(1, SessionID);;

			stmt.execute();
			status=true;
		} 
		catch (SQLException se) {
			se.printStackTrace();
		} 
		catch (Exception e) {
		} 
		return status;
	}

	@Override
	public boolean isAdmin(Integer userID) {
		Connection con = null;
		AdminLoginDTO temp = null;
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM Admin WHERE UserID=?");
			stmt.setInt(1, userID);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()){
				Integer UserID = rs.getInt("UserID");
				Integer Level = rs.getInt("Level");
				Timestamp Timestamp = rs.getTimestamp("Timestamp");
				temp=new AdminLoginDTO();
				temp.setUserID(UserID);
				temp.setLevel(Level);
				temp.setTimestamp(Timestamp);
			}
		} 
		catch (SQLException se) {
			se.printStackTrace();
		} 
		catch (Exception e) {
		} 
		
		if(temp != null)
			return true;
		return false;
	}

}
