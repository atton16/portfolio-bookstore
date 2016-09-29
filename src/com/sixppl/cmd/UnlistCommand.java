package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.ListingDAO;
import com.sixppl.main.Application;

public class UnlistCommand implements Command {
	private ListingDAO listingDao;
	
	public UnlistCommand() {
		listingDao = Application.getSharedInstance().getDAOFactory().getListingDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int pubID = Integer.valueOf(request.getParameter("id"));
		listingDao.setUnlist(pubID);
	}

}
