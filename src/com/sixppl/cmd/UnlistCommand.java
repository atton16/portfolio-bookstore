package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.CartDAO;
import com.sixppl.dao.ListingDAO;
import com.sixppl.main.Application;

public class UnlistCommand implements Command {
	private ListingDAO listingDao;
	private CartDAO cartDao;
	
	public UnlistCommand() {
		listingDao = Application.getSharedInstance().getDAOFactory().getListingDAO();
		cartDao = Application.getSharedInstance().getDAOFactory().getCartDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pubID = Integer.valueOf(request.getParameter("id"));
		listingDao.setUnlist(pubID);
		cartDao.removeAllMatchedPubID(pubID);
	}

}
