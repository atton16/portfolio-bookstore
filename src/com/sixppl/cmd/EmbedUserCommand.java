package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sixppl.dao.AdminLoginDAO;
import com.sixppl.dao.AdminUserBanDAO;
import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

public class EmbedUserCommand implements Command {
	private SessionDAO sessionDao;
	private UserDAO userDao;
	private AdminUserBanDAO adminUserBanDao;
	private AdminLoginDAO adminLoginDao;
	
	public EmbedUserCommand() {
		sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
		adminUserBanDao = Application.getSharedInstance().getDAOFactory().getAdminUserBanDAO();
		adminLoginDao = Application.getSharedInstance().getDAOFactory().getAdminLoginDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SessionDTO sessionDto = new SessionDTO();
		sessionDto.setSessionID(request.getSession().getId());
		int userid = sessionDao.finduserIDbySession(sessionDto);
		UserDTO userDto = userDao.findUserByUserID(userid);
		if(userDto != null){
			userDto.setIsBanned(adminUserBanDao.isBanned(userDto.getUserID()));
			userDto.setIsAdmin(adminLoginDao.isAdmin(userDto.getUserID()));
		}
		session.setAttribute("user", userDto);

	}

}
