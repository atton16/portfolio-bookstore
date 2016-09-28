package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;

import com.sixppl.dao.AdminPubDAO;
import com.sixppl.dao.support.AdminPubDAOImpl;

public class AdminGetPubCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pubID = Integer.parseInt(request.getParameter("id"));
		AdminPubDAO test = new AdminPubDAOImpl();
		JSONArray arrayj=test.findPub(pubID);
		request.setAttribute("jsonreturn", arrayj);
	}

}
