package com.sixppl.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.*;

import com.sixppl.cmd.*;
import com.sixppl.dto.ListingDTO;
import com.sixppl.dto.UserDTO;
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
		
		"/ban",
		"/login",
		"/logout",
		"/signup",
		"/signup/resend",
		"/signup/confirm",
		"/user/profile",
		"/user/profile/verify",
		"/user/profile/confirm",
		"/user/sell",
		"/user/pub/manage",

		"/admin",
		"/admin/pubinfo",
		"/admin/login",
		"/admin/logout",
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
	
	public enum COMMAND {
	    SEARCHTERMS,
	    
	    CART_VIEW,
	    CART_ADD,
	    CART_REMOVE,
	    
	    USER_LOGIN,
	    USER_LOGOUT,
	    USER_REG,
	    USER_PROFILE,
	    USER_EMAIL,
	    USER_CONFIRM,
	    USER_VIEW_PROFILE,
	    USER_EMAIL_CHANGE,
	    
	    SEARCH,
	    SEARCH_ALL,
	    SELL,
	    LIST,
	    UNLIST,

	    ADMIN_LOGIN,
	    ADMIN_LOGOUT,
	    
	    ADMIN_GET_PUB,
	    ADMIN_REMOVE_PUB,
	    
	    ADMIN_GET_USER,
	    ADMIN_BAN_USER,
	    ADMIN_UNBAN_USER,
	    ADMIN_GET_USER_ACTIVITY,
	    
	    ADMIN_GET_PAGE_HIT,
	    
	    SEARCH_GRAPH,
	    
	    YEAR_LIST,
	    USER_IS_BANNED, 
	    CHECKOUT,
	    PUB_DETAIL,
	    MANAGE_PUB,
	    UNIQUE_IP,
	    REGISTERED_USERS,
	    ACTIVE_LOGINS,
	    ANALYTICS_MOST_ADDED_TO_CART,
	    ANALYTICS_MOST_VIEWED
	}
	
	Map<COMMAND,Command> commands;

    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	Application.getSharedInstance().init(this.getServletContext());
    	
    	commands = new HashMap<COMMAND,Command>();
		
		commands.put(COMMAND.SEARCHTERMS, new SearchTermsCommand());
		
		commands.put(COMMAND.CART_VIEW, new CartViewCommand());
		commands.put(COMMAND.CART_ADD, new CartAddCommand());
		commands.put(COMMAND.CART_REMOVE, new CartRemoveCommand());
		
		commands.put(COMMAND.USER_LOGIN, new UserLoginCommand());
		commands.put(COMMAND.USER_LOGOUT, new UserLogoutCommand());
		commands.put(COMMAND.USER_REG, new UserRegCommand());
		commands.put(COMMAND.USER_PROFILE, new UserProfileCommand());
		commands.put(COMMAND.USER_EMAIL, new UserEmailCommand());
		commands.put(COMMAND.USER_CONFIRM, new UserConfirmCommand());
		commands.put(COMMAND.USER_VIEW_PROFILE, new UserViewProfileCommand());
		commands.put(COMMAND.USER_EMAIL_CHANGE, new UserEmailChangeCommand());
		commands.put(COMMAND.USER_IS_BANNED, new UserIsBannedCommand());

		commands.put(COMMAND.SEARCH, new SearchCommand());
		commands.put(COMMAND.SEARCH_ALL, new SearchAllCommand());
		commands.put(COMMAND.SELL, new SellCommand());
		commands.put(COMMAND.LIST, new ListCommand());
		commands.put(COMMAND.UNLIST, new UnlistCommand());
		
		commands.put(COMMAND.ADMIN_GET_PUB, new AdminGetPubCommand());
		commands.put(COMMAND.ADMIN_REMOVE_PUB, new AdminRemovePubCommand());
		commands.put(COMMAND.ADMIN_GET_USER, new AdminGetUserCommand());
		commands.put(COMMAND.ADMIN_BAN_USER, new AdminBanUserCommand());
		commands.put(COMMAND.ADMIN_UNBAN_USER, new AdminUnbanUserCommand());
		commands.put(COMMAND.ADMIN_GET_USER_ACTIVITY, new AdminGetUserActivityCommand());
		commands.put(COMMAND.ADMIN_LOGIN, new AdminLoginCommand());
		commands.put(COMMAND.SEARCH_GRAPH, new SearchGraphCommand());
		commands.put(COMMAND.ADMIN_GET_PAGE_HIT, new AdminGetPageHitCommand());
		commands.put(COMMAND.ADMIN_LOGOUT, new AdminLogoutCommand());
		
		commands.put(COMMAND.CHECKOUT, new CheckoutCommand());
		commands.put(COMMAND.MANAGE_PUB, new ManagePubCommand());
		commands.put(COMMAND.PUB_DETAIL, new PubDetailCommand());

		commands.put(COMMAND.YEAR_LIST, new YearListCommand());
		commands.put(COMMAND.UNIQUE_IP, new UniqueIPCommand());
		commands.put(COMMAND.REGISTERED_USERS, new RegisteredUsersCommand());
		commands.put(COMMAND.ACTIVE_LOGINS, new ActiveLoginsCommand());
		commands.put(COMMAND.ANALYTICS_MOST_ADDED_TO_CART, new AnalyticsMostAddedToCartCommand());
		commands.put(COMMAND.ANALYTICS_MOST_VIEWED, new AnalyticsMostViewedCommand());
		

    }
    
    public void destroy() {
    	Application.getSharedInstance().destroy();
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
		Application.getSharedInstance().embedDefaults(request, response);
		
		HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		UserDTO admin = (UserDTO) session.getAttribute("admin");
		// Ban redirection
		if(user != null && admin == null && !URI.equalsIgnoreCase("/ban") && !URI.equalsIgnoreCase("/logout")) {
			if(user.isBanned()){
				response.sendRedirect(contextPath+"/ban");
				return;
			}
		}
		
		// GET Actions
		// Render: Search Page
		if(URI.equalsIgnoreCase("/search")){
			Application.getSharedInstance().incrementPageHitsCount("Advanced Search");
			commands.get(COMMAND.YEAR_LIST).execute(request,response);
			request.getRequestDispatcher("/search.jsp").forward(request,response);
		// Render: Results Page
		} else if(URI.equalsIgnoreCase("/results")){
			Application.getSharedInstance().incrementPageHitsCount("Search Results");
			commands.get(COMMAND.SEARCHTERMS).execute(request,response);
			commands.get(COMMAND.SEARCH).execute(request, response);
			request.getRequestDispatcher("/results.jsp").forward(request,response);
		// Render: Cart Page
		} else if(URI.equalsIgnoreCase("/cart")){
			if(user == null) {
				response.sendRedirect(contextPath+"/login");
			} else {
				Application.getSharedInstance().incrementPageHitsCount("Shopping Cart");
				commands.get(COMMAND.CART_VIEW).execute(request, response);
				request.getRequestDispatcher("/cart.jsp").forward(request,response);
			}
		// Render: Receipt Page
		} else if(URI.equalsIgnoreCase("/receipt")){
			if(user == null) {
				response.sendRedirect(contextPath+"/login");
			} else {
				Application.getSharedInstance().incrementPageHitsCount("Receipt");
				request.setAttribute("items", new ArrayList<String>());
				request.getRequestDispatcher("/receipt.jsp").forward(request,response);
			}
		// Render: Publication Details
		} else if(URI.equalsIgnoreCase("/pubinfo")){
			commands.get(COMMAND.PUB_DETAIL).execute(request, response);
			ListingDTO item = (ListingDTO) request.getAttribute("item");
			if(item != null) {
				if(item.getStatus() != true) {
					if(user != null) {
						if(user.getUserID() != item.getSellerID()){
							request.setAttribute("item", null);
						}
					} else {
						request.setAttribute("item", null);
					}
				}
			}
			Application.getSharedInstance().incrementPageHitsCount("Publication Details");
			request.getRequestDispatcher("/pubinfo.jsp").forward(request,response);
		// Render: Admin - Publication Details
		} else if(URI.equalsIgnoreCase("/admin/pubinfo")){
			if(admin == null) {
				response.sendRedirect(contextPath);
			} else {
				commands.get(COMMAND.PUB_DETAIL).execute(request, response);
				request.getRequestDispatcher("/admin_pubinfo.jsp").forward(request,response);
			}
		// Render: Ban Page
		} else if(URI.equalsIgnoreCase("/ban")){
			Application.getSharedInstance().incrementPageHitsCount("Ban");
			commands.get(COMMAND.USER_IS_BANNED).execute(request, response);
			if((Boolean) request.getAttribute("banned") == true)
				request.getRequestDispatcher("/ban_notice.jsp").forward(request,response);
			else
				response.sendRedirect(contextPath);
		// Render: Login Page
		} else if(URI.equalsIgnoreCase("/login")){
			if(user != null) {
				response.sendRedirect(contextPath+"/user/profile");
			} else {
				Application.getSharedInstance().incrementPageHitsCount("Login");
				request.getRequestDispatcher("/login.jsp").forward(request,response);
			}
		// Logout
		} else if(URI.equalsIgnoreCase("/logout")){
			commands.get(COMMAND.USER_LOGOUT).execute(request, response);
			response.sendRedirect(contextPath);
		// Render: Registration Page
		} else if(URI.equalsIgnoreCase("/signup")){
			if(user != null) {
				response.sendRedirect(contextPath+"/user/profile");
			} else {
				Application.getSharedInstance().incrementPageHitsCount("Registration");
				request.getRequestDispatcher("/signup.jsp").forward(request,response);
			}
		// Confirm Email
		} else if(URI.equalsIgnoreCase("/signup/confirm")){
			if(user != null) {
				response.sendRedirect(contextPath+"/user/profile");
			} else {
				Application.getSharedInstance().incrementPageHitsCount("Confirm Email");
				commands.get(COMMAND.USER_CONFIRM).execute(request, response);
				request.getRequestDispatcher("/signup_confirm.jsp").forward(request,response);
			}
		// Edit Profile Page
		} else if(URI.equalsIgnoreCase("/user/profile")){
			if(user == null) {
				response.sendRedirect(contextPath+"/login");
			} else {
				Application.getSharedInstance().incrementPageHitsCount("Edit Profile");
				commands.get(COMMAND.USER_VIEW_PROFILE).execute(request,response);
				request.getRequestDispatcher("/profile.jsp").forward(request,response);
			}
		// Resend Verification Email
		} else if(URI.equalsIgnoreCase("/user/profile/verify")){
			if(user == null) {
				response.sendRedirect(contextPath+"/login");
			} else {
				Application.getSharedInstance().incrementPageHitsCount("Resend Verification Email");
				commands.get(COMMAND.USER_EMAIL_CHANGE).execute(request, response);
				request.getRequestDispatcher("/profile_verify.jsp").forward(request,response);
			}
		// Confirm New Email
		} else if(URI.equalsIgnoreCase("/user/profile/confirm")){
			if(user == null) {
				response.sendRedirect(contextPath+"/login");
			} else {
				Application.getSharedInstance().incrementPageHitsCount("Confirm New Email");
				commands.get(COMMAND.USER_CONFIRM).execute(request, response);
				request.getRequestDispatcher("/profile_confirm.jsp").forward(request,response);
			}
		// Sell Page
		} else if(URI.equalsIgnoreCase("/user/sell")){
			Application.getSharedInstance().incrementPageHitsCount("Sell");
			request.setAttribute("error", false);
			request.setAttribute("error_msg", null);
			request.getRequestDispatcher("/sell.jsp").forward(request,response);
		// Manage Publications Page
		} else if(URI.equalsIgnoreCase("/user/pub/manage")){
			if(user == null) {
				response.sendRedirect(contextPath+"/login");
			} else {
				Application.getSharedInstance().incrementPageHitsCount("Manage Publications");
				commands.get(COMMAND.MANAGE_PUB).execute(request, response);
				request.getRequestDispatcher("/pub_manage.jsp").forward(request,response);
			}
		// Admin Dashboard Page
		} else if(URI.equalsIgnoreCase("/admin")){
			if(admin == null) {
				response.sendRedirect(contextPath);
			} else {
				request.getRequestDispatcher("/admin.jsp").forward(request,response);
			}
		// Admin: Login Page
		} else if(URI.equalsIgnoreCase("/admin/login")){
			if(admin != null) {
				response.sendRedirect(contextPath+"/admin");
			} else {
				request.getRequestDispatcher("/admin_login.jsp").forward(request,response);
			}
		// Admin: Logout Page
		} else if(URI.equalsIgnoreCase("/admin/logout")){
			if(admin == null) {
				response.sendRedirect(contextPath);
			} else {
				commands.get(COMMAND.ADMIN_LOGOUT).execute(request, response);
				response.sendRedirect(contextPath+"/admin/login");
			}
		// Admin: Manage Publications
		} else if(URI.equalsIgnoreCase("/admin/pub/manage")){
			if(admin == null) {
				response.sendRedirect(contextPath);
			} else {
				commands.get(COMMAND.SEARCH_ALL).execute(request, response);
				commands.get(COMMAND.SEARCHTERMS).execute(request, response);
				request.getRequestDispatcher("/admin_pub_manage.jsp").forward(request,response);
			}
			
		// Admin: Manage Publications - Search
		} else if(URI.equalsIgnoreCase("/admin/pub/find")){
			if(admin == null) {
				response.sendRedirect(contextPath);
			} else {
				commands.get(COMMAND.ADMIN_GET_PUB).execute(request, response);
				
				if((Boolean)request.getAttribute("error") == true){
			    	response.setStatus(HttpServletResponse.SC_NO_CONTENT);	//204
				} else {
					JSONArray temp = (JSONArray) request.getAttribute("jsonreturn");
					String escapedContextpath = contextPath;
					String last = "";
					while (!escapedContextpath.equals(last)) {
						last = escapedContextpath;
						escapedContextpath = contextPath.replace("/", "\\/");
					}
					if(temp.size() > 0){
				    	response.setStatus(HttpServletResponse.SC_OK);	//200
						System.out.println(temp.get(0).toString().replace("\"picurl\":\"", "\"picurl\":\""+escapedContextpath));
						response.getWriter().write(temp.get(0).toString().replace("\"picurl\":\"", "\"picurl\":\""+escapedContextpath));
				    	response.getWriter().flush();
				    	response.getWriter().close();
					} else {
				    	response.setStatus(HttpServletResponse.SC_NO_CONTENT);	//204
					}
				}
			}
    	// Admin: Manage Users
		} else if (URI.equalsIgnoreCase("/admin/users/manage")){
			if(admin == null) {
				response.sendRedirect(contextPath);
			} else {
				commands.get(COMMAND.SEARCHTERMS).execute(request,response);
				commands.get(COMMAND.ADMIN_GET_USER).execute(request, response);
				request.getRequestDispatcher("/admin_users_manage.jsp").forward(request,response);
			}
    	// Admin: Customer Activity
		} else if (URI.equalsIgnoreCase("/admin/users/viewcustomer")){
			if(admin == null) {
				response.sendRedirect(contextPath);
			} else {
				commands.get(COMMAND.ADMIN_GET_USER_ACTIVITY).execute(request, response);
				request.getRequestDispatcher("/admin_customer.jsp").forward(request,response);
			}
    	// Admin: Analytics
		} else if(URI.equalsIgnoreCase("/admin/analytics")){
			if(admin == null) {
				response.sendRedirect(contextPath);
			} else {
				commands.get(COMMAND.ADMIN_GET_PAGE_HIT).execute(request, response);
				commands.get(COMMAND.UNIQUE_IP).execute(request, response);
				commands.get(COMMAND.REGISTERED_USERS).execute(request, response);
				commands.get(COMMAND.ACTIVE_LOGINS).execute(request, response);
				commands.get(COMMAND.ANALYTICS_MOST_ADDED_TO_CART).execute(request, response);
				commands.get(COMMAND.ANALYTICS_MOST_VIEWED).execute(request, response);
				request.getRequestDispatcher("/admin_analytics.jsp").forward(request,response);
			}
    	// Graph Page
		} else if(URI.equalsIgnoreCase("/graph")){
			Application.getSharedInstance().incrementPageHitsCount("Graph");
            commands.get(COMMAND.SEARCHTERMS).execute(request,response);
			commands.get(COMMAND.SEARCH_GRAPH).execute(request,response);
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
		Application.getSharedInstance().embedDefaults(request, response);

		HttpSession session = request.getSession();
		UserDTO user = (UserDTO) session.getAttribute("user");
		UserDTO admin = (UserDTO) session.getAttribute("admin");
		// Ban redirection
		if(user != null && admin == null && !URI.contains("/rest")) {
			if(user.isBanned()){
				response.sendRedirect(contextPath+"/ban");
				return;
			}
		}
		
		// POST Actions
		// Remove item(s) from cart
		if(URI.equalsIgnoreCase("/cart/remove")){
			if(user == null) {
				response.sendRedirect(contextPath+"/login");
			} else {
				commands.get(COMMAND.CART_REMOVE).execute(request, response);
				response.sendRedirect(contextPath+"/cart");
			}
		// Add item to cart
		} else if(URI.equalsIgnoreCase("/rest/cart/add")){
			response.setStatus(HttpServletResponse.SC_OK);
			if(user == null) {
				response.getWriter().write("Please login!");
			} else if(user != null && admin == null && user.isBanned()) {
				response.getWriter().write("You are banned!");
			} else {
				commands.get(COMMAND.CART_ADD).execute(request, response);
				if(request.getAttribute("error_msg") != null) {
					response.getWriter().write(String.valueOf(request.getAttribute("error_msg")));
				} else {
					response.getWriter().write(String.valueOf(request.getAttribute("cartCount")));
				}
			}
			response.getWriter().flush();
			response.getWriter().close();
		// Checkout
		} else if(URI.equalsIgnoreCase("/receipt")){
			if(user == null) {
				response.sendRedirect(contextPath+"/login");
			} else {
				commands.get(COMMAND.CHECKOUT).execute(request,response);
				request.getRequestDispatcher("/receipt.jsp").forward(request,response);
			}
		// Login
		} else if(URI.equalsIgnoreCase("/login")){
			commands.get(COMMAND.USER_LOGIN).execute(request, response);
			if((Boolean) request.getAttribute("success"))
				response.sendRedirect(contextPath);
			else
				request.getRequestDispatcher("/login.jsp").forward(request,response);
	    // Register
		} else if(URI.equalsIgnoreCase("/signup")){
			if(user != null) {
				response.sendRedirect(contextPath+"/user/profile");
			} else {
				commands.get(COMMAND.USER_REG).execute(request, response);
				request.getRequestDispatcher("/signup.jsp").forward(request,response);
			}
		// Resend Confirmation Email
		} else if(URI.equalsIgnoreCase("/signup/resend")){
			if(user != null) {
				response.sendRedirect(contextPath+"/user/profile");
			} else {
				commands.get(COMMAND.USER_EMAIL).execute(request, response);
				request.getRequestDispatcher("/signup.jsp").forward(request,response);
			}
		// Edit Profile
		} else if(URI.equalsIgnoreCase("/user/profile")){
			if(user == null) {
				response.sendRedirect(contextPath+"/login");
			} else {
				commands.get(COMMAND.USER_PROFILE).execute(request, response);
				request.getRequestDispatcher("/profile.jsp").forward(request,response);
			}
		// Sell
		} else if(URI.equalsIgnoreCase("/user/sell")){
			if(user == null) {
				response.sendRedirect(contextPath+"/login");
			} else {
				commands.get(COMMAND.SELL).execute(request,response);
				request.getRequestDispatcher("/sell.jsp").forward(request,response);
			}
		// Set Listing
		} else if(URI.equalsIgnoreCase("/rest/user/pub/list")){
			if(user == null) {
		    	response.setStatus(HttpServletResponse.SC_ACCEPTED);	//202
			} else {
				System.out.println("List:"+request.getParameter("id"));
				commands.get(COMMAND.LIST).execute(request, response);
				response.setStatus(HttpServletResponse.SC_OK);	//200
			}
		// Set Unlist
		} else if(URI.equalsIgnoreCase("/rest/user/pub/unlist")){
			if(user == null) {
		    	response.setStatus(HttpServletResponse.SC_ACCEPTED);	//202
			} else {
				System.out.println("Unlist:"+request.getParameter("id"));
				commands.get(COMMAND.UNLIST).execute(request, response);
				response.setStatus(HttpServletResponse.SC_OK);	//200
			}
		// Admin: Login
		} else if(URI.equalsIgnoreCase("/admin/login")){
			commands.get(COMMAND.ADMIN_LOGIN).execute(request, response);
			if(request.getAttribute("error_msg") == null)
				response.sendRedirect(contextPath+"/admin");
			else
				request.getRequestDispatcher("/admin_login.jsp").forward(request,response);
    	// Admin: Manage Publications - Remove
		} else if(URI.equalsIgnoreCase("/admin/pub/remove")){
			if(admin == null){
				response.setStatus(HttpServletResponse.SC_ACCEPTED);	//202
			} else {
				commands.get(COMMAND.ADMIN_REMOVE_PUB).execute(request, response);
				System.out.println("Remove:"+request.getParameter("id"));
				if((Boolean)request.getAttribute("error") == true)
					response.setStatus(HttpServletResponse.SC_ACCEPTED);	//202
				else
			    	response.setStatus(HttpServletResponse.SC_OK);			//200
			}
		// Admin: Manage Users - Ban
		} else if(URI.equalsIgnoreCase("/rest/admin/users/ban")){
			if(admin == null){
				response.setStatus(HttpServletResponse.SC_ACCEPTED);	//202
			} else {
				commands.get(COMMAND.ADMIN_BAN_USER).execute(request, response);
		    	if(((Boolean)request.getAttribute("success")))
		    		response.setStatus(HttpServletResponse.SC_OK);
		    	else
		    		response.setStatus(HttpServletResponse.SC_ACCEPTED);
			}
		// Admin: Manage Users - Unban
		} else if(URI.equalsIgnoreCase("/rest/admin/users/unban")){
			if(admin == null){
				response.setStatus(HttpServletResponse.SC_ACCEPTED);	//202
			} else {
				commands.get(COMMAND.ADMIN_UNBAN_USER).execute(request, response);
		    	if(((Boolean)request.getAttribute("success")))
		    		response.setStatus(HttpServletResponse.SC_OK);
		    	else
		    		response.setStatus(HttpServletResponse.SC_ACCEPTED);
			}
		// Default: Redirect to Home Page
		} else {
			response.sendRedirect(contextPath);
		}
	}
}
