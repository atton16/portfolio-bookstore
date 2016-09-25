package com.sixppl.cmd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

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

	public static final int MYSQL_DUPLICATE= 1062;
	public UserRegCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
	}

	@SuppressWarnings("null")
	@Override
	/* This method is for user registration */
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDTO user = new UserDTO();
		String username, password, email, addr, cardno;
		EmailSending emailSending = new EmailSending();
		username = request.getParameter("username");
		password = request.getParameter("password");
		email = request.getParameter("email");
		addr = request.getParameter("address");
		cardno = request.getParameter("ccn");
		if (username == null || username.equals("")
				|| password == null || password.equals("") || addr == null
				|| addr.equals("") ||cardno == null
						|| cardno.equals("") ) {
			request.setAttribute("success", false);
			request.setAttribute("error_msg",
					"The username or email or password or addr or cardno should not be null.");
			System.out.println("The username or email or password should not be null.");
			return;
		} else {
			//user = userDao.findUserByName(request.getParameter("username"));
			if (userDao.findUserByName(request.getParameter("username")) != null) {
				request.setAttribute("success", false);
				request.setAttribute("error_msg", "The username has been used.");
				System.out.println("The username has been used");
				return;
			} 
			user.setUsername(username);
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
			String token = UUID.randomUUID().toString().substring(0, 19);
			System.out.println("the uuid is" + token);
			user.setTokenstring(token);
			userDao.addUser(user);
			
			String to = request.getParameter("email");
			String from = "asst2unsw@gmail.com";

			//String full_path = request.getRequestURL().toString();
			String full_path = "http://localhost:8080/asst2";
			System.out.println("the full path is"+full_path);
			emailSending.sendEmail(to, from, full_path + "/signup/confirm?token="+token);
			request.setAttribute("success", true);
		}

	}

}
