package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sixppl.dao.AdminUserActivityDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.dao.support.AdminUserActivityDAOImpl;
import com.sixppl.dto.AdminCartDTO;
import com.sixppl.dto.CustomerActivityDTO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

import java.util.*;

public class AdminGetUserActivityCommand implements Command{
	private AdminUserActivityDAO admunUserActivityDao;
	private UserDAO userDao;
	public AdminGetUserActivityCommand(){
		admunUserActivityDao = new AdminUserActivityDAOImpl();
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer UserID = null;
		UserDTO userDto = null;
		List<CustomerActivityDTO> buyList = new ArrayList<CustomerActivityDTO>();
		List<AdminCartDTO> removedList = new ArrayList<AdminCartDTO>();
		
		try {
			UserID = Integer.parseInt(request.getParameter("id"));
		} catch(Exception E){
			return;
		}
		
		userDto = userDao.findUserByUserID(UserID);
		
		if(userDto == null)
			return;
		
		buyList = admunUserActivityDao.getBuyingHistory(UserID);
		removedList = admunUserActivityDao.getCartHistory(UserID);
		
		request.setAttribute("user", userDto);
		request.setAttribute("buys", buyList);
		request.setAttribute("removes", removedList);
	}
}
