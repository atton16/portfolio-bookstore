package com.sixppl.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.*;

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

		"/rest/admin/users/ban",
		"/rest/admin/users/unban",
		
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
		"/admin/login",
		"/admin/pub/manage",
		"/admin/pub/find",
		"/admin/pub/remove",
		"/admin/users/manage",
		"/admin/users/viewcustomer",
		"/admin/analytics",
		
		"/graph"
		}, loadOnStartup = 0)
public class Asst2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String TITLE_ATTRIBUTE = "title";
	private static final String CONTEXTPATH_ATTRIBUTE = "contextPath";
	private static final String DUMMY_COMMAND = "dummyCommand";
	private static final String SEARCHTERMS_COMMAND = "searchTermsCommand";
	private static final String CARTVIEW_COMMAND = "cartViewCommand";
	private static final String CARTADD_COMMAND = "cartAddCommand";
	private static final String CARTREMOVE_COMMAND = "cartRemoveCommand";
	private static final String USERLOGIN_COMMAND = "userLoginCommand";
	private static final String SEARCH_COMMAND = "searchCommand";
	private static final String SELL_COMMAND = "sellCommand";
	private static final String LIST_COMMAND = "listCommand";
	private static final String UNLIST_COMMAND = "unlistCommand";
	private static final String ADMINGETPUB_COMMAND = "adminGetPubCommand";
	
	Map<String,Command> commands;

    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	Application.getSharedInstance().init(this.getServletContext());
    	
    	commands = new HashMap<String,Command>();
		commands.put(DUMMY_COMMAND, new DummyCommand());
		commands.put(SEARCHTERMS_COMMAND, new SearchTermsCommand());
		commands.put(CARTVIEW_COMMAND, new CartViewCommand());
		commands.put(CARTADD_COMMAND, new CartAddCommand());
		commands.put(CARTREMOVE_COMMAND, new CartRemoveCommand());
		commands.put(USERLOGIN_COMMAND, new UserLoginCommand());
		commands.put(SEARCH_COMMAND, new SearchCommand());
		commands.put(SELL_COMMAND, new SellCommand());
		commands.put(LIST_COMMAND, new ListCommand());
		commands.put(UNLIST_COMMAND, new UnlistCommand());
		commands.put(ADMINGETPUB_COMMAND, new AdminGetPubCommand());
    }
    
    public void destroy() {
    	Application.getSharedInstance().destroy();
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
			commands.get(SEARCH_COMMAND).execute(request, response);
			//TODO: Do the search
			request.getRequestDispatcher("/results.jsp").forward(request,response);
		// Render: Cart Page
		} else if(URI.equalsIgnoreCase("/cart")){
			//TODO: Get cart
			commands.get(CARTVIEW_COMMAND).execute(request, response);
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
		// Admin: Login Page
		} else if(URI.equalsIgnoreCase("/admin/login")){
			request.getRequestDispatcher("/admin_login.jsp").forward(request,response);
		// Admin: Manage Publications
		} else if(URI.equalsIgnoreCase("/admin/pub/manage")){
			request.getRequestDispatcher("/admin_pub_manage.jsp").forward(request,response);
		// Admin: Manage Publications - Search
		} else if(URI.equalsIgnoreCase("/admin/pub/find")){
			String cmd = request.getParameter("cmd");
			String Pubid = request.getParameter("id");
			//response.getWriter().write("hello");
			commands.get(ADMINGETPUB_COMMAND).execute(request, response);
			StringWriter out = new StringWriter();
			JSONArray temp =(JSONArray) request.getAttribute("jsonreturn");
			JSONValue.writeJSONString(temp, out);
			PrintWriter outPrintWriter = response.getWriter();
			outPrintWriter.write(temp.toString());
			
			
			/*
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
					+ "}"); // TODO: Response in JSON Format*/
	    	response.getWriter().flush();
	    	response.getWriter().close();
    	// Admin: Manage Users
		} else if (URI.equalsIgnoreCase("/admin/users/manage")){
			//TOTO: Admin: Manage Users - Search
			request.getRequestDispatcher("/admin_users_manage.jsp").forward(request,response);
    	// Admin: Customer Activity
		} else if (URI.equalsIgnoreCase("/admin/users/viewcustomer")){
			//TOTO: Admin: Customer Activity
			request.getRequestDispatcher("/admin_customer.jsp").forward(request,response);
    	// Admin: Analytics
		} else if(URI.equalsIgnoreCase("/admin/analytics")){
			request.getRequestDispatcher("/admin_analytics.jsp").forward(request,response);
    	// Graph Page
		} else if(URI.equalsIgnoreCase("/graph")){
			commands.get(SEARCHTERMS_COMMAND).execute(request,response);
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
			commands.get(CARTREMOVE_COMMAND).execute(request, response);
			request.getRequestDispatcher("/cart").forward(request,response);
		// Add item to cart
		} else if(URI.equalsIgnoreCase("/rest/cart/add")){
			//TODO: Add item to cart
			commands.get(CARTADD_COMMAND).execute(request, response);
			
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
			commands.get(USERLOGIN_COMMAND).execute(request, response);
			if((Boolean) request.getAttribute("success"))
				response.sendRedirect(contextPath);
			else
				request.getRequestDispatcher("/login.jsp").forward(request,response);
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
			commands.get(SELL_COMMAND).execute(request,response);
			request.getRequestDispatcher("/sell.jsp").forward(request,response);
		// Set Listing
		} else if(URI.equalsIgnoreCase("/rest/user/pub/list")){
			//TODO: Set Listing
			System.out.println("List:"+request.getParameter("id"));	//TODO: remove this
			commands.get(LIST_COMMAND).execute(request, response);
	    	response.setStatus(HttpServletResponse.SC_OK);	//200
	    	//response.setStatus(HttpServletResponse.SC_ACCEPTED);	//202
		// Set Unlist
		} else if(URI.equalsIgnoreCase("/rest/user/pub/unlist")){
			//TODO: Set Unlist
			System.out.println("Unlist:"+request.getParameter("id"));	//TODO: remove this
			commands.get(UNLIST_COMMAND).execute(request, response);
	    	response.setStatus(HttpServletResponse.SC_OK);	//200
	    	//response.setStatus(HttpServletResponse.SC_ACCEPTED);	//202
		// Admin: Login
		} else if(URI.equalsIgnoreCase("/admin/login")){
			response.sendRedirect(contextPath+"/admin");
    	// Admin: Manage Publications - Remove
		} else if(URI.equalsIgnoreCase("/admin/pub/remove")){
			//TODO: Admin: Manage Publications - Remove
			System.out.println("Remove:"+request.getParameter("id"));	//TODO: remove this
	    	response.setStatus(HttpServletResponse.SC_OK);	//200
	    	//response.setStatus(HttpServletResponse.SC_ACCEPTED);	//202
		// Admin: Manage Users - Ban
		} else if(URI.equalsIgnoreCase("/rest/admin/users/ban")){
			//TODO: Admin: Manage Users - Ban
			System.out.println("Ban:"+request.getParameter("id"));	//TODO: remove this
	    	response.setStatus(HttpServletResponse.SC_OK);	//200
	    	//response.setStatus(HttpServletResponse.SC_ACCEPTED);	//202
		// Admin: Manage Users - Unban
		} else if(URI.equalsIgnoreCase("/rest/admin/users/unban")){
			//TODO: Admin: Manage Users - Unban
			System.out.println("Unban:"+request.getParameter("id"));	//TODO: remove this
	    	response.setStatus(HttpServletResponse.SC_OK);	//200
	    	//response.setStatus(HttpServletResponse.SC_ACCEPTED);	//202
		// Default: Redirect to Home Page
		} else {
			response.sendRedirect(contextPath);
		}
	}
}
