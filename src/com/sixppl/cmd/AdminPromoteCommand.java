package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.AdminDAO;
import com.sixppl.main.Application;

public class AdminPromoteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminDAO adminDao = Application.getSharedInstance().getDAOFactory().getAdminDAO();
		Integer userId = -1;
		Boolean error = true;
		try {
			userId = Integer.parseInt(request.getParameter("id"));
		} catch(Exception e) {
			request.setAttribute("error", error);
			return;
		}
		request.setAttribute("error", !adminDao.promote(userId));
	}

}
