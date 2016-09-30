package com.sixppl.dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sixppl.dao.AdminDAO;
import com.sixppl.main.Application;

public class AdminDAOImpl implements AdminDAO {

	@Override
	public Boolean promote(int userId) {
		Connection con = Application.getSharedInstance().getDAOSupport().getConnection();
		String sql = "INSERT INTO `Admin`(`UserID`, `Level`) VALUES (?,1)";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, userId);
			stmt.executeQuery();
		} catch (SQLException e) {
			return false;
		} finally {
			try {
				if(stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	@Override
	public Boolean demote(int userId, int promoterId) {
		Connection con = Application.getSharedInstance().getDAOSupport().getConnection();
		String sql = "DELETE FROM `Admin` WHERE `UserID` = ? AND `Level` < ?";
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, userId);
			stmt.setInt(2, getLevel(promoterId));
			stmt.executeQuery();
		} catch (SQLException e) {
			return false;
		} finally {
			try {
				if(stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	@Override
	public Integer getLevel(int userId) {
		Connection con = Application.getSharedInstance().getDAOSupport().getConnection();
		String sql = "SELECT FROM `Admin` WHERE `UserID` = ?";
		PreparedStatement stmt = null;
		Integer level = 0;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, userId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				level = rs.getInt("Level");
			}
		} catch (SQLException e) {
			return 0;
		} finally {
			try {
				if(stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return level;
	}

}
