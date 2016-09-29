package com.sixppl.dao;

import java.util.HashMap;
import java.util.Map;
import com.sixppl.dao.support.*;

public class DAOFactory {
	private static final String DUMMY_DAO = "dummyDAO";
	private static final String FOO_DAO = "fooDAO";
	private static final String USER_DAO = "userDAO";
	private static final String SESSION_DAO = "sessionDAO";
	private static final String ENTITY_DAO = "entityDAO";
	private static final String GRAPH_DAO = "graphDAO";
	private static final String CART_DAO = "cartDAO";
	private static final String LISTING_DAO = "listingDAO";
	private static final String ADMINUSER_DAO = "adminUserDAO";
	private static final String ADMINUSERBAN_DAO = "adminUserBanDAO";
	private static final String ADMINLOGIN_DAO = "adminLoginDAO";
	private static final String ADMINANALYTICS_DAO = "adminAnalyticsDAO";
	private static final String ADMINPUB_DAO = "adminPubDAO";
	private static final String ADMINUSERACTIVITY_DAO = "adminUserActivityDAO";

	private Map<String, Object> daos;
	
	public DAOFactory() {
		daos = new HashMap<String, Object>();
		daos.put(DUMMY_DAO, new DummyDAOImpl());
		daos.put(FOO_DAO, new FooDAOImpl());
		daos.put(USER_DAO, new UserDAOImpl());
		daos.put(SESSION_DAO, new SessionDAOImpl());
		daos.put(ENTITY_DAO, new EntityDAOImpl());
		daos.put(GRAPH_DAO, new GraphDAOImpl());
		daos.put(CART_DAO, new CartDAOImpl());
		daos.put(LISTING_DAO, new ListingDAOImpl());
		daos.put(ADMINUSER_DAO, new AdminUserDAOImpl());
		daos.put(ADMINUSERBAN_DAO, new AdminUserBanDAOImpl());
		daos.put(ADMINLOGIN_DAO, new AdminLoginDAOImpl());
		daos.put(ADMINANALYTICS_DAO, new AdminAnalyticsDAOImpl());
		daos.put(ADMINPUB_DAO, new AdminPubDAOImpl());
		daos.put(ADMINUSERACTIVITY_DAO, new AdminUserActivityDAOImpl());

	}
	
	public DummyDAO getDummyDAO() {
		return (DummyDAO) daos.get(DUMMY_DAO);
	}
	
	public FooDAO getFooDAO() {
		return (FooDAO) daos.get(FOO_DAO);
	}
	
	public UserDAO getUserDAO() {
		return (UserDAO) daos.get(USER_DAO);
	}

	public SessionDAO getSessionDAO(){
		return (SessionDAO) daos.get(SESSION_DAO);
	}

	public EntityDAO getEntityDAO() {
		return (EntityDAO) daos.get(ENTITY_DAO);
	}
	
	public GraphDAO getGraphDAO() {
		return (GraphDAO) daos.get(GRAPH_DAO);
	}
	
	public CartDAO getCartDAO() {
		return (CartDAO) daos.get(CART_DAO);
	}
	
	public ListingDAO getListingDAO() {
		return (ListingDAO) daos.get(LISTING_DAO);
	}
	
	public AdminUserDAO getAdminUserDAO() {
		return (AdminUserDAO) daos.get(ADMINUSER_DAO);
	}
	
	public AdminUserBanDAO getAdminUserBanDAO() {
		return (AdminUserBanDAO) daos.get(ADMINUSERBAN_DAO);
	}
	
	public AdminLoginDAO getAdminLoginDAO() {
		return (AdminLoginDAO) daos.get(ADMINLOGIN_DAO);
	}
	
	public AdminAnalyticsDAO getAdminAnalyticsDAO() {
		return (AdminAnalyticsDAO) daos.get(ADMINANALYTICS_DAO);
	}
	
	public AdminPubDAO getAdminPubDAO() {
		return (AdminPubDAO) daos.get(ADMINPUB_DAO);
	}
	
	public AdminUserActivityDAO getAdminUserActivityDAO() {
		return (AdminUserActivityDAO) daos.get(ADMINUSERACTIVITY_DAO);
	}
}
