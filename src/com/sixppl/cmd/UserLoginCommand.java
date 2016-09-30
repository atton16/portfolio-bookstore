package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

public class UserLoginCommand implements Command {
	private UserDAO userDao;
	private SessionDAO sessionDao;
	public UserLoginCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
		sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 HttpSession session = request.getSession(true);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));
		if(username == null|| username.equals("") ||password == null|| password.equals("")){
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "Null value for username or password.");
			return;
		}
		
		UserDTO user  = userDao.findUserByName(username);
		if(user == null){
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "Cannot find username");
			return;
		}
		if(!BCrypt.checkpw(password, user.getPassword()))
		{
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "Login Failed.");
			return;
		}
		String sessionId = request.getSession().getId();
		if(sessionId == null || sessionId.equals("") )
		{
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "can not find sessionID");
			return;
		}
		
		SessionDTO sess = new SessionDTO();
		sess.setSessionID(sessionId);
		sess.setUserID(user.getUserID());
		System.out.println("the session id is"+sess.getSessionID()+"the userID is"+sess.getUserID());
		sessionDao.addSession(sess);
		session.setAttribute("user", user);
		request.setAttribute("success", true);
	}

}
