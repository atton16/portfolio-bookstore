package com.sixppl.dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.UserDAO;
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

		String sql = "INSERT INTO `LoginSessions`(`UserID`, `JSESSIONID`) VALUES (?,?)";
		PreparedStatement stmt = null;
		try {
			System.out.println("test the null pointer");
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, session.getUserID());
			stmt.setInt(2, session.getSessionID());
			
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
	public  boolean delSession(SessionDTO session) {
		boolean flag = true;

		String sql = "delete from  LoginSessions where JSESSIONID = ?";
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, session.getSessionID());
			
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

}