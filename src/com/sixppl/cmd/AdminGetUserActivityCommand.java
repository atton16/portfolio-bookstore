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
		Integer UserID=Integer.parseInt(request.getParameter("userid1"));
		AdminUserActivityDAO temp = new AdminUserActivityDAOImpl();
		List<CustomerActivityDTO> buyinglist = new ArrayList<CustomerActivityDTO>();
		List<AdminCartDTO> cartlist = new ArrayList<AdminCartDTO>();
		buyinglist=temp.getBuyingHistory(UserID);
		cartlist=temp.getCartHistory(UserID);
		if(buyinglist.size()>0){
			request.setAttribute("buyinghistory", buyinglist);
		}
		else{
			request.setAttribute("buyinghistory", null);
		}
		if(cartlist.size()>0){
			request.setAttribute("cartlist", cartlist);
		}
		else{
			request.setAttribute("cartlist", null);
		}
	}

}
