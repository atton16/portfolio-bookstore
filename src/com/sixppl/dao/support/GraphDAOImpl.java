package com.sixppl.dao.support;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sixppl.dao.GraphDAO;
import com.sixppl.dto.GraphDTO;
import com.sixppl.dto.GraphOutputDTO;
import com.sixppl.main.Application;

public class GraphDAOImpl implements GraphDAO {
	
	public GraphDAOImpl() {
	}
	
	@Override
	public void dropGraphTable() throws SQLException {
		String sql = "DROP TABLE IF EXISTS Graph";
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
	public void insertGraph(GraphDTO graph, long PubID) {
		String sql = "INSERT INTO Graph (PubID, NodeFrom, Edge, NodeTo) VALUES(?,?,?,?)";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
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
	}

	@Override
	public void updateGraph(GraphDTO graph, long PubID) {
		String sql = "UPDATE Graph SET NodeFrom=?, Edge=?, NodeTo=? WHERE PubID=?";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
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
	}

	@Override
	public void deleteGraph(long PubID) {
		String sql = "DELETE FROM Graph WHERE PubID=?";
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
	public ArrayList<GraphOutputDTO> findGraphOutput(String node) {
		ArrayList<GraphOutputDTO> result = new ArrayList<GraphOutputDTO>();
		String sql = "SELECT * FROM graphoutput WHERE NodeFrom=? OR NodeTo=?";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, node);
			ps.setString(2, node);
			ResultSet rs = ps.executeQuery();
			while ( rs.next() )
			{
				GraphOutputDTO graph = new GraphOutputDTO();
				graph.setID(rs.getLong("ID"));
				graph.setNodeFromID(rs.getLong("NodeFromID"));
				graph.setNodeFrom(rs.getString("NodeFrom"));
				graph.setNodeFromCaption(rs.getString("NodeFromCaption"));
				graph.setEdgeID(rs.getLong("EdgeID"));
				graph.setEdge(rs.getString("Edge"));
				graph.setEdgeCaption(rs.getString("EdgeCaption"));
				graph.setNodeToID(rs.getLong("NodeToID"));
				graph.setNodeTo(rs.getString("NodeTo"));
				graph.setNodeToCaption(rs.getString("NodeToCaption"));
				result.add(graph);
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
	public ArrayList<GraphOutputDTO> findAllGraphOutput() throws SQLException {
		ArrayList<GraphOutputDTO> result = new ArrayList<GraphOutputDTO>();
		String sql = "SELECT * FROM graphoutput";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while ( rs.next() )
			{
				GraphOutputDTO graph = new GraphOutputDTO();
				graph.setID(rs.getLong("ID"));
				graph.setNodeFromID(rs.getLong("NodeFromID"));
				graph.setNodeFrom(rs.getString("NodeFrom"));
				graph.setNodeFromCaption(rs.getString("NodeFromCaption"));
				graph.setEdgeID(rs.getLong("EdgeID"));
				graph.setEdge(rs.getString("Edge"));
				graph.setEdgeCaption(rs.getString("EdgeCaption"));
				graph.setNodeToID(rs.getLong("NodeToID"));
				graph.setNodeTo(rs.getString("NodeTo"));
				graph.setNodeToCaption(rs.getString("NodeToCaption"));
				result.add(graph);
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
