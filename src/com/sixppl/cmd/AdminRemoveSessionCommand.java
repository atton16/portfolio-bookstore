package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sixppl.dao.*;
import com.sixppl.dao.support.*;

public class AdminRemoveSessionCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminLoginDAO register = new AdminLoginDAOImpl();
		String SessionID = request.getSession().getId();
		register.removeAdminSession(SessionID);
		
	}

}
