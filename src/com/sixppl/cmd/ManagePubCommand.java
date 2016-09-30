package com.sixppl.cmd;

import java.io.IOException;
import java.util.ArrayList;

import com.sixppl.dto.*;
import com.sixppl.main.Application;
import com.sixppl.dao.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagePubCommand implements Command {
	
	ArrayList<ListingDTO> items;
	SessionDAO session;
	ListingDAO listing;
	
	public ManagePubCommand(){
		this.items = new ArrayList<ListingDTO>();
		this.session = Application.getSharedInstance().getDAOFactory().getSessionDAO();
		this.listing = Application.getSharedInstance().getDAOFactory().getListingDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		items = new ArrayList<ListingDTO>();
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setSessionID(request.getSession().getId());
		int userId = session.finduserIDbySession(sessionDTO);
		ListingDTO pubKey = new ListingDTO();
		pubKey.sellerID = userId;
		items = listing.Search(pubKey, request.getSession().getId());
		
		request.setAttribute("items", items);
	}

}
