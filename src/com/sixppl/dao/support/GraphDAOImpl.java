package com.sixppl.dao.support;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.sixppl.bean.GraphBean;
import com.sixppl.bean.GraphOutputBean;
import com.sixppl.dao.GraphDAO;

public class GraphDAOImpl implements GraphDAO {
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public GraphDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insertGraph(GraphBean graph) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO Graph (NodeFrom, Edge, NodeTo) VALUES(?,?,?)";
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, graph.getNodeFrom());
			ps.setString(2, graph.getEdge());
			ps.setString(3, graph.getNodeTo());
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);

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

	@Override
	public void updateGraph(GraphBean graph) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Graph SET NodeFrom=?, Edge=?, NodeTo=? WHERE ID=?";
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, graph.getNodeFrom());
			ps.setString(2, graph.getEdge());
			ps.setString(3, graph.getNodeTo());
			ps.setLong(4, graph.getID());
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);

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

	@Override
	public void deleteGraph(long ID) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM Graph WHERE ID=?";
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setLong(1, ID);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);

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

	@Override
	public ArrayList<GraphBean> findGraph(String type, String keyword) {
		// TODO Auto-generated method stub
		ArrayList<GraphBean> result = new ArrayList<GraphBean>();
		String sql = "SELECT * FROM Entity WHERE Type=? AND Caption LIKE ?";
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, type);
			ps.setString(2, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();
			while ( rs.next() )
		    {
		      GraphBean graph = new GraphBean();
		      graph.setID(rs.getLong("ID"));
		      graph.setNodeFrom(rs.getString("NodeFrom"));
		      graph.setEdge(rs.getString("Edge"));
		      graph.setNodeTo(rs.getString("NodeTo"));
		      result.add(graph);
		    }
		    rs.close();
		    ps.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
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

	@Override
	public ArrayList<GraphOutputBean> findGraphOutput(String type, String keyword) {
		// TODO Auto-generated method stub
		ArrayList<GraphOutputBean> result = new ArrayList<GraphOutputBean>();
		String sql = "SELECT * FROM Entity WHERE Type=? AND Caption LIKE ?";
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, type);
			ps.setString(2, "%" + keyword + "%");
			ResultSet rs = ps.executeQuery();
			while ( rs.next() )
		    {
		      GraphOutputBean graph = new GraphOutputBean();
		      graph.setID(rs.getLong("ID"));
		      graph.setNodeFrom(rs.getString("NodeFrom"));
		      graph.setNodeFromCaption("NodeFromCaption");
		      graph.setEdge(rs.getString("Edge"));
		      graph.setEdgeCaption("EdgeCaption");
		      graph.setNodeTo(rs.getString("NodeTo"));
		      graph.setNodeToCaption("NodeToCaption");
		      result.add(graph);
		    }
		    rs.close();
		    ps.close();
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
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

}
