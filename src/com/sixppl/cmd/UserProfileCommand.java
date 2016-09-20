package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.bean.UserEntity;
import com.sixppl.dao.UserDAO;
import com.sixppl.main.Application;

public class UserProfileCommand  implements Command {
	private UserDAO userDao;
	public UserProfileCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserEntity user = new UserEntity();
		user = userDao.findUserByName(request.getParameter("username"));

		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String yob = request.getParameter("yob");
		String address = request.getParameter("address");
		String ccn = request.getParameter("ccn");
		
		
		if(password!=null && !password.equals(""))
		user.setPassword(password); 
		if(nickname!=null && !nickname.equals(""))
		user.setNickname(nickname); 
		if(firstname!=null && !firstname.equals(""))
		user.setFirstname(firstname); 
		if(lastname!=null && !lastname.equals(""))
		user.setLastname(lastname);
		if(email!=null && !email.equals(""))
		user.setEmail(email);
		if(yob!=null && !yob.equals(""))
		user.setBirthyear(yob);
		if(address!=null && !address.equals(""))
		user.setAddr(address);
		if(ccn!=null && !ccn.equals(""))
		user.setCardno(ccn);
		
		userDao.updateUser(user);
	} 

}
