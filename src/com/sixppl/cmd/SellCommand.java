package com.sixppl.cmd;

import java.io.*;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.sixppl.dao.ListingDAO;
import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.support.SessionDAOImpl;
import com.sixppl.dto.EntityDTO;
import com.sixppl.dto.GraphDTO;
import com.sixppl.dto.ListingDTO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.importData.ImportGraph;
import com.sixppl.importData.PublicationDTO;
import com.sixppl.main.Application;


public class SellCommand implements Command {
	
	private boolean error;
	private String error_msg;
	private ListingDAO listingDao;
	long countPublication;
	long countAuthor;
	long countVenue;
	long countEdge;
	long countEntity;
	
	public SellCommand(){
		error = false;
		error_msg = null;
		listingDao = Application.getSharedInstance().getDAOFactory().getListingDAO();
		countPublication = 0;
		countAuthor = 0;
		countEdge = 0;
		countEntity = 0;
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
		
		// Add Listing
		error = !listingDao.addListing(pubSell);
		if(error == false){
			error_msg = "Successfully posted the publication!";
		}
		else{
			error_msg = "ERROR: Cannot Add Item, Please check if you are logged in.";
		}
		
		// Add to Graph
		PublicationDTO pubGraph = new PublicationDTO();
		pubGraph.setTitle(pubSell.title);
		pubGraph.setAuthor(pubSell.authors);
		pubGraph.setEditor(pubSell.editors);
		pubGraph.setJournal(pubSell.venue);
		
		addToGraph(pubGraph);
		
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
	
	public void addToGraph(PublicationDTO p){
		ArrayList<EntityDTO> insertedEntity = new ArrayList<EntityDTO>();
		ImportGraph dao = new ImportGraph();
		EntityDTO ep = new EntityDTO();
		EntityDTO ea = new EntityDTO();
		EntityDTO ed = new EntityDTO();
		EntityDTO ev = new EntityDTO();
		EntityDTO ee = new EntityDTO();
		GraphDTO pa = new GraphDTO();
		GraphDTO pd = new GraphDTO();
		GraphDTO pv = new GraphDTO();
		// Extract publication
		ep = new EntityDTO(countEntity+1, "P"+ Long.toString(countPublication+1), "Node", "Publication", p.getTitle());
		if (EntityDTO.containsEntity(insertedEntity, ep)) {
			ep = EntityDTO.findEntity(insertedEntity, ep);
		}
		else {
			dao.insertEntity(ep);
			insertedEntity.add(ep);
			countPublication++;
			countEntity++;
		}
		
		// Extract author
		if (!p.getAuthor().isEmpty()){
			for (String author : p.getAuthor()) {
				ea = new EntityDTO(countEntity+1, "A" + Long.toString(countAuthor+1), "Node", "Author", author);
				if (EntityDTO.containsEntity(insertedEntity, ea)) {
					ea = EntityDTO.findEntity(insertedEntity, ea);
				}
				else {
					dao.insertEntity(ea);
					insertedEntity.add(ea);
					countAuthor++;
					countEntity++;
				}
				// Link Publication authored by Author
				ee = new EntityDTO(countEntity+1, "E" + Long.toString(countEdge+1), "Edge", "DirectedLink", "authored by");
				dao.insertEntity(ee);
				insertedEntity.add(ee);
				pa = new GraphDTO(0, ep.getEntityID(), ee.getEntityID(), ea.getEntityID());
				dao.insertGraph(pa);
				countEdge++;
				countEntity++;
				
			}
		}
		// Extract Editor
		if (!p.getEditor().isEmpty()){
			for (String editor : p.getEditor()) {
				ed = new EntityDTO(countEntity+1, "A" + Long.toString(countAuthor+1), "Node", "Author", editor);
				if (EntityDTO.containsEntity(insertedEntity, ed)) {
					ed = EntityDTO.findEntity(insertedEntity, ed);
				}
				else {
					dao.insertEntity(ed);
					insertedEntity.add(ed);
					countAuthor++;
					countEntity++;
				}
				// Link Publication edited by Editor
				ee = new EntityDTO(countEntity+1, "E" + Long.toString(countEdge+1), "Edge", "DirectedLink", "edited by");
				dao.insertEntity(ee);
				insertedEntity.add(ee);
				pd = new GraphDTO(0, ep.getEntityID(), ee.getEntityID(), ed.getEntityID());
				dao.insertGraph(pd);
				countEdge++;
				countEntity++;
			}
		}
		// Extract Venue
		if (p.getJournal() != null) {
			ev = new EntityDTO(countEntity+1, "V" + Long.toString(countVenue+1), "Node", "Venue", p.getJournal());
			if (EntityDTO.containsEntity(insertedEntity, ev)) {
				ev = EntityDTO.findEntity(insertedEntity, ev);
			}
			else {
				dao.insertEntity(ev);
				insertedEntity.add(ev);
				countVenue++;		
				countEntity++;
			}
			// Link Publication published in Venue
			ee = new EntityDTO(countEntity+1, "E" + Long.toString(countEdge+1), "Edge", "DirectedLink", "published in");
			dao.insertEntity(ee);
			insertedEntity.add(ee);
			pv = new GraphDTO(0, ep.getEntityID(), ee.getEntityID(), ev.getEntityID());
			dao.insertGraph(pv);
			countEdge++;
			countEntity++;
		}
	}
}


