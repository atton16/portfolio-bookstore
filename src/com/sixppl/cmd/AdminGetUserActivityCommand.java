package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sixppl.dao.CartDAO;
import com.sixppl.dao.ListingDAO;
import com.sixppl.dao.TransactionDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.dto.CartDTO;
import com.sixppl.dto.ListingDTO;
import com.sixppl.dto.TransactionDTO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

import java.util.*;

public class AdminGetUserActivityCommand implements Command{
	private UserDAO userDao;
	private CartDAO cartDao;
	private TransactionDAO transactionDao;
	private ListingDAO listingDAO;
	
	public AdminGetUserActivityCommand(){
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
		cartDao = Application.getSharedInstance().getDAOFactory().getCartDAO();
		transactionDao = Application.getSharedInstance().getDAOFactory().getTransactionDAO();
		listingDAO = Application.getSharedInstance().getDAOFactory().getListingDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer UserID = null;
		UserDTO userDto = null;
		List<ListingDTO> buyList = new ArrayList<ListingDTO>();
		List<ListingDTO> removedList = new ArrayList<ListingDTO>();
		List<TransactionDTO> buyTransactions = new ArrayList<TransactionDTO>();
		List<CartDTO> cart = new ArrayList<CartDTO>();
		
		try {
			UserID = Integer.parseInt(request.getParameter("id"));
		} catch(Exception E){
			request.setAttribute("error", true);
			request.setAttribute("user", null);
			return;
		}
		
		userDto = userDao.findUserByUserID(UserID);
		
		if(userDto == null){
			request.setAttribute("error", true);
			request.setAttribute("user", null);
			return;
		}
		
		buyTransactions = transactionDao.getByBuyerID(UserID);
		for(TransactionDTO buyTransaction: buyTransactions) {
			ListingDTO pub = listingDAO.getByPubID(buyTransaction.getPubID());
			pub.setBuyDate(buyTransaction.getPurchaseTime());
			buyList.add(pub);
		}
		
		cart = cartDao.getByUserID(UserID);
		for(CartDTO cartItem: cart) {
			if(cartItem.getRemovetime() != null){
				ListingDTO pub = listingDAO.getByPubID(cartItem.getPubID());
				pub.setRemoveFromCartDate(cartItem.getRemovetime());
				removedList.add(pub);
			}
		}
		
		if(buyList.isEmpty() && removedList.isEmpty()){
			request.setAttribute("error", true);
			request.setAttribute("user", null);
			return;
		}
		
		request.setAttribute("user", userDto);
		request.setAttribute("buys", buyList);
		request.setAttribute("removes", removedList);
		request.setAttribute("error", false);
	}
}
