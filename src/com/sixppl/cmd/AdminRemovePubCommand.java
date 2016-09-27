package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.AdminPubDAO;
import com.sixppl.dao.support.AdminPubDAOImpl;

public class AdminRemovePubCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean result=false;
		int pubID = Integer.parseInt(request.getParameter("id"));
		int sellerID = Integer.parseInt(request.getParameter("sellerid"));
		AdminPubDAO test = new AdminPubDAOImpl();
		result=test.removePub(pubID, sellerID);
		request.setAttribute("removestatus", result);
	}

}