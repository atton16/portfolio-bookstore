package com.sixppl.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchTermsCommand implements Command {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> searchTerms = new ArrayList<String>();
		for (String key: request.getParameterMap().keySet()) {
			if(key.equalsIgnoreCase("page"))
				continue;
			String[] valueArray = request.getParameterMap().get(key);
			for (String value : valueArray) {
				searchTerms.add(value);
			}
		}
		request.setAttribute("searchTerms", searchTerms);
	}

}
