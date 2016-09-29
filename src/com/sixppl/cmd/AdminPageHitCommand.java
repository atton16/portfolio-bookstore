package com.sixppl.cmd;

import java.io.IOException;
import com.sixppl.dao.*;
import com.sixppl.main.Application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPageHitCommand implements Command{
	private AdminAnalyticsDAO adminAnalyticsDao;
	
	public AdminPageHitCommand() {
		adminAnalyticsDao = Application.getSharedInstance().getDAOFactory().getAdminAnalyticsDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI().substring(request.getContextPath().length());
		Boolean status = adminAnalyticsDao.updatePageHits(uri);
	}

}
