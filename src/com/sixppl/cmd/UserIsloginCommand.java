package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

public class UserIsloginCommand {
	private UserDAO userDao;
	private SessionDAO sessionDao;
	public UserIsloginCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
		sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
	}
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		

		//String sessionId = request.getSession().getId();
		String sessionId = "5642232";
		if(sessionId == null || sessionId.equals("") )
		{
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "can not find sessionID");
			return;
		}
		int sessionID = Integer.parseInt(sessionId);
		SessionDTO session = new SessionDTO();
		session.setSessionID(sessionID);
	
		System.out.println("the session id is"+session.getSessionID()+"the userID is"+session.getUserID());
		int exist = sessionDao.finduserIDbySession(session);
		if (exist < 0){
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "can not find sessionID");
			return;
		}
		request.setAttribute("success", true);
	}
}
