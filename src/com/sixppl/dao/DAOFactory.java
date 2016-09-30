package com.sixppl.dao;

import java.util.HashMap;
import java.util.Map;
import com.sixppl.dao.support.*;

public class DAOFactory {
	private enum DAO {
		USER,
		SESSION,
		ENTITY,
		GRAPH,
		CART,
		LISTING,
		ADMIN,
		ADMIN_USER,
		ADMIN_USER_BAN,
		ADMIN_LOGIN,
		ADMIN_ANALYTICS,
		ADMIN_PUB,
		ADMIN_USER_ACTIVITY
	}

	private Map<DAO, Object> daos;
	
	public DAOFactory() {
		daos = new HashMap<DAO, Object>();
		daos.put(DAO.USER, new UserDAOImpl());
		daos.put(DAO.SESSION, new SessionDAOImpl());
		daos.put(DAO.ENTITY, new EntityDAOImpl());
		daos.put(DAO.GRAPH, new GraphDAOImpl());
		daos.put(DAO.CART, new CartDAOImpl());
		daos.put(DAO.LISTING, new ListingDAOImpl());
		daos.put(DAO.ADMIN, new AdminDAOImpl());
		daos.put(DAO.ADMIN_USER, new AdminUserDAOImpl());
		daos.put(DAO.ADMIN_USER_BAN, new AdminUserBanDAOImpl());
		daos.put(DAO.ADMIN_LOGIN, new AdminLoginDAOImpl());
		daos.put(DAO.ADMIN_ANALYTICS, new AdminAnalyticsDAOImpl());
		daos.put(DAO.ADMIN_PUB, new AdminPubDAOImpl());
		daos.put(DAO.ADMIN_USER_ACTIVITY, new AdminUserActivityDAOImpl());

	}
	
	public UserDAO getUserDAO() {
		return (UserDAO) daos.get(DAO.USER);
	}

	public SessionDAO getSessionDAO(){
		return (SessionDAO) daos.get(DAO.SESSION);
	}

	public EntityDAO getEntityDAO() {
		return (EntityDAO) daos.get(DAO.ENTITY);
	}
	
	public GraphDAO getGraphDAO() {
		return (GraphDAO) daos.get(DAO.GRAPH);
	}
	
	public CartDAO getCartDAO() {
		return (CartDAO) daos.get(DAO.CART);
	}
	
	public ListingDAO getListingDAO() {
		return (ListingDAO) daos.get(DAO.LISTING);
	}
	
	public AdminDAO getAdminDAO() {
		return (AdminDAO) daos.get(DAO.ADMIN_USER);
	}
	
	public AdminUserDAO getAdminUserDAO() {
		return (AdminUserDAO) daos.get(DAO.ADMIN_USER);
	}
	
	public AdminUserBanDAO getAdminUserBanDAO() {
		return (AdminUserBanDAO) daos.get(DAO.ADMIN_USER_BAN);
	}
	
	public AdminLoginDAO getAdminLoginDAO() {
		return (AdminLoginDAO) daos.get(DAO.ADMIN_LOGIN);
	}
	
	public AdminAnalyticsDAO getAdminAnalyticsDAO() {
		return (AdminAnalyticsDAO) daos.get(DAO.ADMIN_ANALYTICS);
	}
	
	public AdminPubDAO getAdminPubDAO() {
		return (AdminPubDAO) daos.get(DAO.ADMIN_PUB);
	}
	
	public AdminUserActivityDAO getAdminUserActivityDAO() {
		return (AdminUserActivityDAO) daos.get(DAO.ADMIN_USER_ACTIVITY);
	}
}
