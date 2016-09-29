package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.CartDAO;
import com.sixppl.main.Application;

public class CartAddCommand implements Command {
	private CartDAO cartDao;
	
	public CartAddCommand() {
		cartDao = Application.getSharedInstance().getDAOFactory().getCartDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userID = 0;//Wait for session method
		int pubID = Integer.valueOf(request.getParameter("id"));
		int cartCount = 0;
		try{
			cartCount = cartDao.addCart(pubID,userID);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			cartCount = 0;
		}
		request.setAttribute("cartCount", cartCount);
	}

}
 