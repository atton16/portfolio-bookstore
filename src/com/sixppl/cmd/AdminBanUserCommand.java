package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.AdminUserBanDAO;
import com.sixppl.dao.support.AdminUserBanDAOImpl;

public class AdminBanUserCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserBanDAO userban = new AdminUserBanDAOImpl();
		Integer id = Integer.parseInt(request.getParameter("id"));
		Boolean success = userban.ban(id);
		request.setAttribute("success",success);
	}

}
