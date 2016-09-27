package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sixppl.dao.AdminUserActivityDAO;
import com.sixppl.dao.support.AdminUserActivityDAOImpl;
import com.sixppl.dto.AdminCartDTO;
import com.sixppl.dto.CustomerActivityDTO;

import java.util.*;

public class AdminGetUserActivityCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Integer UserID = null;
		UserID=Integer.parseInt(request.getParameter("userid1"));
		if(UserID!=null){
			request.setAttribute("user", UserID);
		
		AdminUserActivityDAO temp = new AdminUserActivityDAOImpl();
		List<CustomerActivityDTO> buyinglist = new ArrayList<CustomerActivityDTO>();
		List<AdminCartDTO> cartlist = new ArrayList<AdminCartDTO>();
		buyinglist=temp.getBuyingHistory(UserID);
		cartlist=temp.getCartHistory(UserID);
		if(buyinglist.size()>0){
			request.setAttribute("buys", buyinglist);
		}
		else{
			request.setAttribute("buys", null);
		}
		if(cartlist.size()>0){
			request.setAttribute("removes", cartlist);
		}
		else{
			request.setAttribute("removes", null);
		}
	}
	
	else{
		request.setAttribute("user", null);
	}
	}
}
