package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.ListingDAO;
import com.sixppl.dto.ListingDTO;
import com.sixppl.main.Application;

public class PubDetailCommand implements Command {
	
	ListingDTO item;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("id") == null || request.getParameter("id").isEmpty()){
			request.setAttribute("item", null);
			return;
		}
		
		int pubId = Integer.parseInt(request.getParameter("id"));
		ListingDTO pubKey = new ListingDTO();
		pubKey.setPubID(pubId);
		ListingDAO listing = Application.getSharedInstance().getDAOFactory().getListingDAO();
		item = listing.Search(pubKey).get(0);
		
		request.setAttribute("item", item);
	}

}
