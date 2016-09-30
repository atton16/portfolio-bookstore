package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.bean.CartBean;
import com.sixppl.dao.CartDAO;
import com.sixppl.dao.SessionDAO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.main.Application;

public class EmbedCartCommand implements Command {
	private CartDAO cartDao;
	private SessionDAO sessionDao;
	
	public EmbedCartCommand() {
		cartDao = Application.getSharedInstance().getDAOFactory().getCartDAO();
		sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setSessionID(request.getSession().getId());
		int userID = sessionDao.finduserIDbySession(sessionDTO);
		CartBean cartBean = new CartBean();
		try{
			cartBean.setCartList(cartDao.viewCart(userID));
		}catch(Exception e){
			e.printStackTrace();
		}
		request.getSession().setAttribute("cart", cartBean.getCartList());
	}

}
