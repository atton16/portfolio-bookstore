package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;

import com.sixppl.dao.AdminPubDAO;
import com.sixppl.main.Application;

public class AdminGetPubCommand implements Command{
	private AdminPubDAO adminPubDao;
	
	public AdminGetPubCommand() {
		adminPubDao = Application.getSharedInstance().getDAOFactory().getAdminPubDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer pubID;
		try {
			pubID = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			request.setAttribute("error", true);
			return;
		}
		
		JSONArray arrayj=adminPubDao.findPub(pubID);
		request.setAttribute("jsonreturn", arrayj);
		request.setAttribute("error", false);
	}

}
