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
		String password, candidate;
		UserDTO user = new UserDTO();
		user = userDao.findUserByName(request.getParameter("username"));
		password = user.getPassword();
		candidate = request.getParameter("password");
		request.setAttribute("login",BCrypt.checkpw(candidate, password)?"TRUE":"FALSE");
		
	}

}
