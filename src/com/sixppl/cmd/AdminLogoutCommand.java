package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.AdminLoginDAO;
import com.sixppl.dao.support.AdminLoginDAOImpl;

public class AdminLogoutCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminLoginDAO adminDao = new AdminLoginDAOImpl();
		String SessionID = request.getSession().getId();
		adminDao.logout(SessionID);
	}

}
