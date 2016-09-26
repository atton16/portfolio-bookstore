package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.CartDAO;
import com.sixppl.dao.support.CartDAOImpl;

public class CartAddCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userID = 0;//Wait for session method
		int pubID = Integer.valueOf(request.getParameter("id"));
		CartDAO dao = new CartDAOImpl();
		int cartCount = 0;
		try{
			cartCount = dao.addCart(pubID,userID);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			cartCount = 0;
		}
		request.setAttribute("cartCount", cartCount);
	}

}
 