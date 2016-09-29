package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sixppl.dao.*;
import com.sixppl.dto.*;
import com.sixppl.main.Application;

import java.util.*;

public class AdminGetPageHitCommand implements Command{
	private AdminAnalyticsDAO adminAnalyticsDao;
	
	public AdminGetPageHitCommand() {
		adminAnalyticsDao = Application.getSharedInstance().getDAOFactory().getAdminAnalyticsDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<AdminAnalyticsDTO> pagehitlist = adminAnalyticsDao.getPageHits();
		request.setAttribute("page_hits", pagehitlist);
	}

}
