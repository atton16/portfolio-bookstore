package com.sixppl.cmd;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.sixppl.dao.EntityDAO;
import com.sixppl.dao.GraphDAO;
import com.sixppl.dao.ListingDAO;
import com.sixppl.dao.SessionDAO;
import com.sixppl.dao.UserDAO;
import com.sixppl.dto.EntityDTO;
import com.sixppl.dto.GraphDTO;
import com.sixppl.dto.ListingDTO;
import com.sixppl.dto.SessionDTO;
import com.sixppl.dto.UserDTO;
import com.sixppl.importData.PublicationDTO;
import com.sixppl.main.Application;


public class SellCommand implements Command {
	
	private boolean error;
	private String error_msg;
	private ListingDAO listingDao;
	private EntityDAO entityDao;
	private GraphDAO graphDao;
	private SessionDAO sessionDao;
	private UserDAO userDao;
	long countPublication;
	long countAuthor;
	long countVenue;
	long countEdge;
	long countEntity;
	ArrayList<EntityDTO> insertedEntity;
	
	public SellCommand(){
		error = false;
		error_msg = null;
		listingDao = Application.getSharedInstance().getDAOFactory().getListingDAO();
		entityDao = Application.getSharedInstance().getDAOFactory().getEntityDAO();
		graphDao = Application.getSharedInstance().getDAOFactory().getGraphDAO();
		sessionDao = Application.getSharedInstance().getDAOFactory().getSessionDAO();
		userDao = Application.getSharedInstance().getDAOFactory().getUserDAO();
		countEntity = 0;
//		countPublication = 0;
//		countAuthor = 0;
//		countEdge = 0;
		
		// Get all existing nodes from entity table
		try {
			insertedEntity = entityDao.findAllNodes();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		// Get max ID of each node class
		try {
			countPublication = entityDao.getMaxNodeID("Publication");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			countAuthor = entityDao.getMaxNodeID("Author");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			countVenue = entityDao.getMaxNodeID("Venue");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			countEdge = entityDao.getMaxEdgeID();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Check if all field is filled
		if(parameterChecker(request) == false){
			error = true;
			error_msg = "Please enter the required fields.";
			request.setAttribute("error", error);
			request.setAttribute("error_msg", error_msg);
			return;
		}
		
		//Check if Seller have a valid email
		SessionDTO sessionDTO = new SessionDTO();
		sessionDTO.setSessionID(request.getSession().getId());
		int sellerID = sessionDao.finduserIDbySession(sessionDTO);
		UserDTO seller = userDao.findUserByUserID(sellerID);
		if(seller.getEmail() == null || seller.getEmail().isEmpty()){
			error = true;
			error_msg = "Your email address is invalid. You can check your email in Edit Profile.";
			request.setAttribute("error", error);
			request.setAttribute("error_msg", error_msg);
			return;
		}
		
		ListingDTO pubSell = new ListingDTO();
		PublicationDTO pubGraph = new PublicationDTO();
		ArrayList<String> pubGraphAuthor = new ArrayList<String>();
		ArrayList<String> pubGraphEditor = new ArrayList<String>();
		pubSell.title = request.getParameter("title").trim();
		if(request.getParameter("authors") != null){
			String[] authors = request.getParameter("authors").split(",");
			for(String author: authors){
				pubSell.authors.add(author.trim());
				if (!author.trim().equals("")) {
					pubGraphAuthor.add(author.trim());
				}
			}
		}
		if(request.getParameter("editors") != null){
			String[] editors = request.getParameter("editors").split(",");
			for(String editor: editors){
				pubSell.editors.add(editor.trim());
				if (!editor.trim().equals("")) {
					pubGraphEditor.add(editor.trim());
				}
			}
		}
	
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
		
		pubSell.price = Float.valueOf(request.getParameter("price"));

		
		// Add Listing
		error = !listingDao.addListing(pubSell);
		if(error == false){
			error_msg = "Successfully posted the publication!";
		}
		else{
			error_msg = "ERROR: Cannot Add Item, Please check if you are logged in.";
		}
		
		// Add to Graph
		pubGraph.setTitle(pubSell.title);
		if (!pubGraphAuthor.isEmpty()){
			pubGraph.setAuthor(pubGraphAuthor);
		}
		if (!pubGraphEditor.isEmpty()){
			pubGraph.setEditor(pubGraphEditor);
		}
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
		try {
			insertedEntity = entityDao.findAllNodes();
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		EntityDTO ep = new EntityDTO();
		EntityDTO ea = new EntityDTO();
		EntityDTO ed = new EntityDTO();
		EntityDTO ev = new EntityDTO();
		EntityDTO ee = new EntityDTO();
		GraphDTO pa = new GraphDTO();
		GraphDTO pd = new GraphDTO();
		GraphDTO pv = new GraphDTO();
		long PubID = listingDao.getMaxPubID();
		// Extract publication
		ep = new EntityDTO(countEntity+1, "P"+ Long.toString(PubID), "Node", "Publication", p.getTitle());
		if (EntityDTO.containsEntity(insertedEntity, ep)) {
			ep = EntityDTO.findEntity(insertedEntity, ep);
		}
		else {
			try {
				entityDao.insertEntity(ep,PubID);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Insert Title");
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
					try {
						entityDao.insertEntity(ea,PubID);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					System.out.println("Insert Author");
					insertedEntity.add(ea);
					countAuthor++;
					countEntity++;
				}
				// Link Publication authored by Author
				ee = new EntityDTO(countEntity+1, "E" + Long.toString(countEdge+1), "Edge", "DirectedLink", "authored by");
				try {
					entityDao.insertEntity(ee,PubID);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Link Title to author");
				insertedEntity.add(ee);
				pa = new GraphDTO(0, ep.getEntityID(), ee.getEntityID(), ea.getEntityID());
				try {
					graphDao.insertGraph(pa,PubID);
				} catch (SQLException e) {
					e.printStackTrace();
				}
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
					try {
						entityDao.insertEntity(ed,PubID);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					insertedEntity.add(ed);
					System.out.println("Insert editor");
					countAuthor++;
					countEntity++;
				}
				// Link Publication edited by Editor
				ee = new EntityDTO(countEntity+1, "E" + Long.toString(countEdge+1), "Edge", "DirectedLink", "edited by");
				try {
					entityDao.insertEntity(ee,PubID);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				insertedEntity.add(ee);
				pd = new GraphDTO(0, ep.getEntityID(), ee.getEntityID(), ed.getEntityID());
				try {
					graphDao.insertGraph(pd,PubID);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				countEdge++;
				countEntity++;
			}
		}
		// Extract Venue
		if (p.getJournal() != null && !p.getJournal().isEmpty()) {
			ev = new EntityDTO(countEntity+1, "V" + Long.toString(countVenue+1), "Node", "Venue", p.getJournal());
			if (EntityDTO.containsEntity(insertedEntity, ev)) {
				ev = EntityDTO.findEntity(insertedEntity, ev);
			}
			else {
				try {
					entityDao.insertEntity(ev,PubID);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				System.out.println("Insert venue");
				insertedEntity.add(ev);
				countVenue++;		
				countEntity++;
			}
			// Link Publication published in Venue
			ee = new EntityDTO(countEntity+1, "E" + Long.toString(countEdge+1), "Edge", "DirectedLink", "published in");
			try {
				entityDao.insertEntity(ee,PubID);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			insertedEntity.add(ee);
			pv = new GraphDTO(0, ep.getEntityID(), ee.getEntityID(), ev.getEntityID());
			try {
				graphDao.insertGraph(pv,PubID);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			countEdge++;
			countEntity++;
		}
	}
}


