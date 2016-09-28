package com.sixppl.dao.support;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.sixppl.dao.AdminUserDAO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;
public class AdminUserDAOImpl implements AdminUserDAO{

	public List<UserDTO> findByNickname(String nickname, int offset, int limit){
		Connection con = null;
		List<UserDTO> userlist=new ArrayList<UserDTO>();
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT UserID, Nickname, Firstname, Lastname, Email FROM User WHERE Nickname=? LIMIT ?,?");
			stmt.setString(1, nickname);
			stmt.setInt(2, offset);
			stmt.setInt(3, limit);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				UserDTO temp = new UserDTO();
				Integer UserID = rs.getInt("UserID");
				String Nickname=rs.getString("Nickname");
				String Firstname = rs.getString("Firstname");
				String Lastname = rs.getString("Lastname");
				String Email = rs.getString("Email");
				temp.setUserID(UserID);
				temp.setNickname(Nickname);
				temp.setFirstname(Firstname);
				temp.setLastname(Lastname);
				temp.setEmail(Email);
				userlist.add(temp);
				//mainarray.add(temp);
			}


		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		} 
		return userlist;
	}

	public List<UserDTO> findByFirstname(String firstname, int offset, int limit){
		Connection con = null;
		List<UserDTO> userlist=new ArrayList<UserDTO>();
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT UserID, Nickname, Firstname, Lastname, Email FROM User WHERE Firstname=? LIMIT ?,?");
			stmt.setString(1, firstname);
			stmt.setInt(2, offset);
			stmt.setInt(3, limit);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				UserDTO temp = new UserDTO();
				Integer UserID = rs.getInt("UserID");
				String Nickname=rs.getString("Nickname");
				String Firstname = rs.getString("Firstname");
				String Lastname = rs.getString("Lastname");
				String Email = rs.getString("Email");
				temp.setUserID(UserID);
				temp.setNickname(Nickname);
				temp.setFirstname(Firstname);
				temp.setLastname(Lastname);
				temp.setEmail(Email);
				userlist.add(temp);
				//mainarray.add(temp);
			}


		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		} 
		return userlist;
	}

	public List<UserDTO>findByLastname(String lastname, int offset, int limit){
		Connection con = null;
		List<UserDTO> userlist=new ArrayList<UserDTO>();

		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT UserID, Nickname, Firstname, Lastname, Email FROM User WHERE Lastname=? LIMIT ?,?");
			stmt.setString(1, lastname);
			stmt.setInt(2, offset);
			stmt.setInt(3, limit);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				UserDTO temp = new UserDTO();
				Integer UserID = rs.getInt("UserID");
				String Nickname=rs.getString("Nickname");
				String Firstname = rs.getString("Firstname");
				String Lastname = rs.getString("Lastname");
				String Email = rs.getString("Email");
				temp.setUserID(UserID);
				temp.setNickname(Nickname);
				temp.setFirstname(Firstname);
				temp.setLastname(Lastname);
				temp.setEmail(Email);
				userlist.add(temp);
				//mainarray.add(temp);
			}


		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		} 
		return userlist;
	}

	public List<UserDTO> findByEmail(String email, int offset, int limit){
		Connection con = null;
		List<UserDTO> userlist=new ArrayList<UserDTO>();

		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT UserID, Nickname, Firstname, Lastname, Email FROM User WHERE Email=? LIMIT ?,?");
			stmt.setString(1, email);
			stmt.setInt(2, offset);
			stmt.setInt(3, limit);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				UserDTO temp = new UserDTO();
				Integer UserID = rs.getInt("UserID");
				String Nickname=rs.getString("Nickname");
				String Firstname = rs.getString("Firstname");
				String Lastname = rs.getString("Lastname");
				String Email = rs.getString("Email");
				temp.setUserID(UserID);
				temp.setNickname(Nickname);
				temp.setFirstname(Firstname);
				temp.setLastname(Lastname);
				temp.setEmail(Email);
				userlist.add(temp);
				//mainarray.add(temp);
			}


		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		} finally {

		}
		return userlist;
	}

	public List<UserDTO>findAllCustomers(int offset, int limit){
		Connection con = null;
		List<UserDTO> userlist=new ArrayList<UserDTO>();
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT UserID, Nickname, Firstname, Lastname, Email FROM User LIMIT ?,?");
			stmt.setInt(1, offset);
			stmt.setInt(2, limit);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				UserDTO temp = new UserDTO();
				Integer UserID = rs.getInt("UserID");
				String Nickname=rs.getString("Nickname");
				String Firstname = rs.getString("Firstname");
				String Lastname = rs.getString("Lastname");
				String Email = rs.getString("Email");
				temp.setUserID(UserID);
				temp.setNickname(Nickname);
				temp.setFirstname(Firstname);
				temp.setLastname(Lastname);
				temp.setEmail(Email);
				userlist.add(temp);
				//mainarray.add(temp);
			}


		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		} 
		return userlist;
	}

	public List<UserDTO>findAllSellers(int offset, int limit){
		Connection con = null;
		List<UserDTO> userlist=new ArrayList<UserDTO>();
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT UserID, Nickname, Firstname, Lastname, Email FROM User LIMIT ?,?");
			stmt.setInt(1, offset);
			stmt.setInt(2, limit);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				UserDTO temp = new UserDTO();
				Integer UserID = rs.getInt("UserID");
				String Nickname=rs.getString("Nickname");
				String Firstname = rs.getString("Firstname");
				String Lastname = rs.getString("Lastname");
				String Email = rs.getString("Email");
				temp.setUserID(UserID);
				temp.setNickname(Nickname);
				temp.setFirstname(Firstname);
				temp.setLastname(Lastname);
				temp.setEmail(Email);
				userlist.add(temp);
				//mainarray.add(temp);
			}


		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		} 
		return userlist;
	}
}
