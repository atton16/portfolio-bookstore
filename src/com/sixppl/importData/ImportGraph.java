package com.sixppl.importData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sixppl.dto.EntityDTO;
import com.sixppl.dto.GraphDTO;

public class ImportGraph {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost/bookstore";
	private static final String DB_USER = "bookstore";
	private static final String DB_PASSWORD = "F8ruehc2xCgRmmev";
	
	public ImportGraph() {
		// TODO Auto-generated constructor stub
	}
	
	public void dropEntityTable() throws SQLException {
		String sql = "DROP TABLE IF EXISTS Entity";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	
	public void createEntityTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS Entity ("
				+ "ID BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,"
				+ "EntityID text NOT NULL,"
				+ "Class text NOT NULL,"
				+ "Type text NOT NULL,"
				+ "Caption text NOT NULL,"
				+ "PRIMARY KEY(ID)"
				+ ") ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	
	public void insertEntity(EntityDTO entity) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Entity (EntityID, Class, Type, Caption) VALUES(?,?,?,?)";
		Connection connection = null;
		try {
			connection = getDBConnection();
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
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
	}

	public void updateEntity(EntityDTO entity) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Entity SET EntityID=?, Class=?, Type=?, Caption=? WHERE ID=?";
		Connection connection = null;
		try {
			connection = getDBConnection();
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
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
	}

	public void deleteEntity(long ID) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Entity WHERE ID=?";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, ID);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
	}

	public ArrayList<EntityDTO> findEntity(String type, String keyword) {
		// TODO Auto-generated method stub
		ArrayList<EntityDTO> result = new ArrayList<EntityDTO>();
		String sql = "SELECT * FROM Entity WHERE Type=? AND Caption LIKE ?";
		if (keyword == null) {
			sql = "SELECT * FROM Entity WHERE Type=?";
		}
		Connection connection = null;
		try {
			connection = getDBConnection();
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
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
		return result;
	}

	public ArrayList<String> findLinkedEntity(String node) {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		Connection connection = null;
		try {
			connection = getDBConnection();
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
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
		return result;
	}

	public EntityDTO findEntityByEntityId(String entityID) {
		// TODO Auto-generated method stub
		EntityDTO result = new EntityDTO();
		String sql = "SELECT * FROM Entity WHERE EntityID=?";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, entityID);
			ResultSet rs = ps.executeQuery();
			result.setID(rs.getLong("ID"));
			result.setEntityID(rs.getString("EntityID"));
			result.setEntityClass(rs.getString("Class"));
			result.setEntityType(rs.getString("Type"));
			result.setEntityCaption(rs.getString("Caption"));
		    rs.close();
		    ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
		return result;
	}
	
	public void dropGraphTable() throws SQLException {
		String sql = "DROP TABLE IF EXISTS Graph";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	
	public void createGraphTable() throws SQLException {
		String sql = "CREATE TABLE IF NOT EXISTS Graph ("
				+ "ID BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,"
				+ "NodeFrom text NOT NULL,"
				+ "Edge text NOT NULL,"
				+ "NodeTo text NOT NULL,"
				+ "PRIMARY KEY(ID)"
				+ ") ENGINE=InnoDB CHARACTER SET utf8 COLLATE utf8_general_ci;";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	
	public void insertGraph(GraphDTO graph) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Graph (NodeFrom, Edge, NodeTo) VALUES(?,?,?)";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, graph.getNodeFrom());
			ps.setString(2, graph.getEdge());
			ps.setString(3, graph.getNodeTo());
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	
	public void updateGraph(GraphDTO graph) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Graph SET NodeFrom=?, Edge=?, NodeTo=? WHERE ID=?";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, graph.getNodeFrom());
			ps.setString(2, graph.getEdge());
			ps.setString(3, graph.getNodeTo());
			ps.setLong(4, graph.getID());
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	
	public void deleteGraph(long ID) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Graph WHERE ID=?";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, ID);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());

		}
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
	}
	
	private static Connection getDBConnection() {
		Connection dbConnection = null;
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return dbConnection;
	}
}
