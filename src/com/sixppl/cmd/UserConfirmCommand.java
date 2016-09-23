package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.sixppl.dao.UserDAO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

public class UserConfirmCommand {
	private UserDAO userDao;
	public UserConfirmCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
	}
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		UserDTO user = new UserDTO();
		System.out.println(request.getParameter("token"));
		user = userDao.findUserByToken(request.getParameter("token"));
		if(user == null){
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "Cannot find token");
			return;
		}
		String newemail = user.getNewemail();
		user.setEmail(newemail);
		user.setNewemail(null);
		user.setTokenstring(null);
		userDao.updateUser(user);
	}
}
