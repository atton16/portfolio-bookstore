package com.sixppl.cmd;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sixppl.dto.UserDTO;
import java.util.*;
import com.sixppl.dao.AdminUserDAO;
import com.sixppl.dao.support.AdminUserDAOImpl;

public class AdminGetUserCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		AdminUserDAO temp = new AdminUserDAOImpl();
		List<UserDTO> templist= new ArrayList<UserDTO>(); 
		HttpSession session=request.getSession();
		
		if(type.equals("Nickname")){
			templist=temp.findByNickname(keyword);
		}
		else if(type.equals("Firstname")){
			templist=temp.findByFirstname(keyword);
		}
		else if(type.equals("Lastname")){
			templist=temp.findByLastname(keyword);
		}
		else if(type.equals("Email")){
			templist=temp.findByEmail(keyword);
		}
		else if(type.equals("All Customers")){
			templist=temp.findAllCustomers();
		}
		else if(type.equals("All Sellers")){
			templist=temp.findAllSellers();
		}
		request.setAttribute("type", type);
		request.setAttribute("keyword", keyword);
		request.setAttribute("total", templist.size());
		request.setAttribute("start", 0);
		request.setAttribute("items", templist);
		
		if(templist.size()<10){
			request.setAttribute("end", templist.size());
			String nextParams=null;
			String prevParams=null;
			request.setAttribute("items", templist);
		}
		else{
			request.setAttribute("end", 10);
			String prevParams=null;
			String nextParams = "?type="+type+"&keyword="+keyword+"&total=" + Integer.toString(templist.size()) + "&nextpage=2";
		}
		
		
	}

}
