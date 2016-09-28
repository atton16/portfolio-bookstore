package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sixppl.dao.*;
import com.sixppl.dao.support.*;
import com.sixppl.dto.*;
import java.util.*;

public class AdminGetPageHitCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminAnalyticsDAO temp = new AdminAnalyticsDAOImpl();
		List<AdminAnalyticsDTO> pagehitlist = temp.getPageHits();
		request.setAttribute("page_hits", pagehitlist);
	}

}
