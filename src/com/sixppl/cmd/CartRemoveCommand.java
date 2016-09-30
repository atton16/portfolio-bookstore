package com.sixppl.cmd;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.CartDAO;
import com.sixppl.dao.SessionDAO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.main.Application;

public class CartRemoveCommand implements Command {
	private CartDAO cartDao;
	private SessionDAO sessionDao;
	
	public CartRemoveCommand() {
		cartDao = Application.getSharedInstance().getDAOFactory().getCartDAO();
		sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setSessionID(request.getSession().getId());
		int userID = sessionDao.finduserIDbySession(sessionDTO);
		ArrayList<Integer> removedPub = new ArrayList<Integer>();
		String[] removedPubID = request.getParameterValues("id");
		for(String pubID:removedPubID){
			try{
				removedPub.add(Integer.valueOf(pubID));
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		try{
			cartDao.removeCart(userID,removedPub);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
