package com.sixppl.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
@MultipartConfig()
@WebServlet(urlPatterns = {
		"/rest/cart/add",
		
		"/rest/user/pub/list",
		"/rest/user/pub/unlist",
		
		"/search",
		"/results",
		"/pubinfo",
		
		"/cart",
		"/cart/remove",
		"/receipt",
		
		"/login",
		"/logout",
		"/signup",
		"/signup/resend",
		"/signup/confirm",
		"/user/profile",
		"/user/sell",
		"/user/pub/manage",
		
		"/admin",
		"/admin/pub/manage",
		"/admin/pub/find",
		"/admin/pub/remove",
		
		"/graph"
		}, loadOnStartup = 0)
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
			//TODO: Do the search
			request.getRequestDispatcher("/results.jsp").forward(request,response);
		// Render: Cart Page
		} else if(URI.equalsIgnoreCase("/cart")){
			//TODO: Get cart
			request.getRequestDispatcher("/cart.jsp").forward(request,response);
		// Render: Receipt Page
		} else if(URI.equalsIgnoreCase("/receipt")){
			request.getRequestDispatcher("/receipt.jsp").forward(request,response);
		// Render: Publication Details
		} else if(URI.equalsIgnoreCase("/pubinfo")){
			//TODO: Get publication info
			request.getRequestDispatcher("/pubinfo.jsp").forward(request,response);
		// Render: Login Page
		} else if(URI.equalsIgnoreCase("/login")){
			request.getRequestDispatcher("/login.jsp").forward(request,response);
		// Logout
		} else if(URI.equalsIgnoreCase("/logout")){
			//TODO: Do logout
			request.getRequestDispatcher("/home.jsp").forward(request,response);
		// Render: Registration Page
		} else if(URI.equalsIgnoreCase("/signup")){
			request.getRequestDispatcher("/signup.jsp").forward(request,response);
		// Confirm Email
		} else if(URI.equalsIgnoreCase("/signup/confirm")){
			request.setAttribute("error", false);	//TODO: remove this
			request.setAttribute("email", "xx");	//TODO: remove this
			request.getRequestDispatcher("/signup_confirm.jsp").forward(request,response);
		// Edit Profile Page
		} else if(URI.equalsIgnoreCase("/user/profile")){
			request.getRequestDispatcher("/profile.jsp").forward(request,response);
		// Sell Page
		} else if(URI.equalsIgnoreCase("/user/sell")){
			request.getRequestDispatcher("/sell.jsp").forward(request,response);
		// Manage Publications Page
		} else if(URI.equalsIgnoreCase("/user/pub/manage")){
			request.getRequestDispatcher("/pub_manage.jsp").forward(request,response);
		// Admin Dashboard Page
		} else if(URI.equalsIgnoreCase("/admin")){
			request.getRequestDispatcher("/admin.jsp").forward(request,response);
		// Admin: Manage Publications
		} else if(URI.equalsIgnoreCase("/admin/pub/manage")){
			request.getRequestDispatcher("/admin_pub_manage.jsp").forward(request,response);
		// Admin: Manage Publications - Search
		} else if(URI.equalsIgnoreCase("/admin/pub/find")){
			//TODO: Admin: Manage Publications - Search
	    	response.setStatus(HttpServletResponse.SC_OK);	//200
	    	//response.setStatus(HttpServletResponse.SC_NO_CONTENT);	//204
	    	response.getWriter().write("{"
	    			+ "\"id\":\"pubid1\","
	    			+ "\"title\":\"1984\","
	    			+ "\"authors\":[\"George Orwell\"],"
	    			+ "\"editors\": [],"
	    			+ "\"picurl\": \""+contextPath+"/uploads/1984.jpeg\","
					+ "\"price\": 280.00,"
					+ "\"seller\": \"Hale\","
					+ "\"listed\": \"DD/MM/YY\""
					+ "}"); // TODO: Response in JSON Format
	    	response.getWriter().flush();
	    	response.getWriter().close();
    	// Graph Page
		} else if(URI.equalsIgnoreCase("/graph")){
			request.getRequestDispatcher("/graph.jsp").forward(request,response);
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
			//TODO: Remove from cart
			request.getRequestDispatcher("/cart.jsp").forward(request,response);
		// Add item to cart
		} else if(URI.equalsIgnoreCase("/rest/cart/add")){
			//TODO: Add item to cart
	    	response.setStatus(HttpServletResponse.SC_OK);
	    	response.getWriter().write(String.valueOf(new java.util.Random().nextInt(2))); // TODO: Return number of item in the cart
	    	response.getWriter().flush();
	    	response.getWriter().close();
		// Checkout
		} else if(URI.equalsIgnoreCase("/receipt")){
			//TODO: Checkout
			request.getRequestDispatcher("/receipt.jsp").forward(request,response);
		// Login
		} else if(URI.equalsIgnoreCase("/login")){
			//TODO: Login
			request.getRequestDispatcher("/home.jsp").forward(request,response);
		// Register
		} else if(URI.equalsIgnoreCase("/signup")){
			//TODO: Register
			request.setAttribute("error", false);	//TODO: remove this
			request.setAttribute("email", "a@a.com");	//TODO: remove this
			request.getRequestDispatcher("/signup.jsp").forward(request,response);
		// Resend Confirmation Email
		} else if(URI.equalsIgnoreCase("/signup/resend")){
			//TODO: Resend Confirmation Email
			request.setAttribute("error", false);	//TODO: remove this
			request.setAttribute("email", "a@a.com");	//TODO: remove this
			request.getRequestDispatcher("/signup.jsp").forward(request,response);
		// Edit Profile
		} else if(URI.equalsIgnoreCase("/user/profile")){
			//TODO: Edit Profile
			request.getRequestDispatcher("/profile.jsp").forward(request,response);
		// Sell
		} else if(URI.equalsIgnoreCase("/user/sell")){
			//TODO: Sell
			System.out.println(request.getParameter("title"));	//TODO: remove this
			System.out.println(request.getParameter("pubtype"));	//TODO: remove this
			System.out.println(request.getPart("pic"));	//TODO: remove this
			request.getRequestDispatcher("/sell.jsp").forward(request,response);
		// Set Listing
		} else if(URI.equalsIgnoreCase("/rest/user/pub/list")){
			//TODO: Set Listing
			System.out.println("List:"+request.getParameter("id"));	//TODO: remove this
	    	response.setStatus(HttpServletResponse.SC_OK);	//200
	    	//response.setStatus(HttpServletResponse.SC_ACCEPTED);	//202
		// Set Unlist
		} else if(URI.equalsIgnoreCase("/rest/user/pub/unlist")){
			//TODO: Set Unlist
			System.out.println("Unlist:"+request.getParameter("id"));	//TODO: remove this
	    	response.setStatus(HttpServletResponse.SC_OK);	//200
	    	//response.setStatus(HttpServletResponse.SC_ACCEPTED);	//202
		// Admin: Manage Publications - Remove
		} else if(URI.equalsIgnoreCase("/admin/pub/remove")){
			System.out.println("Remove:"+request.getParameter("id"));	//TODO: remove this
	    	response.setStatus(HttpServletResponse.SC_OK);	//200
	    	//response.setStatus(HttpServletResponse.SC_ACCEPTED);	//202
		// Default: Redirect to Home Page
		} else {
			response.sendRedirect(contextPath);
		}
	}
}
