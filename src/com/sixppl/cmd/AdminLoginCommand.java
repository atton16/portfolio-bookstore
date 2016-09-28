package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.sixppl.dao.*;
import com.sixppl.dao.support.*;
import com.sixppl.dto.*;
import com.sixppl.main.Application;
public class AdminLoginCommand implements Command{
	private UserDAO userDao;
	public AdminLoginCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String UserName = request.getParameter("username");
		String Password = request.getParameter("password");
		AdminLoginDTO adminDto = new AdminLoginDTO();
		AdminLoginDAO adminDao = new AdminLoginDAOImpl();
		UserDTO user = userDao.findUserByName(UserName);
		
		if(user == null) {
			request.setAttribute("error_msg", "Invalid username or password.");
			return;
		}
		
		if(!BCrypt.checkpw(Password, user.getPassword()))
		{
			request.setAttribute("error_msg", "Invalid username or password.");
			return;
		}
		
		adminDto = adminDao.getAdmin(user.getUserID());
		
		if(adminDto == null) {
			request.setAttribute("error_msg", "Login failed");
			return;
		}
		
		Boolean success = adminDao.login(user.getUserID(),request.getSession().getId());
		
		if(!success) {
			request.setAttribute("error_msg", "Login failed");
			return;
		}
		
		request.setAttribute("error_msg", null);
		request.getSession().setAttribute("admin", user);
		return;
	}

}
