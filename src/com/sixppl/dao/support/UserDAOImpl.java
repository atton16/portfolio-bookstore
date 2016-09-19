package com.sixppl.dao.support;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import com.sixppl.bean.UserEntity;
import com.sixppl.dao.UserDAO;


public class UserDAOImpl implements UserDAO{
	static Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public UserDAOImpl() {
		connection = DAOSupport.getConnection();
		logger.info("Got connection");
	}
	

	@Override
	public Boolean addUser(UserEntity user) {

		String sql = "insert into User (UserID, Username, Password, Nickname, Firstname, Lastname,Email"
				+ "Birthyear,Address, CardNumber) values ('"
				+ user.getUserID()
				+ "', '"
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
		String sql = String.format("SELECT * from User where usrname='%s'", usrname);
		try{
			Statement stmt = (Statement) connection.createStatement();
			List<UserEntity> users = (List<UserEntity>) stmt.executeQuery(sql);
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
