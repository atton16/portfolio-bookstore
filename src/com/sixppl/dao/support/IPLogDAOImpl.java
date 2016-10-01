package com.sixppl.dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sixppl.dao.IPLogDAO;
import com.sixppl.main.Application;

public class IPLogDAOImpl implements IPLogDAO {
	private Connection conn;

	public IPLogDAOImpl() {
		conn = Application.getSharedInstance().getDAOSupport().getConnection();
	}
	@Override
	public void add(String remoteAddress) {
		PreparedStatement stmt = null;
		String sql = "INSERT INTO `IPLog`(`IPAddress`) VALUES (?)";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, remoteAddress);
			stmt.execute();
			stmt.close();
		} catch (Exception e) {}
		
		try {
			if(stmt != null)
				stmt.close();
		} catch (Exception e) {}
	}

	@Override
	public Integer size() {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Integer size = 0;
		String sql = "SELECT COUNT(*) AS Total FROM `IPLog`";
		try {
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				size = rs.getInt("Total");
			}
			stmt.close();
		} catch (Exception e) {}
		
		try {
			if(rs != null)
				rs.close();
			if(stmt != null)
				stmt.close();
		} catch (Exception e) {}
		
		return size;
	}

}
