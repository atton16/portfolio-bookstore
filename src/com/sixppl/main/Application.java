package com.sixppl.main;

import javax.servlet.ServletContext;

/**
 * Singleton Application
 * @author atton16
 *
 */
public class Application {
	private static Application app;
	private ServletContext servletContext;
	
	public static Application getSharedInstance() {
		if (app == null) {
			app = new Application();
		}
		return app;
	}
	
	public String getContextPath() {
		return servletContext.getContextPath();
	}
	
	public void init(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
}
