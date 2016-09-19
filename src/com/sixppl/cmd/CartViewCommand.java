package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.bean.CartBean;
import com.sixppl.dao.CartDAO;
import com.sixppl.dao.support.CartDAOImpl;

public class CartViewCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int userID = 0;// Wait for session method
		CartDAO cart = new CartDAOImpl();
		CartBean cartBean = new CartBean();
		cartBean.setCartList(cart.viewCart(userID));
		request.setAttribute("CartList", cart);
	}

}
