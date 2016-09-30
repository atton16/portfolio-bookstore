package com.sixppl.cmd;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.mindrot.jbcrypt.EmailSending;

import com.sixppl.dao.UserDAO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

public class UserRegCommand implements Command {
	private UserDAO userDao;

	public static final int MYSQL_DUPLICATE= 1062;
	public UserRegCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
	}

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		return matcher.find();
	}
	
	private static final Pattern VALID_CCN_REGEX = 
			Pattern.compile("^([0-9]){16}$", Pattern.CASE_INSENSITIVE);

	private static boolean validate_ccn(String ccn) {
		Matcher matcher = VALID_CCN_REGEX .matcher(ccn);
		return matcher.find();
	}

	@Override
	/* This method is for user registration */
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDTO user = new UserDTO();
		String username, password, email, addr, cardno;
		Integer yob;
		EmailSending emailSending = new EmailSending();

		request.setAttribute("username", request.getParameter("username"));
		request.setAttribute("password", request.getParameter("password"));
		request.setAttribute("nickname", request.getParameter("nickname"));
		request.setAttribute("firstname", request.getParameter("firstname"));
		request.setAttribute("lastname", request.getParameter("lastname"));
		request.setAttribute("email", request.getParameter("email"));
		request.setAttribute("yob", request.getParameter("yob"));
		request.setAttribute("address", request.getParameter("address"));
		request.setAttribute("ccn", request.getParameter("ccn"));


		user.setUsername(request.getParameter("username")); 

		System.out.println(request.getParameter("username"));
		user.setPassword(request.getParameter("password")); 


		password = BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt());
		user.setPassword(password);

		user.setNickname(request.getParameter("nickname")); 
		user.setFirstname(request.getParameter("firstname")); 

		username = request.getParameter("username");
		password = request.getParameter("password");
		email = request.getParameter("email");
		addr = request.getParameter("address");
		cardno = request.getParameter("ccn");
		if (username == null || username.equals("") || email == null || email.equals("")
				|| password == null || password.equals("") || addr == null
				|| addr.equals("") ||cardno == null
				|| cardno.equals("") ) {
			request.setAttribute("error", true);
			request.setAttribute("error_msg",
					"The username or email or password or addr or cardno should not be null.");
			System.out.println("The username or email or password should not be null.");
			return;
		}

		if (!UserRegCommand.validate(email)){
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "Invalid email pattern");
			System.out.println("Invalid email pattern");
			return;
		}
		
		try {
			yob = Integer.parseInt(request.getParameter("yob"));
		} catch (Exception e) {
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "Invalid year of birth");
			System.out.println("Invalid year of birth");
			return;
		}
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		if (yob > year) {
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "Are you sure you are born in the future???");
			System.out.println("Are you sure you are born in the future???");
			return;
		}
		
		if (yob < year-150) {
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "Hello overly old peep!");
			System.out.println("Hello overly old peep!");
			return;
		}
		
		if (!UserRegCommand.validate_ccn(cardno)) {
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "Credit card number must be 16-digit long!");
			System.out.println("Credit card number must be 16-digit long!");
			return;
		}
		
		//user = userDao.findUserByName(request.getParameter("username"));
		if (userDao.findUserByName(request.getParameter("username")) != null) {
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "The username has been used.");
			System.out.println("The username has been used");
			return;
		} 
		if (userDao.findUserByEmail(request.getParameter("email")) != null) {
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "The email has been used.");
			System.out.println("The email has been used");
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
		user.setBirthyear(Integer.parseInt(request.getParameter("yob")));
		user.setAddr(request.getParameter("address"));
		user.setCardno(request.getParameter("ccn"));
		String token = UUID.randomUUID().toString();
		System.out.println("the uuid is" + token);
		user.setTokenstring(token);
		userDao.addUser(user);

		String to = request.getParameter("email");
		String from = "asst2unsw@gmail.com";
		
		String Judge_env = Application.getSharedInstance().getEnvironment();
		
		if (Judge_env =="DEVELOPMENT"){
			String contextPath = request.getContextPath();
			String fullURI = request.getRequestURI();
			String URI = fullURI.substring(contextPath.length());
			String full_path = request.getRequestURL().substring(0, request.getRequestURL().indexOf(URI));
			System.out.println("the full path is"+full_path);
			emailSending.sendEmail(to, from, full_path + "/signup/confirm?token="+token);
			request.setAttribute("email", to);
			request.setAttribute("error", false);
		}
		else if (Judge_env =="PRODUCTION")
		{	
	    String ip = Application.getSharedInstance().getProductionIP();
	    String port = Application.getSharedInstance().getProductionPort();
		System.out.println("the ip  is"+ip);
		String strUrl = "https://" + ip 
		                          + ":"   
		                          + port ;         
		System.out.println("the addres is"+strUrl);
		emailSending.sendEmail(to, from, strUrl  + "/asst2/signup/confirm?token="+token);
		request.setAttribute("email", to);
		request.setAttribute("error", false);}
		
		else if (Judge_env =="PRODUCTION_IP"){
			String contextPath = request.getContextPath();
			String fullURI = request.getRequestURI();
			String URI = fullURI.substring(contextPath.length());
			String full_path = request.getRequestURL().substring(0, request.getRequestURL().indexOf(URI));
		
			String ip = Application.getIpAddress();
		
			System.out.println("the ip should be"+ip);
	        
			String strBackUrl = "https://" + ip   
			                    + ":"   
			                    + Application.getSharedInstance().getProductionPort();
			       
			System.out.println("the addres is "+strBackUrl);
			System.out.println("the full path is "+full_path);
			emailSending.sendEmail(to, from, strBackUrl + "/asst2/signup/confirm?token="+token);
			request.setAttribute("email", to);
			request.setAttribute("error", false);
		}
		else {
			System.out.println("Please put the correct ENV in Application.java!!");
		}
	


	}

}
