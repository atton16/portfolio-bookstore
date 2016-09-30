package com.sixppl.cmd;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.ListingDAO;
import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.dao.support.UserDAOImpl;
import com.sixppl.dto.ListingDTO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

public class CheckoutCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListingDAO listing = Application.getSharedInstance().getDAOFactory().getListingDAO();
		SessionDAO sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
		UserDTO user = new UserDTO();
		ArrayList<ListingDTO> items = new ArrayList<ListingDTO>();
		String[] pubIds = request.getParameterValues("id");
		for(String pubID: pubIds){
			ListingDTO pubKey = new ListingDTO();
			pubKey.setPubID(Integer.parseInt(pubID.trim()));
			items.add(listing.Search(pubKey, request.getSession().getId()).get(0));
		}
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setSessionID(request.getSession().getId());
		int userId = sessionDao.finduserIDbySession(sessionDTO);
		
		UserDAO userDao = new UserDAOImpl();
		user = userDao.findUserByUserID(userId);
		
		request.setAttribute("user", user);
		request.setAttribute("items", items);
	}

}
