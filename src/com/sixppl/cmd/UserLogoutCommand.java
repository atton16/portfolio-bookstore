package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.SessionDAO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.main.Application;

public class UserLogoutCommand implements Command {
	private SessionDAO sessionDao;
	public UserLogoutCommand() {
		
		sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
	}
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sessionId = request.getSession().getId();
		if(sessionId == null || sessionId.equals("") )
		{
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "can not find sessionID");
			return;
		}
		
		SessionDTO session = new SessionDTO();
		session.setSessionID(sessionId);
		session.setUserID(sessionDao.finduserIDbySession(session));
		System.out.println("the session id is"+session.getSessionID()+"the userID is"+session.getUserID());
		sessionDao.delSession(session);
		request.getSession().removeAttribute("user");
		request.setAttribute("success", true);
	}

}
