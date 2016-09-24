package com.sixppl.dao.support;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.sixppl.dao.EntityDAO;
import com.sixppl.dto.EntityDTO;
import com.sixppl.main.Application;

public class EntityDAOImpl implements EntityDAO{

	public EntityDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void dropTable() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DROP TABLE IF EXISTS Entity";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void createTable() throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DROP TABLE IF EXISTS Entity";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		sql = "CREATE TABLE IF NOT EXISTS Entity ("
			+ "ID BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,"
			+ "EntityID text NOT NULL,"
			+ "Class text NOT NULL,"
			+ "Type text NOT NULL,"
			+ "Caption text NOT NULL,"
			+ "PRIMARY KEY(ID)"
			+ ") ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;";
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void insertEntity(EntityDTO entity) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Entity (EntityID, Class, Type, Caption) VALUES(?,?,?,?)";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, entity.getEntityID());
			ps.setString(2, entity.getEntityClass());
			ps.setString(3, entity.getEntityType());
			ps.setString(4, entity.getEntityCaption());
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void updateEntity(EntityDTO entity) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "UPDATE Entity SET EntityID=?, Class=?, Type=?, Caption=? WHERE ID=?";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, entity.getEntityID());
			ps.setString(2, entity.getEntityClass());
			ps.setString(3, entity.getEntityType());
			ps.setString(4, entity.getEntityCaption());
			ps.setLong(5, entity.getID());
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deleteEntity(long ID) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Entity WHERE ID=?";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, ID);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public ArrayList<EntityDTO> findEntity(String type, String keyword) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<EntityDTO> result = new ArrayList<EntityDTO>();
		String sql = "SELECT * FROM Entity WHERE Type=? AND Caption LIKE ?";
		if (keyword == null) {
			sql = "SELECT * FROM Entity WHERE Type=?";
		}
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, type);
			if (keyword != null) {
				ps.setString(2, "%" + keyword + "%");
			}
			ResultSet rs = ps.executeQuery();
			while ( rs.next() )
		    {
		      EntityDTO entity = new EntityDTO();
		      entity.setID(rs.getLong("ID"));
		      entity.setEntityID(rs.getString("EntityID"));
		      entity.setEntityClass(rs.getString("Class"));
		      entity.setEntityType(rs.getString("Type"));
		      entity.setEntityCaption(rs.getString("Caption"));
		      result.add(entity);
		    }
		    rs.close();
		    ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public ArrayList<String> findLinkedEntity(String node) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			CallableStatement cStmt = connection.prepareCall("{CALL ListNode(?)}");
			cStmt.setString(1, node);
			ResultSet rs = cStmt.executeQuery();
			while ( rs.next() )
		    {
		      result.add(rs.getString("NodeID"));
		    }
		    rs.close();
		    cStmt.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public EntityDTO findEntityByEntityId(String entityID) throws SQLException {
		// TODO Auto-generated method stub
		EntityDTO result = new EntityDTO();
		String sql = "SELECT * FROM Entity WHERE EntityID=?";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, entityID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result.setID(rs.getLong("ID"));
				result.setEntityID(rs.getString("EntityID"));
				result.setEntityClass(rs.getString("Class"));
				result.setEntityType(rs.getString("Type"));
				result.setEntityCaption(rs.getString("Caption"));
			}
		    rs.close();
		    ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
}
