package com.sixppl.cmd;

import java.io.IOException;
import java.util.List;

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
		if(request.getParameter("id") == null || request.getParameter("id").isEmpty()){
			request.setAttribute("item", null);
			return;
		}
		
		int pubId = Integer.parseInt(request.getParameter("id"));
		ListingDTO pubKey = new ListingDTO();
		pubKey.setPubID(pubId);
		ListingDAO listing = Application.getSharedInstance().getDAOFactory().getListingDAO();
		List<ListingDTO> items = listing.searchAll(pubKey, request.getSession().getId());
		
		if(!items.isEmpty()){
			item = items.get(0);
			Application.getSharedInstance().getDAOFactory().getListingStatisticsDAO().incrementMostViewed(pubId);
			request.setAttribute("item", item);
			return;
		}
		request.setAttribute("item", null);
		
	}

}
