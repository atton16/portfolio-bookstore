package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.sixppl.dao.UserDAO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

public class UserLoginCommand implements Command {
	private UserDAO userDao;
	public UserLoginCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
	}
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		UserDTO user = new UserDTO();

		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));
		user = userDao.findUserByName(request.getParameter("username"));
		if(user == null){
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "Cannot find username");
			return;
		}
		String password = user.getPassword();
		if(BCrypt.checkpw(request.getParameter("password"), password))
		{
			request.setAttribute("success", true);
		} else {
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "Login Failed.");
		}
		
	}

}
