package com.sixppl.dao.support;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.sixppl.dao.AdminUserDAO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;
public class AdminUserDAOImpl implements AdminUserDAO{

	public List<UserDTO> findByNickname(String nickname){
		Connection con = null;
		List<UserDTO> userlist=new ArrayList<UserDTO>();
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM User WHERE Nickname LIKE ?");
			stmt.setString(1, "%"+nickname+"%");
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
			}


		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		} 
		return userlist;
	}

	public List<UserDTO> findByFirstname(String firstname){
		Connection con = null;
		List<UserDTO> userlist=new ArrayList<UserDTO>();
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM User WHERE Firstname LIKE ?");
			stmt.setString(1, "%"+firstname+"%");
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
			}


		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		} 
		return userlist;
	}

	public List<UserDTO>findByLastname(String lastname){
		Connection con = null;
		List<UserDTO> userlist=new ArrayList<UserDTO>();

		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM User WHERE Lastname LIKE ?");
			stmt.setString(1, "%"+lastname+"%");
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
			}


		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		} 
		return userlist;
	}

	public List<UserDTO> findByEmail(String email){
		Connection con = null;
		List<UserDTO> userlist=new ArrayList<UserDTO>();

		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM User WHERE Email LIKE ?");
			stmt.setString(1, "%"+email+"%");
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
			}


		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		} finally {

		}
		return userlist;
	}

	public List<UserDTO>findAllCustomers(){
		Connection con = null;
		List<UserDTO> userlist=new ArrayList<UserDTO>();
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM User WHERE EXISTS (SELECT BuyerID FROM Transaction WHERE User.UserID = Transaction.BuyerID)");
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
			}


		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		} 
		return userlist;
	}

	public List<UserDTO>findAllSellers(){
		Connection con = null;
		List<UserDTO> userlist=new ArrayList<UserDTO>();
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT * FROM User WHERE EXISTS (SELECT SellerID FROM Listing WHERE User.UserID = Listing.SellerID)");
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
			}


		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		} 
		return userlist;
	}

	@Override
	public Boolean isCustomer(Integer userId) {
		Integer total = 0;
		Connection con = null;
		try {
			con = Application.getSharedInstance().getDAOSupport().getConnection();

			PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) AS Total FROM Transaction WHERE BuyerID = ?");
			stmt.setInt(1, userId);
			ResultSet rs= stmt.executeQuery();
			while(rs.next()){
				total = rs.getInt("Total");
			}


		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
		} 
		return total > 0 ? true : false;
	}
}
