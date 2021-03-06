package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sixppl.dao.*;
import com.sixppl.main.Application;

public class AdminGetPageHitCommand implements Command{
	private PageHitsDAO pageHitsDao;
	
	public AdminGetPageHitCommand() {
		pageHitsDao = Application.getSharedInstance().getDAOFactory().getPageHitsDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("page_hits", pageHitsDao.getPageHits());
	}

}
