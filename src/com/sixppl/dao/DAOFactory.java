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
		TRANSACTION,
		ADMIN,
		ADMIN_USER,
		ADMIN_USER_BAN,
		ADMIN_LOGIN,
		ADMIN_PUB,
		PAGE_HITS,
		IP_LOG,
		LISTING_STATISTICS
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
		daos.put(DAO.TRANSACTION, new TransactionDAOImpl());
		daos.put(DAO.ADMIN, new AdminDAOImpl());
		daos.put(DAO.ADMIN_USER, new AdminUserDAOImpl());
		daos.put(DAO.ADMIN_USER_BAN, new AdminUserBanDAOImpl());
		daos.put(DAO.ADMIN_LOGIN, new AdminLoginDAOImpl());
		daos.put(DAO.ADMIN_PUB, new AdminPubDAOImpl());
		daos.put(DAO.PAGE_HITS, new PageHitsDAOImpl());
		daos.put(DAO.IP_LOG, new IPLogDAOImpl());
		daos.put(DAO.LISTING_STATISTICS, new ListingStatisticsDAOImpl());

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
	
	public TransactionDAO getTransactionDAO() {
		return (TransactionDAO) daos.get(DAO.TRANSACTION);
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
	
	public AdminPubDAO getAdminPubDAO() {
		return (AdminPubDAO) daos.get(DAO.ADMIN_PUB);
	}
	
	public PageHitsDAO getPageHitsDAO() {
		return (PageHitsDAO) daos.get(DAO.PAGE_HITS);
	}
	
	public IPLogDAO getIPLogDAO() {
		return (IPLogDAO) daos.get(DAO.IP_LOG);
	}
	
	public ListingStatisticsDAO getListingStatisticsDAO() {
		return (ListingStatisticsDAO) daos.get(DAO.LISTING_STATISTICS);
	}
}
