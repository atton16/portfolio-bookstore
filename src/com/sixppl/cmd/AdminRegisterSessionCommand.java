package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sixppl.dao.*;
import com.sixppl.dao.support.*;

public class AdminRegisterSessionCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminLoginDAO register = new AdminLoginDAOImpl();
		String SessionID = request.getSession().getId();
		String UserName = request.getParameter("username");
		String Password = request.getParameter("password");
		AdminLoginDAO UIDcheck = new AdminLoginDAOImpl();
		Integer UserID = UIDcheck.returnUID(UserName, Password);
		register.registerAdminSession(UserID, SessionID);
	}

}
