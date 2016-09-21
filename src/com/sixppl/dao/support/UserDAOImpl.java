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

import com.sixppl.dao.UserDAO;
import com.sixppl.dto.UserDTO;
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
	public Boolean addUser(UserDTO user){

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

	public UserDTO findUserByName(String usrname) {
		String sql = String.format("SELECT * from `User` where `Username`=?");
		List<UserDTO> users = new LinkedList<UserDTO>();

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, usrname);
			rs = stmt.executeQuery();

			while(rs.next()){
				UserDTO ue = new UserDTO();
				ue.setPassword(rs.getString("Password"));

				users.add(ue);
			}
			
			if(users != null && !users.isEmpty()){
				rs.close();
				stmt.close();
				return users.get(0);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public Boolean updateUser(UserDTO user) {

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
