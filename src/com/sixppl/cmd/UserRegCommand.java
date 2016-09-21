package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sixppl.bean.UserEntity;
import com.sixppl.dao.DummyDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.main.Application;

public class UserRegCommand implements Command {
	private UserDAO userDao;
	public UserRegCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserEntity user = new UserEntity();

		user.setUsername(request.getParameter("username")); 
		System.out.println(request.getParameter("username"));
		user.setPassword(request.getParameter("password")); 
		user.setNickname(request.getParameter("nickname")); 
		user.setFirstname(request.getParameter("firstname")); 
		user.setLastname(request.getParameter("lastname"));
		user.setEmail(request.getParameter("email"));
		user.setBirthyear(request.getParameter("yob"));
		user.setAddr(request.getParameter("address"));
		user.setCardno(request.getParameter("ccn"));
		userDao.addUser(user);
	}


}
