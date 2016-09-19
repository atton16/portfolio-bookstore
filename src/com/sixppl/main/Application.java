package com.sixppl.main;

import javax.servlet.ServletContext;

import com.sixppl.dao.DAOFactory;
import com.sixppl.dao.support.DAOSupport;

/**
 * Singleton Application
 * @author atton16
 *
 */
public class Application {
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
		this.daoFactory = new DAOFactory();
		this.daoSupport = new DAOSupport();
	}
	
	public String getContextPath() {
		return servletContext.getContextPath();
	}
	
	public String getTitle() {
		return title;
	}
	
	public DAOFactory getDAOFactory() {
		return daoFactory;
	}
	
	public DAOSupport getDAOSupport() {
		return daoSupport;
	}
}
