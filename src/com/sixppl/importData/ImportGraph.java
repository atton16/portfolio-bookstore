package com.sixppl.importData;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sixppl.dto.EntityDTO;
import com.sixppl.dto.GraphDTO;
import com.sixppl.dto.ListingDTO;

public class ImportGraph {

	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_CONNECTION = "jdbc:mysql://localhost/bookstore";
	private static final String DB_USER = "bookstore";
	private static final String DB_PASSWORD = "F8ruehc2xCgRmmev";
	
	Connection connection;
	
	public ImportGraph() {
	}
	
	public long getMaxPubID() {
		long result = 0;
		String sql = "SELECT MAX(PubID) AS CurrentPubID FROM Listing";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = rs.getLong("CurrentPubID");
			}
		    rs.close();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		finally{
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
		return result;
	}
	
	public int getTotal(){
		int total = 0;
		PreparedStatement stmt = null;
		Connection connection = null;
		String sql = "SELECT COUNT(*) AS Total FROM Listing";
		try {
			connection = getDBConnection();
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				total = rs.getInt("Total");
			}
			System.out.println("Total Pub : " + total);
			//STEP 6: Clean-up environment
			rs.close();
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}//end try
		return total;
	}
	
	public boolean addListing(ListingDTO pubSell){
		boolean pass = false;
		PreparedStatement stmt = null;
		Connection connection = null;
		String sql = "INSERT INTO `Listing` (`Title`,`Authors`,`Editors`,`Type`,`Year`,`Venue`,`SellerID`,`Picture`,`Price`,`Status`) VALUES (?,?,?,?,?,?,?,?,?,?)";
		try {
			connection = getDBConnection();
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, pubSell.title);
			String authors = "";
			for(String author: pubSell.authors){
				authors += author;
				authors += ",";
			}
			authors = authors.substring(0,authors.lastIndexOf(","));
			stmt.setString(2, authors);
			String editors = "";
			for(String editor: pubSell.editors){
				editors += editor;
				editors += ",";
			}
			editors = editors.substring(0,editors.lastIndexOf(","));
			stmt.setString(3, editors);
			
			stmt.setString(4, pubSell.type);
			stmt.setInt(5, pubSell.year);
			stmt.setString(6, pubSell.venue);
			stmt.setInt(7, pubSell.sellerID);
			@SuppressWarnings("resource")
			Scanner s = new Scanner(pubSell.picture).useDelimiter("\\A");
			String picString = s.hasNext() ? s.next() : "";
			stmt.setString(8, picString);
			stmt.setFloat(9, pubSell.price);
			stmt.setBoolean(10, true);
			stmt.executeUpdate();
			pass = true;

			//STEP 6: Clean-up environment
			
			s.close();
			stmt.close();
		}catch(SQLException se){
			//Handle errors for JDBC
			pass = false;
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
			pass = false;
		}finally{
			//finally block used to close resources
			try{
				if(stmt!=null)
					stmt.close();
			}catch(SQLException se2){
			}// nothing we can do
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}//end try
		return pass;
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
				+ "PubID BIGINT(20) UNSIGNED NOT NULL,"
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
	
	public void insertEntity(EntityDTO entity, long PubID) {
		String sql = "INSERT INTO Entity (PubID, EntityID, Class, Type, Caption) VALUES(?,?,?,?,?)";
		Connection connection = null;
		try {
			connection = getDBConnection();
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
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
	}

	public void updateEntity(EntityDTO entity, long PubID) {
		String sql = "UPDATE Entity SET EntityID=?, Class=?, Type=?, Caption=? WHERE PubID=?";
		Connection connection = null;
		try {
			connection = getDBConnection();
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
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
	}

	public void deleteEntity(long PubID) {
		String sql = "DELETE FROM Entity WHERE PubID=?";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, PubID);
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
	
	public EntityDTO findEntityByEntityId(String entityID) {
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
	
	public EntityDTO findEntityByCaption(String Class, String Type, String Caption) throws SQLException {
		EntityDTO result = new EntityDTO();
		String sql = "SELECT * FROM Entity WHERE Class=? AND Type=? AND Caption =?";
		Connection connection = null;
		try {
			connection = getDBConnection();
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

	public ArrayList<EntityDTO> findEntity(String type, String keyword) {
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

	public ArrayList<EntityDTO> findAllNodes() throws SQLException {
		ArrayList<EntityDTO> result = new ArrayList<EntityDTO>();
		String sql = "SELECT * FROM Entity WHERE Class='Node'";
		Connection connection = null;
		try {
			connection = getDBConnection();
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
	
	public ArrayList<EntityDTO> getRandomNodes(long nodeAmount) throws SQLException {
		ArrayList<EntityDTO> result = new ArrayList<EntityDTO>();
		String sql = "SELECT * FROM Entity WHERE Class='Node' ORDER BY RAND() LIMIT 3";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			//ps.setLong(1, nodeAmount);
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
	
	public long getMaxNodeID(String Type) throws SQLException {
		long result = 0;
		String MaxEntityID = "";
		String sql = "SELECT EntityID FROM Entity WHERE Type=? ORDER BY EntityID DESC LIMIT 0,1";
		Connection connection = null;
		try {
			connection = getDBConnection();
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
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
		if(MaxEntityID != null && !MaxEntityID.equals("")) {
			result = Long.parseLong(MaxEntityID.substring(1));
		}
		return result;
	}
	
	public long getMaxEdgeID() throws SQLException {
		long result = 0;
		String MaxEntityID = "";
		String sql = "SELECT EntityID FROM Entity WHERE Class='Edge' ORDER BY EntityID DESC LIMIT 0,1";
		Connection connection = null;
		try {
			connection = getDBConnection();
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
		finally {
			if (connection != null) {
				try {
					connection.close();
				}
				catch (SQLException e) {}
			}
		}
		if(MaxEntityID != null && !MaxEntityID.equals("")) {
			result = Long.parseLong(MaxEntityID.substring(1));
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
				+ "PubID BIGINT(20) UNSIGNED NOT NULL,"
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
	
	public void insertGraph(GraphDTO graph, long PubID) {
		String sql = "INSERT INTO Graph (PubID, NodeFrom, Edge, NodeTo) VALUES(?,?,?,?)";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, PubID);
			ps.setString(2, graph.getNodeFrom());
			ps.setString(3, graph.getEdge());
			ps.setString(4, graph.getNodeTo());
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
	
	public void updateGraph(GraphDTO graph, long PubID) {
		String sql = "UPDATE Graph SET NodeFrom=?, Edge=?, NodeTo=? WHERE PubID=?";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, graph.getNodeFrom());
			ps.setString(2, graph.getEdge());
			ps.setString(3, graph.getNodeTo());
			ps.setLong(4, PubID);
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
	
	public void deleteGraph(long PubID) {
		String sql = "DELETE FROM Graph WHERE PubID=?";
		Connection connection = null;
		try {
			connection = getDBConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, PubID);
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
