package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sixppl.dao.*;
import com.sixppl.dao.support.*;
import com.sixppl.dto.*;
public class AdminLoginCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String UserName = request.getParameter("username");
		String Password = request.getParameter("password");
		String SessionID = request.getSession().getId();
		AdminLoginDTO temp = new AdminLoginDTO();
		AdminLoginDAO UIDcheck = new AdminLoginDAOImpl();
		Integer UserID = UIDcheck.returnUID(UserName, Password);
		temp=UIDcheck.isAnAdmin(UserID);
		if(temp!=null){
			request.setAttribute("admincheck", temp);
		}
		else{
			request.setAttribute("admincheck", null);
		}
	}

}
