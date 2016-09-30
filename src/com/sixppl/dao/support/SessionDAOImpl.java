package com.sixppl.dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.sixppl.dao.SessionDAO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.main.Application;

public class SessionDAOImpl implements SessionDAO {
	static Logger logger = Logger.getLogger(SessionDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public SessionDAOImpl() {
		connection = Application.getSharedInstance().getDAOSupport().getConnection();
		logger.info("Got connection");
	}

	public  boolean addSession(SessionDTO session) {
		boolean flag = true;

		String sql = "UPDATE `LoginSessions` SET (`UserID`) VALUES (?) WHERE `JSESSIONID` = ?";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, session.getUserID());
			stmt.setString(2, session.getSessionID());
			try{
				stmt.executeUpdate();
				stmt.close();
			} catch (SQLException e) {
				sql = "INSERT INTO `LoginSessions`(`UserID`, `JSESSIONID`) VALUES (?,?)";
				stmt = connection.prepareStatement(sql);
				stmt.setInt(1, session.getUserID());
				stmt.setString(2, session.getSessionID());
				int i = stmt.executeUpdate();
				if(i==0){
					return false;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return flag;
	}
	public  boolean delSession(SessionDTO session) {
		boolean flag = true;

		String sql = "delete from  LoginSessions where JSESSIONID = ?";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, session.getSessionID());

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
	public  int finduserIDbySession(SessionDTO session){
		String sql = String.format("SELECT * from `LoginSessions` where `JSESSIONID`=?");

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, session.getSessionID());
			rs = stmt.executeQuery();
			List<Integer> userIds = new LinkedList<Integer>();

			while(rs.next()){
				int userid = Integer.parseInt(rs.getString("UserID"));
				userIds.add(userid);
			}

			if(userIds != null && !userIds.isEmpty()){
				rs.close();
				stmt.close();
				return userIds.get(0);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return -1;

	}

	@Override
	public int findAdminUserIDbySession(SessionDTO session) {
		String sql = String.format("SELECT * from `AdminLoginSessions` where `JSESSIONID`=?");

		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, session.getSessionID());
			rs = stmt.executeQuery();
			List<Integer> userIds = new LinkedList<Integer>();

			while(rs.next()){
				int userid = Integer.parseInt(rs.getString("UserID"));
				userIds.add(userid);
			}

			if(userIds != null && !userIds.isEmpty()){
				rs.close();
				stmt.close();
				return userIds.get(0);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return -1;
	};

}
