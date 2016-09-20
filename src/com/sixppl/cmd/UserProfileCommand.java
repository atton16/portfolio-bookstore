package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

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
		user = userDao.findUserByName(request.getParameter("username"));

		String cpassword = request.getParameter("cpassword");
		String npassword = request.getParameter("npassword");
		String nickname = request.getParameter("nickname");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String yob = request.getParameter("yob");
		String address = request.getParameter("address");
		String ccn = request.getParameter("ccn");

		if (cpassword != null && !cpassword.equals("")
				&& BCrypt.checkpw(cpassword, user.getPassword())) {

			if (npassword != null && !npassword.equals(""))
				user.setPassword(npassword);
			if (nickname != null && !nickname.equals(""))
				user.setNickname(nickname);
			if (firstname != null && !firstname.equals(""))
				user.setFirstname(firstname);
			if (lastname != null && !lastname.equals(""))
				user.setLastname(lastname);
			if (email != null && !email.equals(""))
				user.setEmail(email);
			if (yob != null && !yob.equals(""))
				user.setBirthyear(yob);
			if (address != null && !address.equals(""))
				user.setAddr(address);
			if (ccn != null && !ccn.equals(""))
				user.setCardno(ccn);

			userDao.updateUser(user);
		
		}
	} 

}
