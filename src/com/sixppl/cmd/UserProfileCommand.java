package com.sixppl.cmd;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.mindrot.jbcrypt.EmailSending;

import com.sixppl.dao.UserDAO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

public class UserProfileCommand  implements Command {
	private UserDAO userDao;
	public UserProfileCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
	}
	@Override
	/*This method is for user profile update*/
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO user = new UserDTO();
		String username = request.getParameter("username");
		String cpassword = request.getParameter("cpassword");
		String npassword = request.getParameter("npassword");
		String nickname = request.getParameter("nickname");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String yob = request.getParameter("yob");
		String address = request.getParameter("address");
		String ccn = request.getParameter("ccn");
		if(username == null || username.equals("")||cpassword == null || cpassword.equals("")){
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "username or password should not be null");
			return;
		}
		user = userDao.findUserByName(username);
		if(user == null){
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "Cannot find username");
			return;
		}
		if(!BCrypt.checkpw(cpassword, user.getPassword())){
			request.setAttribute("success", false);
			request.setAttribute("error_msg", "input the correct password");
			return;
		}

		

		if (npassword != null && !npassword.equals(""))
			user.setPassword(npassword);
		if (nickname != null && !nickname.equals(""))
			user.setNickname(nickname);
		if (firstname != null && !firstname.equals(""))
			user.setFirstname(firstname);
		if (lastname != null && !lastname.equals(""))
			user.setLastname(lastname);

		if (yob != null && !yob.equals(""))
			user.setBirthyear(yob);
		if (address != null && !address.equals(""))
			user.setAddr(address);
		if (ccn != null && !ccn.equals(""))
			user.setCardno(ccn);
		if(email != null && !email.equals("")){
			user.setNewemail(email);
			String token = UUID.randomUUID().toString();
			user.setTokenstring(token);
			String to = request.getParameter("email");
			String from = "asst2unsw@gmail.com";
			String full_path = request.getRequestURL().toString();
			EmailSending emailSending = new EmailSending();
			emailSending.sendEmail(to, from, full_path + "/signup/confirm?token="+token);
		}
		userDao.updateUser(user);
	
		
	} 

}
