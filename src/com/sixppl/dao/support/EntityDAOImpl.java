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
	}
	
	@Override
	public void dropEntityTable() throws SQLException {
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
	public void createEntityTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS Entity ("
			+ "ID BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,"
			+ "PubID BIGINT(20) UNSIGNED NOT NULL,"
			+ "EntityID text NOT NULL,"
			+ "Class text NOT NULL,"
			+ "Type text NOT NULL,"
			+ "Caption text NOT NULL,"
			+ "PRIMARY KEY(ID)"
			+ ") ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;";
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
	public void insertEntity(EntityDTO entity, long PubID) throws SQLException {
		String sql = "INSERT INTO Entity (PubID, EntityID, Class, Type, Caption) VALUES(?,?,?,?,?)";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, PubID);
			ps.setString(2, entity.getEntityID());
			ps.setString(3, entity.getEntityClass());
			ps.setString(4, entity.getEntityType());
			ps.setString(5, entity.getEntityCaption());
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void updateEntity(EntityDTO entity, long PubID) throws SQLException {
		String sql = "UPDATE Entity SET EntityID=?, Class=?, Type=?, Caption=? WHERE PubID=?";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, entity.getEntityID());
			ps.setString(2, entity.getEntityClass());
			ps.setString(3, entity.getEntityType());
			ps.setString(4, entity.getEntityCaption());
			ps.setLong(5, PubID);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void updatePubIDByEntityID(int pubID, String entityID) throws SQLException {
		String sql = "UPDATE Entity SET PubID = ? WHERE EntityID=?";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, pubID);
			ps.setString(2, entityID);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void deleteEntity(long PubID) throws SQLException {
		String sql = "DELETE FROM Entity WHERE PubID=?";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, PubID);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public EntityDTO findEntityByEntityId(String entityID) throws SQLException {
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
	
	@Override
	public EntityDTO findEntityByCaption(String Class, String Type, String Caption) throws SQLException {
		EntityDTO result = new EntityDTO();
		String sql = "SELECT * FROM Entity WHERE Class=? AND Type=? AND Caption =?";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, Class);
			ps.setString(2, Type);
			ps.setString(3, Caption);
			ResultSet rs = ps.executeQuery();
			if ( rs.next() )
		    {
		      EntityDTO entity = new EntityDTO();
		      entity.setID(rs.getLong("ID"));
		      entity.setEntityID(rs.getString("EntityID"));
		      entity.setEntityClass(rs.getString("Class"));
		      entity.setEntityType(rs.getString("Type"));
		      entity.setEntityCaption(rs.getString("Caption"));
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
	public ArrayList<EntityDTO> findEntity(String type, String keyword) throws SQLException {
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
	public ArrayList<EntityDTO> findAllNodes() throws SQLException {
		ArrayList<EntityDTO> result = new ArrayList<EntityDTO>();
		String sql = "SELECT * FROM Entity WHERE Class='Node'";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
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
	public ArrayList<EntityDTO> getRandomNodes(int nodeAmount) throws SQLException {
		ArrayList<EntityDTO> result = new ArrayList<EntityDTO>();
		String sql = "SELECT * FROM Entity WHERE Class='Node' ORDER BY RAND() LIMIT ?";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, nodeAmount);
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
	public long getMaxNodeID(String Type) throws SQLException {
		long result = 0;
		String MaxEntityID = "";
		String sql = "SELECT EntityID FROM Entity WHERE Type=? ORDER BY EntityID DESC LIMIT 0,1";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, Type);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				MaxEntityID = rs.getString("EntityID");
			}
		    rs.close();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		if(MaxEntityID != null && !MaxEntityID.equals("")) {
			result = Long.parseLong(MaxEntityID.substring(1));
		}
		return result;
	}

	@Override
	public long getMaxEdgeID() throws SQLException {
		long result = 0;
		String MaxEntityID = "";
		String sql = "SELECT EntityID FROM Entity WHERE Class='Edge' ORDER BY EntityID DESC LIMIT 0,1";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				MaxEntityID = rs.getString("EntityID");
			}
		    rs.close();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		if(MaxEntityID != null && !MaxEntityID.equals("")) {
			result = Long.parseLong(MaxEntityID.substring(1));
		}
		return result;
	}

	
}
