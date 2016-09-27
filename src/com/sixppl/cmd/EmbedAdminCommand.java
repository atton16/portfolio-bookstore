package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

public class EmbedAdminCommand implements Command {
	private SessionDAO sessionDao;
	private UserDAO userDao;
	
	public EmbedAdminCommand() {
		sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SessionDTO sessionDto = new SessionDTO();
		sessionDto.setSessionID(request.getSession().getId());
		int userid = sessionDao.findAdminUserIDbySession(sessionDto);
		UserDTO userDto = userDao.findUserByUserID(userid);
		session.setAttribute("admin", userDto);

	}

}
