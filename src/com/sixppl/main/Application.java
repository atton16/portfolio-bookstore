package com.sixppl.main;

import javax.servlet.ServletContext;

import com.sixppl.dao.DAOFactory;
import com.sixppl.dao.ListingDAO;
import com.sixppl.dao.support.DAOSupport;

/**
 * Singleton Application
 * @author atton16
 *
 */
public class Application {
	public static final String DEVELOPMENT_ENV = "DEVELOPMENT";
	public static final String PRODUCTION_ENV = "PRODUCTION";
	private static final String ENV = DEVELOPMENT_ENV;
	private static final String PRODUCTION_IP = "1.1.1.1";
	private static final String PRODUCTION_PORT = "8443";
	private static final String title = "DBLP";
	private static Application app;
	private ServletContext servletContext;
	private DAOFactory daoFactory;
	private DAOSupport daoSupport;
	
	public static Application getSharedInstance() {
		if (app == null) {
			app = new Application();
		}
		return app;
	}

	public void init(ServletContext servletContext) {
		this.servletContext = servletContext;
		this.daoSupport = new DAOSupport();
		this.daoFactory = new DAOFactory();
	}
	
	public String getContextPath() {
		return servletContext.getContextPath();
	}
	
	public String getTitle() {
		return title;
	}
	
	public Integer getListingCount() {
		Integer count = 0;
		ListingDAO dao = Application.getSharedInstance().getDAOFactory().getListingDAO();
		try{
			count = dao.getListingCount();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return count;
	}
	
	public DAOFactory getDAOFactory() {
		return daoFactory;
	}
	
	public DAOSupport getDAOSupport() {
		return daoSupport;
	}
	
	public void destroy() {
		daoSupport.destroy();
	}
	
	public String getEnvironment() {
		return Application.ENV;
	}
	
	public String getProductionIP() {
		return Application.PRODUCTION_IP;
	}
	
	public String getProductionPort() {
		return Application.PRODUCTION_PORT;
	}
}
