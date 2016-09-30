package com.sixppl.cmd;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.cmd.Command;
import com.sixppl.dao.ListingDAO;
import com.sixppl.dao.support.ListingDAOImpl;
import com.sixppl.dto.ListingDTO;
import com.sixppl.main.Application;

public class SearchCommand implements Command {
	private int start;
	private int end;
	private int total;
	private String prevParams;
	private String nextParams;
	private ArrayList<ListingDTO> items; 
	private int page;
	private ListingDAO listingDao;
	
	public SearchCommand(){
		start = 0;
		end = 0;
		total = 0;
		prevParams = null;
		nextParams = null;
		items = new ArrayList<ListingDTO>();
		page = 0;
		listingDao = Application.getSharedInstance().getDAOFactory().getListingDAO();

	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("SearchCommand EXECUTED");
		// TODO Auto-generated method stub
		if(request.getParameterMap() == null || request.getParameterMap().size() <= 1){
			System.out.println("Empty Search Executed");
			// Empty Search
			if(request.getParameter("page") != null){
				page = Integer.valueOf(request.getParameter("page"));
				System.out.println("page parameter found");
			}
			ArrayList<ListingDTO> results = listingDao.emptySearch();
			System.out.println("Item found = " + results.size());
			setResultsAttribute(request,results);
			
		}
		else if(request.getParameter("keyword") != null){
			// Simple Search
			if(request.getParameter("page") != null){
				page = Integer.valueOf(request.getParameter("page"));
			}
			ListingDTO pubKey = new ListingDTO();
			String keyword = request.getParameter("keyword").toLowerCase().trim();
			String type = request.getParameter("type").toLowerCase().trim();
			if(type.contains("title")){
				pubKey.title = keyword;
			}
			else if(type.contains("author") || type.contains("editor")){
				pubKey.writers.add(keyword);
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
			ArrayList<ListingDTO> results = listingDao.Search(pubKey);
			setResultsAttribute(request,results);
		}
		else{
			//Advance Search
			if(request.getParameter("page") != null){
				page = Integer.valueOf(request.getParameter("page"));
			}
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
			
			ArrayList<ListingDTO> results = listingDao.Search(pubKey);
			setResultsAttribute(request,results);
			
		}

	}
	
	public void setResultsAttribute(HttpServletRequest request,ArrayList<ListingDTO> results){
		if(results.size() <= 0){
			request.setAttribute("start", start);
			request.setAttribute("end", end);
			request.setAttribute("total", total);
			request.setAttribute("prevParams", prevParams);
			request.setAttribute("nextParams", nextParams);
			request.setAttribute("items", items);
			return;
		}
		total = results.size();
		start = page*10 + 1;
		for(int i = start; results.get(i) != null && i <= start + 10; i++){
			items.add(results.get(i));
		}
		end = start + items.size() - 1;
		
		String urlpath = request.getContextPath();
		if(urlpath.contains("?")){
			urlpath = urlpath.substring(urlpath.indexOf("?")+1);
		}
		else{
			urlpath = "";
		}
		int i = -1,j = -1;
		if(page == 0){
			i = page +1;
		}
		else if(page == (total%10 == 0?total/10:total/10+1)){
			j = page -1;
		}
		else{
			i = page +1;
			j = page -1;
		}
		if(urlpath.contains("page")){
			urlpath = urlpath.substring(0, urlpath.lastIndexOf("&"));
			if(j == -1){
				prevParams = null;
				nextParams = urlpath + "&page=" + i;
			}
			else if(i == -1){
				nextParams = null;
				prevParams = urlpath + "&page=" + j;
			}
			else if(i != -1 && j != -1){
				prevParams = urlpath + "&page=" + j;
				nextParams = urlpath + "&page=" + i;
			}
		}
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("total", total);
		request.setAttribute("prevParams", prevParams);
		request.setAttribute("nextParams", nextParams);
		request.setAttribute("items", items);
	}

}
