package com.sixppl.cmd;

import java.io.IOException;
import com.sixppl.dao.*;
import com.sixppl.dao.support.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPageHitCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminAnalyticsDAO pagehits=new AdminAnalyticsDAOImpl();
		String uri=request.getRequestURI().substring(request.getContextPath().length());
		Boolean status = pagehits.updatePageHits(uri);
	}

}
