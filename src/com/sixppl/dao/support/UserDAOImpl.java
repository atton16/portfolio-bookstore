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
	public static final int MYSQL_DUPLICATE= 1062;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public UserDAOImpl() {
		connection = Application.getSharedInstance().getDAOSupport().getConnection();
		logger.info("Got connection");
	}


	@Override
	public boolean addUser(UserDTO user){
	boolean flag = true;
	String sql = "insert into User (Username, Password, Nickname, Firstname, "
			+ "Lastname,Email, Birthyear,Address, CardNumber,TokenString) values (?,?,?,?,?,?,?,?,?,?)";
	PreparedStatement stmt = null;
	try {
		stmt = connection.prepareStatement(sql);
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getNickname());
		stmt.setString(4, user.getFirstname());
		stmt.setString(5, user.getLastname());
		stmt.setString(6, user.getEmail());
		stmt.setString(7, user.getBirthyear());
		stmt.setString(8, user.getAddr());
		stmt.setString(9, user.getCardno());
		stmt.setString(10, user.getTokenstring());
		int i = stmt.executeUpdate();
			if(i==0){
				return false;
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return flag;

	}

	@Override
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
				ue.setUserID(Integer.parseInt(rs.getString("UserID")));
				ue.setUsername(rs.getString("Username"));
				ue.setPassword(rs.getString("Password"));
				ue.setNickname(rs.getString("Nickname"));
				ue.setFirstname(rs.getString("Firstname"));
				ue.setLastname(rs.getString("Lastname"));
				ue.setEmail(rs.getString("Email"));
				ue.setNewemail(rs.getString("NewEmail"));
				ue.setBirthyear(rs.getString("Birthyear"));
				ue.setAddr(rs.getString("Address"));
				ue.setCardno(rs.getString("CardNumber"));
				ue.setTokenstring(rs.getString("TokenString"));

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
	public UserDTO findUserByToken(String token) {
		String sql = String.format("SELECT * from `User` where `TokenString`=?");
		List<UserDTO> users = new LinkedList<UserDTO>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, token);
			rs = stmt.executeQuery();

			while(rs.next()){
				UserDTO ue = new UserDTO();
				ue.setUserID(Integer.parseInt(rs.getString("UserID")));
				ue.setUsername(rs.getString("Username"));
				ue.setPassword(rs.getString("Password"));
				ue.setNickname(rs.getString("Nickname"));
				ue.setFirstname(rs.getString("Firstname"));
				ue.setLastname(rs.getString("Lastname"));
				ue.setEmail(rs.getString("Email"));
				ue.setNewemail(rs.getString("NewEmail"));
				ue.setBirthyear(rs.getString("Birthyear"));
				ue.setAddr(rs.getString("Address"));
				ue.setCardno(rs.getString("CardNumber"));
				ue.setTokenstring(rs.getString("TokenString"));
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
	public boolean updateUser(UserDTO user) {
		boolean flag = true;
		String sql = "update User set Password=?,Nickname=?,Firstname=?,Lastname=?"
				+ "Email=?,NewEmail=?,Birthyear=?,Address=?,CardNumber=?,TokenString=? where Username=?";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, user.getPassword());
			stmt.setString(2, user.getNickname());
			stmt.setString(3, user.getFirstname());
			stmt.setString(4, user.getLastname());
			stmt.setString(5, user.getEmail());
			stmt.setString(6, user.getNewemail());
			stmt.setString(7, user.getBirthyear());
			stmt.setString(8, user.getAddr());
			stmt.setString(9, user.getCardno());
			stmt.setString(10, user.getTokenstring());
			stmt.setString(11, user.getUsername());
			int i = stmt.executeUpdate();
			if(i==0){
				flag = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return flag;

	}


}
