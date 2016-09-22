package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.mindrot.jbcrypt.EmailSending;

import com.sixppl.dao.DummyDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

public class UserRegCommand implements Command {
	private UserDAO userDao;
	public UserRegCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
	}
	@Override
	/*This method is for user registration*/
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDTO user = new UserDTO();
		String username,password,email;
		EmailSending emailSending = new EmailSending();
		username = request.getParameter("username");
		password = request.getParameter("password");
		email = request.getParameter("email");
		if (username == null || username.equals("") || username == null
				|| password == null || password.equals("") || email == null
				|| email.equals("")){
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "The username or email or password should not be null.");
		} else {
			user = userDao.findUserByName(request.getParameter("username"));
			if (user.getUsername() != null) {
				request.setAttribute("success", false);
				request.setAttribute("error_msg", "The username has been used.");
			} else {
				user.setUsername(request.getParameter("username"));
				password = BCrypt.hashpw(request.getParameter("password"),
						BCrypt.gensalt());
				user.setPassword(password);

				user.setNickname(request.getParameter("nickname"));
				user.setFirstname(request.getParameter("firstname"));
				user.setLastname(request.getParameter("lastname"));
				user.setEmail(request.getParameter("email"));
				user.setBirthyear(request.getParameter("yob"));
				user.setAddr(request.getParameter("address"));
				user.setCardno(request.getParameter("ccn"));
				userDao.addUser(user);
				String to = request.getParameter("email");
				String from = "asst2unsw@gmail.com";
				String full_path = request.getRequestURL().toString();
				emailSending.sendEmail(to, from, full_path + "/signup/confirm");
			}
		}
		
	}

	

}
