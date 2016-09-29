package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.AdminUserBanDAO;
import com.sixppl.main.Application;

public class AdminBanUserCommand implements Command {
	private AdminUserBanDAO userBanDao;
	
	public AdminBanUserCommand() {
		userBanDao = Application.getSharedInstance().getDAOFactory().getAdminUserBanDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Boolean success = userBanDao.ban(id);
		request.setAttribute("success", success);
	}

}
