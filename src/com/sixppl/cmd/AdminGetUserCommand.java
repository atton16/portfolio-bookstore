package com.sixppl.cmd;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

import java.util.*;

import com.sixppl.dao.AdminLoginDAO;
import com.sixppl.dao.AdminUserBanDAO;
import com.sixppl.dao.AdminUserDAO;

public class AdminGetUserCommand implements Command {
	private AdminUserDAO adminUserDao;
	private AdminUserBanDAO adminUserBanDao;
	private AdminLoginDAO adminLoginDao;
	
	public AdminGetUserCommand() {
		adminUserDao = Application.getSharedInstance().getDAOFactory().getAdminUserDAO();
		adminUserBanDao = Application.getSharedInstance().getDAOFactory().getAdminUserBanDAO();
		adminLoginDao = Application.getSharedInstance().getDAOFactory().getAdminLoginDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		List<UserDTO> results= new ArrayList<UserDTO>(); 
		
		if(type == null){
			return;
		}
		
		if(type.equals("Nick Name")){
			results=adminUserDao.findByNickname(keyword);
		}
		else if(type.equals("First Name")){
			results=adminUserDao.findByFirstname(keyword);
		}
		else if(type.equals("Last Name")){
			results=adminUserDao.findByLastname(keyword);
		}
		else if(type.equals("Email")){
			results=adminUserDao.findByEmail(keyword);
		}
		else if(type.equals("All Customers")){
			results=adminUserDao.findAllCustomers();
		}
		else if(type.equals("All Sellers")){
			results=adminUserDao.findAllSellers();
		}


		Integer page = 1;
		Integer total = results.size();
		if(request.getParameter("page") != null){
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch(Exception e) {}
		}
		while(page*10 > total) page--;	// Overflow protection
		if(page < 1)
			page = 1;
		Integer start = page*10-10+1;
		Integer end = page*10;
		end = end > total ? total : end;
		
		String queryString = "";
		
		if(request.getQueryString() != null) {
			for(String s: request.getQueryString().split("&")) {
				if(!s.contains("page=")){
					queryString+=s;
					queryString+="&";
				}
			}
			if(queryString.length() > 0)
				queryString = queryString.substring(0, queryString.length()-1);
		}

		if(page > 1)
			request.setAttribute("prevParams", queryString+"&page="+String.valueOf(page-1));
		if(end < results.size())
			request.setAttribute("nextParams", queryString+"&page="+String.valueOf(page+1));
		
		for(UserDTO user: results) {
			user.setIsBanned(adminUserBanDao.isBanned(user.getUserID()));
			user.setIsAdmin(adminLoginDao.isAdmin(user.getUserID()));
			user.setIsCustomer(adminUserDao.isCustomer(user.getUserID()));
		}

		results = results.subList(start-1, end);
		request.setAttribute("items", results);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("total", total);
//		request.setAttribute("type", type);
//		request.setAttribute("keyword", keyword);
		
	}

}
