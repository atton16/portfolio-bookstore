package com.sixppl.cmd;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.CartDAO;
import com.sixppl.dao.support.CartDAOImpl;

public class CartRemoveCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userID = 0;//Wait for session method
		ArrayList<Integer> removedPub = new ArrayList<Integer>();
		String[] removedPubID = request.getParameterValues("id");
		for(String pubID:removedPubID){
			removedPub.add(Integer.valueOf(pubID));
		}
		CartDAO cart = new CartDAOImpl();
		cart.removeCart(userID,removedPub);
		
	}

}
