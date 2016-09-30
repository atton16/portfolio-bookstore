package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.AdminUserBanDAO;
import com.sixppl.main.Application;

public class AdminUnbanUserCommand implements Command{
	private AdminUserBanDAO adminUserBanDao;
	
	public AdminUnbanUserCommand() {
		adminUserBanDao = Application.getSharedInstance().getDAOFactory().getAdminUserBanDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Boolean success = adminUserBanDao.unban(id);
		request.setAttribute("success",success);
	}

}
