package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.dto.UserDTO;
import com.sixppl.main.Application;
import com.sixppl.main.support.EmailSupport;
import com.sixppl.main.support.EmailSending;

public class UserEmailChangeCommand implements Command {
	private UserDAO userDao;
	private SessionDAO sessionDao;
	public UserEmailChangeCommand() {
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
		sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
		
	}
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionDTO sessionDto = new SessionDTO();
		HttpSession session = request.getSession();
		sessionDto.setSessionID(session.getId());
		UserDTO user = userDao.findUserByUserID(sessionDao.finduserIDbySession(sessionDto));
		
		if(user == null){
			request.setAttribute("error", true);
			request.setAttribute("error_msg", "Cannot find username");
			return;
		}
		
		String email = user.getNewemail();
		
		EmailSending emailSending = new EmailSending();
		String to = email;
		String token = user.getTokenstring();
		
		String contextPath = request.getContextPath();
		String fullURI = request.getRequestURI();
		String URI = fullURI.substring(contextPath.length());
		String full_path = request.getRequestURL().substring(0, request.getRequestURL().indexOf(URI));
		
		System.out.println("the full path is"+full_path);
		if(token == null) {
			request.setAttribute("error", true);
			return;
		}
		emailSending.sendEmail(
				to,
				EmailSupport.SenderEmail(),
				EmailSupport.ChangeEmailSubject(),
				EmailSupport.ChangeEmailContent(full_path + "/user/profile/confirm?token="+token)
				);
		request.setAttribute("email", to);
		request.setAttribute("error", false);
	}

}
