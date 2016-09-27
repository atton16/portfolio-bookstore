package com.sixppl.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.ListingDAO;
import com.sixppl.dao.support.ListingDAOImpl;
import com.sixppl.dto.ListingDTO;

public class SellCommand implements Command {
	
	private boolean error;
	private String error_msg;
	
	public SellCommand(){
		error = false;
		error_msg = null;
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ListingDAO listing = new ListingDAOImpl();
		ListingDTO pubSell = new ListingDTO();
		pubSell.title = request.getParameter("title").trim();
		if(request.getParameter("authors") != null){
			String[] authors = request.getParameter("authors").split(",");
			for(String author: authors){
				pubSell.authors.add(author.trim());
			}
		}
		if(request.getParameter("editors") != null){
			String[] editors = request.getParameter("editors").split(",");
			for(String editor: editors){
				pubSell.editors.add(editor.trim());
			}
		}
		pubSell.type = request.getParameter("pubtype").trim();
		pubSell.year = Integer.valueOf(request.getParameter("year"));
		if(request.getParameter("venue") != null)
			pubSell.venue = request.getParameter("venue").trim();
//		TODO: Implement upload pic: request.getPart("pic")
//		pubSell.picture = request.getParameter("pic").trim();
		pubSell.price = Integer.valueOf(request.getParameter("price"));
		
		error = listing.addListing(pubSell);
		if(error == false){
			error_msg = null;
		}
		else{
			error_msg = "ERROR: CANNOT ADD ITEM";
		}
		
		request.setAttribute("error", error);
		request.setAttribute("error_msg", error_msg);
	}

}
