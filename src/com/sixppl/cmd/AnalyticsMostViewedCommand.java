package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.main.Application;

public class AnalyticsMostViewedCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("most_viewed", Application.getSharedInstance().getDAOFactory().getListingStatisticsDAO().getMostViewed(10));
	}

}
