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
		Boolean result=false;
		int pubID = Integer.parseInt(request.getParameter("id"));
		int sellerID = Integer.parseInt(request.getParameter("sellerid"));
		result=adminPubDao.removePub(pubID, sellerID);
		request.setAttribute("removestatus", result);
	}

}
