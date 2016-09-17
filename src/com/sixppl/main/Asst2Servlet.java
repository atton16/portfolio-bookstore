package com.sixppl.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.main.Application;

/**
 * Main Servlet
 * @author atton16
 *
 */
@WebServlet(urlPatterns = { "/test" }, loadOnStartup = 0)
public class Asst2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public void init() throws ServletException {
    	Application app;
    	
    	app = Application.getSharedInstance();
    	app.init(getServletContext());
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/home.jsp").forward(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/home.jsp").forward(request,response);
	}
}
