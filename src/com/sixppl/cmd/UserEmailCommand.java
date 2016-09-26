package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.mindrot.jbcrypt.EmailSending;

import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

public class UserEmailCommand {
	private UserDAO userDao;
	public UserEmailCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
		
	}
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");

		System.out.println(request.getParameter("email"));
		if(email == null|| email.equals("") ){
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "Null value for username or password.");
			return;
		}
		
		UserDTO user  = userDao.findUserByEmail(email);
		if(user == null){
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "Cannot find username");
			return;
		}
		EmailSending emailSending = new EmailSending();
		String to = email;
		String from = "asst2unsw@gmail.com";
		String token = user.getTokenstring();
		//here can check if  it is 21 or 26 ,then choose specific full_path
		//String full_path = request.getRequestURL().toString();
		String full_path = "http://localhost:8080/asst2";
		System.out.println("the full path is"+full_path);
		emailSending.sendEmail(to, from, full_path + "/signup/confirm?token="+token);
		
		request.setAttribute("success", true);
	}

}
