package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.bean.UserEntity;
import com.sixppl.dao.UserDAO;
import com.sixppl.main.Application;

public class UserLoginCommand implements Command {
	private UserDAO userDao;
	public UserLoginCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
	}
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserEntity user = new UserEntity();
		user = userDao.findUserByName(request.getParameter("username"));
	}

}
