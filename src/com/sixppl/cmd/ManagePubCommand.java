package com.sixppl.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sixppl.dto.*;
import com.sixppl.main.Application;
import com.sixppl.dao.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagePubCommand implements Command {
	
	SessionDAO session;
	ListingDAO listing;
	
	public ManagePubCommand(){
		this.session = Application.getSharedInstance().getDAOFactory().getSessionDAO();
		this.listing = Application.getSharedInstance().getDAOFactory().getListingDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ListingDTO> items = new ArrayList<ListingDTO>();
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setSessionID(request.getSession().getId());
		int userId = session.finduserIDbySession(sessionDTO);
		ListingDTO pubKey = new ListingDTO();
		pubKey.sellerID = userId;
		items = listing.searchAll(pubKey, request.getSession().getId());
		

		Integer page = 1;
		Integer total = items.size();
		if(request.getParameter("page") != null){
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch(Exception e) {}
		}
		while(page*10 > (total + (10-total%10))) page--;	// Overflow protection
		if(page < 1)
			page = 1;
		Integer start = page*10-10+1;
		Integer end = page*10;
		end = end > total ? total : end;
		
		String queryString = "";
		
		if(request.getQueryString() != null) {
			for(String s: request.getQueryString().split("&")) {
				if(!s.contains("page=")){
					queryString+=s;
					queryString+="&";
				}
			}
			if(queryString.length() > 0)
				queryString = queryString.substring(0, queryString.length()-1);
		}

		if(page > 1)
			request.setAttribute("prevParams", queryString+"&page="+String.valueOf(page-1));
		if(end < total)
			request.setAttribute("nextParams", queryString+"&page="+String.valueOf(page+1));
		
		items = items.subList(start-1, end);
		request.setAttribute("items", items);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("total", total);
	}

}
