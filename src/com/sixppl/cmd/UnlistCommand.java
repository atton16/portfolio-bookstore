package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.ListingDAO;
import com.sixppl.dao.support.ListingDAOImpl;

public class UnlistCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pubID = Integer.valueOf(request.getParameter("id"));
		ListingDAO listing = new ListingDAOImpl();
		listing.setUnlist(pubID);
	}

}
