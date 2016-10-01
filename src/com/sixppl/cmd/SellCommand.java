package com.sixppl.cmd;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.sixppl.dao.ListingDAO;
import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.support.SessionDAOImpl;
import com.sixppl.dto.ListingDTO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.main.Application;


public class SellCommand implements Command {
	
	private boolean error;
	private String error_msg;
	private ListingDAO listingDao;
	
	public SellCommand(){
		error = false;
		error_msg = null;
		listingDao = Application.getSharedInstance().getDAOFactory().getListingDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(parameterChecker(request) == false){
			error = true;
			error_msg = "Please enter the required fields.";
			request.setAttribute("error", error);
			request.setAttribute("error_msg", error_msg);
			return;
		}
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
		SessionDAO sessionDao = new SessionDAOImpl();
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setSessionID(request.getSession().getId());
		pubSell.sellerID = sessionDao.finduserIDbySession(sessionDTO);
		
		pubSell.type = request.getParameter("pubtype").trim();
		pubSell.year = Integer.valueOf(request.getParameter("year"));
		if(request.getParameter("venue") != null)
			pubSell.venue = request.getParameter("venue").trim();
		
		
		Part filePart = request.getPart("pic");
		InputStream inputStream = filePart.getInputStream();
		int picId = listingDao.getTotal()+1;
		String picUrl = Application.UPLOADS_PATH + "pic"+picId+".jpg";
		System.out.println(picUrl);
		OutputStream outputStream = new FileOutputStream(new File(picUrl));

		int read = 0;
		byte[] bytes = new byte[1024];
		while ((read = inputStream.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
		outputStream.flush();
		outputStream.close();

		pubSell.picture = "/uploads/pic"+picId+".jpg";
		
		pubSell.price = Integer.valueOf(request.getParameter("price"));
		
		error = !listingDao.addListing(pubSell);
		if(error == false){
			error_msg = "Successfully posted the publication!";
		}
		else{
			error_msg = "ERROR: Cannot Add Item, Please check if you are logged in.";
		}
		
		request.setAttribute("error", error);
		request.setAttribute("error_msg", error_msg);
	}
	
	public boolean parameterChecker(HttpServletRequest request){
		boolean pass = true;
		if(request.getParameter("title") == null || request.getParameter("title").trim().isEmpty()){
			pass = false;
		}
		if(request.getParameter("authors") == null && request.getParameter("editors")== null){
			pass = false;
		}
		if(request.getParameter("authors").trim().isEmpty() && request.getParameter("editors").trim().isEmpty()){
			pass = false;
		}
		if(request.getParameter("pubtype") == null || request.getParameter("pubtype").contains("Publication Type")){
			pass = false;
		}
		if(request.getParameter("year") == null || request.getParameter("year").trim().isEmpty()){
			pass = false;
		}
		if(request.getParameter("price") == null || request.getParameter("price").trim().isEmpty()){
			pass = false;
		}
		
		return pass;
	}

}
