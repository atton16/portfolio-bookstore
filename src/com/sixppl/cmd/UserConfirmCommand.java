package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.sixppl.dao.UserDAO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

public class UserConfirmCommand implements Command {
	private UserDAO userDao;
	public UserConfirmCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
	}
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO user = new UserDTO();
		System.out.println(request.getParameter("token"));
		user = userDao.findUserByToken(request.getParameter("token"));
		if(user == null){
			System.out.println("no userID is found");
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "Cannot find token");
			return;
		}
		String newemail = user.getNewemail();
		if (newemail !=null){
			System.out.println("set new email as null");
			user.setEmail(newemail);
		}
		System.out.println("check if succsess,userid is" + user.getUserID()
				+ "username is" + user.getUsername() + "password is"
				+ user.getPassword() + "email is"+user.getEmail()+ "address is" + user.getAddr() + "cardno"
				+ user.getCardno());
		if(user.getNewemail() != null){
			user.setNewemail(null);
		}
		user.setTokenstring(null);
		
		userDao.updateUser(user);
		System.out.println("succsess");
		System.out.println(user.getEmail());
		request.setAttribute("email", user.getEmail());
		request.setAttribute("error", false);
	}
}
