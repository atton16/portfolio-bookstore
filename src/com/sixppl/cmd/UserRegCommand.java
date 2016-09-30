package com.sixppl.cmd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.mindrot.jbcrypt.EmailSending;

import com.sixppl.dao.DummyDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

public class UserRegCommand implements Command {
	private UserDAO userDao;

	public static final int MYSQL_DUPLICATE= 1062;
	public UserRegCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
	}

	@SuppressWarnings("null")
	@Override
	/* This method is for user registration */
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDTO user = new UserDTO();
		String username, password, email, addr, cardno;
		EmailSending emailSending = new EmailSending();

		request.setAttribute("username", request.getParameter("username"));
		request.setAttribute("password", request.getParameter("password"));
		request.setAttribute("nickname", request.getParameter("nickname"));
		request.setAttribute("firstname", request.getParameter("firstname"));
		request.setAttribute("lastname", request.getParameter("lastname"));
		request.setAttribute("email", request.getParameter("email"));
		request.setAttribute("yob", request.getParameter("yob"));
		request.setAttribute("address", request.getParameter("address"));
		request.setAttribute("ccn", request.getParameter("ccn"));
		
		
		user.setUsername(request.getParameter("username")); 

		System.out.println(request.getParameter("username"));
		user.setPassword(request.getParameter("password")); 

		
		password = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt());
		user.setPassword(password);
	
		user.setNickname(request.getParameter("nickname")); 
		user.setFirstname(request.getParameter("firstname")); 

		username = request.getParameter("username");
		password = request.getParameter("password");
		email = request.getParameter("email");
		addr = request.getParameter("address");
		cardno = request.getParameter("ccn");
		if (username == null || username.equals("") || email == null || email.equals("")
				|| password == null || password.equals("") || addr == null
				|| addr.equals("") ||cardno == null
						|| cardno.equals("") ) {
			request.setAttribute("error", true);
			request.setAttribute("error_msg",
					"The username or email or password or addr or cardno should not be null.");
			System.out.println("The username or email or password should not be null.");
			return;
		} 
			//user = userDao.findUserByName(request.getParameter("username"));
		if (userDao.findUserByName(request.getParameter("username")) != null) {
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "The username has been used.");
			System.out.println("The username has been used");
			return;
		} 
		if (userDao.findUserByEmail(request.getParameter("email")) != null) {
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "The email has been used.");
			System.out.println("The email has been used");
			return;
		} 
		user.setUsername(username);
		password = BCrypt.hashpw(request.getParameter("password"),
				BCrypt.gensalt());
		user.setPassword(password);
		user.setNickname(request.getParameter("nickname"));
		user.setFirstname(request.getParameter("firstname"));
		user.setLastname(request.getParameter("lastname"));
		user.setEmail(request.getParameter("email"));
		user.setBirthyear(Integer.parseInt(request.getParameter("yob")));
		user.setAddr(request.getParameter("address"));
		user.setCardno(request.getParameter("ccn"));
		String token = UUID.randomUUID().toString();
		System.out.println("the uuid is" + token);
		user.setTokenstring(token);
		userDao.addUser(user);
		
		String to = request.getParameter("email");
		String from = "asst2unsw@gmail.com";

		String contextPath = request.getContextPath();
		String fullURI = request.getRequestURI();
		String URI = fullURI.substring(contextPath.length());
		String full_path = request.getRequestURL().substring(0, request.getRequestURL().indexOf(URI));
		
		System.out.println("the full path is"+full_path);
		emailSending.sendEmail(to, from, full_path + "/signup/confirm?token="+token);
		request.setAttribute("email", to);
		request.setAttribute("error", false);
	

	}

}
