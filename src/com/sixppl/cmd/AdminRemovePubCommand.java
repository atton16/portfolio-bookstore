package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.AdminPubDAO;
import com.sixppl.main.Application;

public class AdminRemovePubCommand implements Command{
	private AdminPubDAO adminPubDao;
	
	public AdminRemovePubCommand() {
		adminPubDao = Application.getSharedInstance().getDAOFactory().getAdminPubDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean success=false;
		Integer pubID;
		try {
			pubID = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			request.setAttribute("error", true);
			return;
		}
		success = adminPubDao.removePub(pubID);
		request.setAttribute("error", !success);
	}

}
