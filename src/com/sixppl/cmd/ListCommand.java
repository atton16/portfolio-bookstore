package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.ListingDAO;
import com.sixppl.main.Application;

public class ListCommand implements Command {
	private ListingDAO listingDao;
	
	public ListCommand() {
		listingDao = Application.getSharedInstance().getDAOFactory().getListingDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pubID = Integer.valueOf(request.getParameter("id"));
		listingDao.setList(pubID);
	}

}
