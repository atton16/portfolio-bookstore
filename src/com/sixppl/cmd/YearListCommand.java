package com.sixppl.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.ListingDAO;
import com.sixppl.dao.support.ListingDAOImpl;

public class YearListCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Integer> years_asc, years_desc;
		years_desc = new LinkedList<Integer>();
		ListingDAO dao = new ListingDAOImpl();
		try{
			years_asc = dao.getYearsAscending();
			years_desc.addAll(years_asc);
			Collections.reverse(years_desc);
		}catch(Exception e){
			e.printStackTrace();
			years_asc = new LinkedList<Integer>();
		}

		request.setAttribute("years_asc", years_asc);
		request.setAttribute("years_desc", years_desc);
	}

}
