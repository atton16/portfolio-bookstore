package com.sixppl.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.cmd.Command;
import com.sixppl.dao.ListingDAO;
import com.sixppl.dto.ListingDTO;
import com.sixppl.main.Application;

public class SearchAllCommand implements Command {
	private ListingDAO listingDao;
	
	public SearchAllCommand(){
		listingDao = Application.getSharedInstance().getDAOFactory().getListingDAO();

	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SearchCommand EXECUTED");
		if(request.getParameterMap() == null || request.getParameterMap().size() <= 1){
			return;
		}
		else if(request.getParameter("keyword") != null){
			// Simple Search
			System.out.println("Simple Search Executed");
			ListingDTO pubKey = new ListingDTO();
			String keyword = request.getParameter("keyword").toLowerCase().trim();
			String type = request.getParameter("type").toLowerCase().trim();
			if(type.contains("title")){
				pubKey.title = keyword;
			}
			else if(type.contains("author") || type.contains("editor")){
				System.out.println("writer keyword: " + keyword);
				String[] writers = keyword.split(",");
				for(String writer:writers){
					pubKey.writers.add(writer.trim());
				}
			}
			else if(type.contains("type")){
				pubKey.type = keyword;
			}
			else if(type.contains("year")){
				pubKey.fromYear = Integer.valueOf(keyword);
				pubKey.toYear = Integer.valueOf(keyword);
			}
			else if(type.contains("venue")){
				pubKey.venue = keyword;
			}
			else{
				System.out.println("Cannot Find Parmeters");
				return;
			}
			ArrayList<ListingDTO> results = listingDao.searchAll(pubKey, request.getSession().getId());
			setResultsAttribute(request,results);
		}
		else{
			System.out.println("Advanced Search Executed");
			//Advance Search
			ListingDTO pubKey = new ListingDTO();
			pubKey.title = request.getParameter("title").trim();
			String[] writers = request.getParameter("authors-and-editors").split(",");
			for(String writer:writers){
				pubKey.writers.add(writer.trim());
			}
			if(!request.getParameter("pubtype").toLowerCase().contains("any")){
				pubKey.type = request.getParameter("pubtype").trim();
			}
			if(!request.getParameter("year-from").toLowerCase().contains("dawn of the ape")){
				pubKey.fromYear = Integer.valueOf(request.getParameter("year-from"));
			}
			if(!request.getParameter("year-to").toLowerCase().contains("end of humanity")){
				pubKey.toYear = Integer.valueOf(request.getParameter("year-to"));
			}
			pubKey.venue = request.getParameter("venue").trim();
			
			ArrayList<ListingDTO> results = listingDao.searchAll(pubKey, request.getSession().getId());
			setResultsAttribute(request,results);
			
		}

	}
	
	public void setResultsAttribute(HttpServletRequest request,ArrayList<ListingDTO> results){
		Integer page = 1;
		Integer total = results.size();
		if(request.getParameter("page") != null){
			try {
				page = Integer.parseInt(request.getParameter("page"));
			} catch(Exception e) {}
		}
		while(page*10 > (total + (10-total%10))) page--;	// Overflow protection
		if(page < 1)
			page = 1;
		Integer start = page*10-10+1;
		Integer end = page*10;
		end = end > total ? total : end;
		
		String queryString = "";
		
		if(request.getQueryString() != null) {
			for(String s: request.getQueryString().split("&")) {
				if(!s.contains("page=")){
					queryString+=s;
					queryString+="&";
				}
			}
			if(queryString.length() > 0)
				queryString = queryString.substring(0, queryString.length()-1);
		}

		if(page > 1)
			request.setAttribute("prevParams", queryString+"&page="+String.valueOf(page-1));
		if(end < total)
			request.setAttribute("nextParams", queryString+"&page="+String.valueOf(page+1));

		List<ListingDTO> items = results.subList(start-1, end);
		request.setAttribute("items", items);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("total", total);
	}

}
