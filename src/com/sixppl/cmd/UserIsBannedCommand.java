package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.AdminUserBanDAO;
import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.support.AdminUserBanDAOImpl;
import com.sixppl.main.Application;

public class UserIsBannedCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminUserBanDAO userban = new AdminUserBanDAOImpl();
		SessionDAO sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
		Integer id = Integer.parseInt(request.getParameter("id"));
		if(request.getParameter("bantype").equals("ban")){
			if(!userban.checkBan(id)){
				request.setAttribute("banflag",userban.userBan(id));
			}
		}
		else if(request.getParameter("bantype").equals("unban")){
			if(userban.checkBan(id)){
				request.setAttribute("unbanflag",userban.userUnban(id));
			}
		}
	}

}
