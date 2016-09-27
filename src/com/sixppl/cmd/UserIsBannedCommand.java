package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.AdminUserBanDAO;
import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.support.AdminUserBanDAOImpl;
import com.sixppl.dto.SessionDTO;
import com.sixppl.main.Application;

public class UserIsBannedCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserBanDAO userban = new AdminUserBanDAOImpl();
		SessionDAO sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
		SessionDTO session = new SessionDTO();
		
		String sessionId = request.getSession().getId();
		if(sessionId == null || sessionId.equals("") )
		{
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "can not find sessionID");
			return;
		}
		
		session.setSessionID(sessionId);
		Integer id = sessionDao.finduserIDbySession(session);
		request.setAttribute("banned", userban.checkBan(id));
	}

}
