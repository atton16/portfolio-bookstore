package com.sixppl.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.cmd.*;
import com.sixppl.main.Application;

/**
 * Main Servlet
 * @author atton16
 *
 */
@WebServlet(urlPatterns = {
		"/search",
		"/results",
		"/cart",
		"/cart/remove",
		"/dummy" }, loadOnStartup = 0)
public class Asst2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TITLE_ATTRIBUTE = "title";
	private static final String CONTEXTPATH_ATTRIBUTE = "contextPath";
	private static final String DUMMY_COMMAND = "dummyCommand";
	private static final String SEARCHTERMS_COMMAND = "searchTermsCommand";
	
	Map<String,Command> commands;

    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	Application.getSharedInstance().init(this.getServletContext());
    	
    	commands = new HashMap<String,Command>();
		commands.put(DUMMY_COMMAND, new DummyCommand());
		commands.put(SEARCHTERMS_COMMAND, new SearchTermsCommand());
    }
    
    private void embedAttributes(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(TITLE_ATTRIBUTE, Application.getSharedInstance().getTitle());
		request.setAttribute(CONTEXTPATH_ATTRIBUTE, this.getServletContext().getContextPath());
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath;
		String fullURI;
		String URI;
		
		// Extract URI
		contextPath = request.getContextPath();
		fullURI = request.getRequestURI();
		URI = fullURI.substring(contextPath.length());
		
		// Embed default JSP attributes to every page
		embedAttributes(request, response);
		
		// GET Actions
		// Render: Search Page
		if(URI.equalsIgnoreCase("/search")){
			request.getRequestDispatcher("/search.jsp").forward(request,response);
		// Render: Results Page
		} else if(URI.equalsIgnoreCase("/results")){
			commands.get(SEARCHTERMS_COMMAND).execute(request,response);
			request.getRequestDispatcher("/results.jsp").forward(request,response);
		// Render: Cart Page
		} else if(URI.equalsIgnoreCase("/cart")){
			request.getRequestDispatcher("/cart.jsp").forward(request,response);
		// Render: Dummy Page
		} else if(URI.equalsIgnoreCase("/dummy")){
			commands.get(DUMMY_COMMAND).execute(request, response);
			request.getRequestDispatcher("/dummy.jsp").forward(request,response);
		// Default: Redirect to Home Page
		} else {
			response.sendRedirect(contextPath);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String contextPath;
		String fullURI;
		String URI;
		
		// Extract URI
		contextPath = request.getContextPath();
		fullURI = request.getRequestURI();
		URI = fullURI.substring(contextPath.length());
		
		// Embed default JSP attributes to every page
		embedAttributes(request, response);
		
		// POST Actions
		// Remove item(s) from cart
		if(URI.equalsIgnoreCase("/cart/remove")){
			request.getRequestDispatcher("/cart.jsp").forward(request,response);
		// Default: Redirect to Home Page
		} else {
			response.sendRedirect(contextPath);
		}
	}
}
