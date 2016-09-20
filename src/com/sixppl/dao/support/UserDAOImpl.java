package com.sixppl.dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.sixppl.bean.UserEntity;
import com.sixppl.dao.UserDAO;
import com.sixppl.dto.ListingDTO;
import com.sixppl.main.Application;


public class UserDAOImpl implements UserDAO{
	static Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public UserDAOImpl() {
		connection = Application.getSharedInstance().getDAOSupport().getConnection();
		logger.info("Got connection");
	}
	

	@Override
	public Boolean addUser(UserEntity user) {

		String sql = "insert into User (Username, Password, Nickname, Firstname, Lastname,Email"
				+ "Birthyear,Address, CardNumber) values ('"
				+ user.getUsername()
				+ "','"
				+ user.getPassword()
				+ "','"
				+ user.getNickname()
				+ "', '"
				+ user.getFirstname()
				+ "', '"
				+ user.getLastname()
				+ "', '"
				+ user.getEmail()
				+ "', '"
				+ user.getBirthyear()
				+ "', '"
				+ user.getAddr()
				+ "', '" 
				+ user.getCardno() + "')";

		try {
			Statement stmt = (Statement) connection.createStatement();
			stmt.execute(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public UserEntity findUserByName(String usrname) {
		String sql = String.format("SELECT * from `User` where `Username`=?");
		System.out.println(sql);
		List<UserEntity> users = new LinkedList<UserEntity>();
		try{
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, usrname);
			System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()){
				UserEntity ue = new UserEntity();
				ue.setPassword(rs.getString("Password"));
				
				users.add(ue);
			}
			 System.out.println("Find user:" + users);
			 if(users != null && !users.isEmpty())
				 return users.get(0); 
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Boolean updateUser(UserEntity user) {

		String sql = "update User set Username='"
				+ user.getUsername()
				+ "', Password = '"
				+ user.getPassword()
				+ "', Nickname = '"
				+ user.getNickname()
				+ "', Firstname = '"
				+ user.getFirstname()
				+ "', Lastname = '"
				+ user.getLastname()
				+ "', Email = '"
				+ user.getEmail()
				+ "', Birthyear = '"
				+ user.getBirthyear()
				+ "', Address = '"
				+ user.getAddr()
				+ "', CardNumber = '" 
				+ user.getCardno() + "' where UserID = '"+user.getUserID()+"'";

		try {
			Statement stmt = (Statement) connection.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

}
