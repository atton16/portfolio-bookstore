package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.UserDAO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;
import com.sixppl.main.support.ApplicationSupport;
import com.sixppl.main.support.EmailSending;

public class UserEmailCommand implements Command {
	private UserDAO userDao;
	public UserEmailCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
		
	}
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");

		System.out.println(request.getParameter("email"));
		if(email == null|| email.equals("") ){
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "Null value for username or password.");
			return;
		}
		
		UserDTO user  = userDao.findUserByNewEmail(email);
		if(user == null){
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "Cannot find username");
			return;
		}
		EmailSending emailSending = new EmailSending();
		String to = email;
		String from = "asst2unsw@gmail.com";
		String token = user.getTokenstring();
		
		String contextPath = request.getContextPath();
		String fullURI = request.getRequestURI();
		String URI = fullURI.substring(contextPath.length());
		String full_path = request.getRequestURL().substring(0, request.getRequestURL().indexOf(URI));
		
		System.out.println("the full path is"+full_path);
		if(token == null) {
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "User is already activated.");
			return;
		}
		emailSending.sendEmail(to, from, ApplicationSupport.RegistrationEmailSubject(), ApplicationSupport.RegistrationEmailContent(full_path + "/signup/confirm?token="+token));
		request.setAttribute("email", to);
		request.setAttribute("error", false);
	}

}
