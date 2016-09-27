package com.sixppl.cmd;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sixppl.dto.UserDTO;
import java.util.*;
import com.sixppl.dao.AdminUserDAO;
import com.sixppl.dao.support.AdminUserDAOImpl;

public class AdminGetUserCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchtype = request.getParameter("searchtype");
		String search = request.getParameter("keyword");
		AdminUserDAO temp = new AdminUserDAOImpl();
		List<UserDTO> templist= new ArrayList<UserDTO>(); 
		if(searchtype.equals("Nickname")){
			templist=temp.findByNickname(search);
		}
		else if(searchtype.equals("Firstname")){
			templist=temp.findByFirstname(search);
		}
		else if(searchtype.equals("Lastname")){
			templist=temp.findByLastname(search);
		}
		else if(searchtype.equals("Email")){
			templist=temp.findByEmail(search);
		}
		else if(searchtype.equals("All Customers")){
			templist=temp.findAllCustomers();
		}
		else if(searchtype.equals("All Sellers")){
			templist=temp.findAllSellers();
		}
		request.setAttribute("searchresults", templist);
		
	}

}
