package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.bean.CartBean;
import com.sixppl.dao.CartDAO;
import com.sixppl.main.Application;

public class CartViewCommand implements Command {
	private CartDAO cartDao;
	
	public CartViewCommand() {
		cartDao = Application.getSharedInstance().getDAOFactory().getCartDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userID = 0;// Wait for session method
		CartBean cartBean = new CartBean();
		try{
			cartBean.setCartList(cartDao.viewCart(userID));
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("CartList", cartDao);	//TODO: Why return DAO?? Return something else
	}

}
