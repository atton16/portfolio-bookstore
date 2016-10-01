package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.CartDAO;
import com.sixppl.dao.SessionDAO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.main.Application;

public class CartAddCommand implements Command {
	private CartDAO cartDao;
	private SessionDAO sessionDao;
	
	public CartAddCommand() {
		cartDao = Application.getSharedInstance().getDAOFactory().getCartDAO();
		sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setSessionID(request.getSession().getId());
		int userID = sessionDao.finduserIDbySession(sessionDTO);
		int pubID = Integer.valueOf(request.getParameter("id"));
		int cartCount = 0;
		try{
			cartCount = cartDao.addCart(pubID,userID);
		}catch(Exception e){
			e.printStackTrace();
		}
		Application.getSharedInstance().getDAOFactory().getListingStatisticsDAO().incrementMostAddedToCart(pubID);
		
		request.setAttribute("cartCount", cartCount);
	}

}
 