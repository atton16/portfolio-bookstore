package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.main.Application;

public class AnalyticsMostAddedToCartCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("most_added_to_cart", Application.getSharedInstance().getDAOFactory().getListingStatisticsDAO().getMostAddedToCart(10));

	}

}
