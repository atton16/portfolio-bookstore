package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.AdminLoginDAO;
import com.sixppl.main.Application;

public class AdminLogoutCommand implements Command{
	private AdminLoginDAO adminLoginDao;
	
	public AdminLogoutCommand() {
		adminLoginDao = Application.getSharedInstance().getDAOFactory().getAdminLoginDAO();
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String SessionID = request.getSession().getId();
		adminLoginDao.logout(SessionID);
	}

}
