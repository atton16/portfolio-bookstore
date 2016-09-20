package com.sixppl.dao.support;

import java.sql.*;
import java.util.Enumeration;

public class DAOSupport {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/bookstore";

	//  Database credentials
	static final String USER = "bookstore";
	static final String PASS = "F8ruehc2xCgRmmev";

	private Connection conn;

	public DAOSupport() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return conn;
	}

	public String getDriverClassName() {
		return JDBC_DRIVER;
	}

	public void destroy() {
		Driver driver = null;
		Enumeration<Driver> drivers = DriverManager.getDrivers();  
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
        while(drivers.hasMoreElements()) {
            try {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);

            } catch (SQLException e) {
    			e.printStackTrace();
            }
        }
	}

}
