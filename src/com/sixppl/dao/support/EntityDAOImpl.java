package com.sixppl.dao.support;

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
	public void insertEntity(EntityDTO entity) {
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
	public void updateEntity(EntityDTO entity) {
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
	public void deleteEntity(long ID) {
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
	public ArrayList<EntityDTO> findEntity(String type, String keyword) {
		// TODO Auto-generated method stub
		ArrayList<EntityDTO> result = new ArrayList<EntityDTO>();
		String sql = "SELECT * FROM Entity WHERE Type=? AND Caption LIKE ?";
		Connection connection = null;
		try {
			connection = Application.getSharedInstance().getDAOSupport().getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, type);
			ps.setString(2, "%" + keyword + "%");
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
