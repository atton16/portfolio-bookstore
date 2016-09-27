package com.sixppl.cmd;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.mindrot.jbcrypt.EmailSending;

import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;

public class UserProfileCommand  implements Command {
	private UserDAO userDao;
	private SessionDAO sessionDao;
	public UserProfileCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
		sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
	}
	@Override
	/*This method is for user profile update*/
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDTO user = new UserDTO();
		HttpSession session = request.getSession();
		String cpassword = request.getParameter("cpassword");
		String npassword = request.getParameter("npassword");
		String nickname = request.getParameter("nickname");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String yob = request.getParameter("yob");
		String address = request.getParameter("address");
		String ccn = request.getParameter("ccn");
		if(cpassword == null || cpassword.equals("")){
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "Password should not be null");
			return;
		}
		SessionDTO sessionDto = new SessionDTO();
		sessionDto.setSessionID(session.getId());
		user = userDao.findUserByUserID(sessionDao.finduserIDbySession(sessionDto));
		if(user == null){
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "User does not exist");
			return;
		}
		if(!BCrypt.checkpw(cpassword, user.getPassword())){
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "Incorrect current password");
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
			user.setBirthyear(Integer.parseInt(yob));
		if (address != null && !address.equals(""))
			user.setAddr(address);
		if (ccn != null && !ccn.equals(""))
			user.setCardno(ccn);
		if(email != null && !email.equals("") && !email.equals(user.getEmail())){
			user.setNewemail(email);
			String token = UUID.randomUUID().toString();
			user.setTokenstring(token);
			String to = request.getParameter("email");
			String from = "asst2unsw@gmail.com";
			
			String contextPath = request.getContextPath();
			String fullURI = request.getRequestURI();
			String URI = fullURI.substring(contextPath.length());
			String full_path = request.getRequestURL().substring(0, request.getRequestURL().indexOf(URI));
			
			EmailSending emailSending = new EmailSending();
			emailSending.sendEmail(to, from, full_path + "/user/profile/confirm?token="+token);
		}
		userDao.updateUser(user);
		session.setAttribute("user", user);
		request.setAttribute("user", session.getAttribute("user"));
		request.setAttribute("error", false);
		request.setAttribute("error_msg", "Changes saved.");
		if(user.getNewemail() != null)
			request.setAttribute("need_verify", true);
		else
			request.setAttribute("need_verify", false);

	} 

}
