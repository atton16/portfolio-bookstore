package com.sixppl.importData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sixppl.dto.EntityDTO;
import com.sixppl.dto.GraphDTO;
import com.sixppl.dto.ListingDTO;

public class ExtractXMLGraph {

	public ExtractXMLGraph() {
	}
	
	// Randomly select 100 publications from XML file and insert into database
	public static void main(String[] args) throws SQLException {
	// 1. Read XML
		NodeList nodeList = null;
		// !!!IMPORTANT - Update path to XML file before executing
		String xmlPath = "/Users/monai/Documents/workspace/asst2/res/dblp.xml";
		InputSource xmlFile = new InputSource(xmlPath);
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			nodeList = doc.getElementsByTagName("*");
			Node rootNode = nodeList.item(0);
			nodeList = rootNode.getChildNodes();
		// Remove non-element nodes
			for(int i = nodeList.getLength()-1; i >= 0; i--){
				if (nodeList.item(i).getNodeType() != Node.ELEMENT_NODE){
					nodeList.item(i).getParentNode().removeChild(nodeList.item(i));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<Integer> randomlist = new ArrayList<Integer>();
		for(int i = 0; i < nodeList.getLength(); i++){
			randomlist.add(i);
		}
		Collections.shuffle(randomlist);
	// Retrieve 100 random publications
		ArrayList<PublicationDTO> random_publications = new ArrayList<PublicationDTO>();
		for (int i = 0; i < 100; i++){
			PublicationHandler handler = new PublicationHandler();
			random_publications.add(handler.toPublication(nodeList.item(randomlist.get(i))));
		}
		ImportGraph dao = new ImportGraph();
	// 2. Drop existing Entity and Graph Table
		try {
			dao.dropEntityTable();
			dao.dropGraphTable();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	// 3. Create Entity and Graph Table
		try {
			dao.createEntityTable();
			dao.createGraphTable();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		long countPublication = dao.getMaxNodeID("Publication");
		long countAuthor = dao.getMaxNodeID("Author");
		long countVenue = dao.getMaxNodeID("Venue");
		long countSchool = dao.getMaxNodeID("School");
		long countEdge = dao.getMaxEdgeID();
		long countEntity = 0;
		ArrayList<EntityDTO> insertedEntity = new ArrayList<EntityDTO>();
		
		for (PublicationDTO p : random_publications) {
		// 4. Insert into Listing Table
			ListingDTO pubSell = new ListingDTO();
			int picId = (dao.getTotal()+1) % 20;
			if (picId == 0) {
				picId = 20;
			}
			if (p.getTitle().length() > 255) {
				pubSell.title = p.getTitle().substring(0, 255);
			}
			else {
				pubSell.title = p.getTitle();
			}
			for (String author : p.getAuthor()) {
				pubSell.authors.add(author);
			}
			if (pubSell.authors.isEmpty()) {
				pubSell.authors.add("");
			}
			for (String editor : p.getEditor()) {
				pubSell.editors.add(editor);
			}
			if (pubSell.editors.isEmpty()) {
				pubSell.editors.add("");
			}
			pubSell.type = p.getType();
			if (p.getYear() == null) {
				pubSell.year = 2016;
			}
			else {
				pubSell.year = Integer.parseInt(p.getYear());
			}
			if (p.getJournal() == null) {
				pubSell.venue = "";
			}
			else {
				pubSell.venue = p.getJournal();
			}
			pubSell.sellerID = 1;
			pubSell.picture = "/uploads/pic"+picId+".jpg";
			Random r = new Random();
			float random = (float) (r.nextInt(10000) / 100.00);
			pubSell.price = random;
			dao.addListing(pubSell);
		// 5. Insert into Entity and Graph Table
			long PubID = dao.getMaxPubID();
			EntityDTO ep = new EntityDTO();
			EntityDTO es = new EntityDTO();
			EntityDTO ea = new EntityDTO();
			EntityDTO ed = new EntityDTO();
			EntityDTO ev = new EntityDTO();
			EntityDTO ee = new EntityDTO();
			GraphDTO pa = new GraphDTO();
			GraphDTO pd = new GraphDTO();
			GraphDTO as = new GraphDTO();
			GraphDTO pv = new GraphDTO();
		// Extract publication
			ep = new EntityDTO(countEntity+1, "P"+ Long.toString(countPublication+1), "Node", "Publication", p.getTitle());
			if (EntityDTO.containsEntity(insertedEntity, ep)) {
				ep = EntityDTO.findEntity(insertedEntity, ep);
			}
			else {
				dao.insertEntity(ep,PubID);
				insertedEntity.add(ep);
				countPublication++;
				countEntity++;
			}
		// Extract School
			if (p.getSchool() != null) {
				es = new EntityDTO(countEntity+1, "S" + Long.toString(countSchool+1), "Node", "School", p.getSchool());
				if (EntityDTO.containsEntity(insertedEntity, es)) {
					es = EntityDTO.findEntity(insertedEntity, es);
				}
				else {
					dao.insertEntity(es,PubID);
					insertedEntity.add(es);
					countSchool++;
					countEntity++;
				}
			}
		// Extract author
			if (!p.getAuthor().isEmpty()){
				for (String author : p.getAuthor()) {
					ea = new EntityDTO(countEntity+1, "A" + Long.toString(countAuthor+1), "Node", "Author", author);
					if (EntityDTO.containsEntity(insertedEntity, ea)) {
						ea = EntityDTO.findEntity(insertedEntity, ea);
					}
					else {
						dao.insertEntity(ea,PubID);
						insertedEntity.add(ea);
						countAuthor++;
						countEntity++;
					}
		// Link Publication authored by Author
					ee = new EntityDTO(countEntity+1, "E" + Long.toString(countEdge+1), "Edge", "DirectedLink", "authored by");
					dao.insertEntity(ee,PubID);
					insertedEntity.add(ee);
					pa = new GraphDTO(0, ep.getEntityID(), ee.getEntityID(), ea.getEntityID());
					dao.insertGraph(pa,PubID);
					countEdge++;
					countEntity++;
		// Link Author affiliated in School 
					if(p.getSchool() != null) {
						ee = new EntityDTO(countEntity+1, "E" + Long.toString(countEdge+1), "Edge", "DirectedLink", "affiliated in");
						dao.insertEntity(ee,PubID);
						insertedEntity.add(ee);
						as = new GraphDTO(0, ea.getEntityID(), ee.getEntityID(), es.getEntityID());
						dao.insertGraph(as,PubID);
						countEdge++;
						countEntity++;
					}
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
						dao.insertEntity(ed,PubID);
						insertedEntity.add(ed);
						countAuthor++;
						countEntity++;
					}
		// Link Publication edited by Editor
					ee = new EntityDTO(countEntity+1, "E" + Long.toString(countEdge+1), "Edge", "DirectedLink", "edited by");
					dao.insertEntity(ee,PubID);
					insertedEntity.add(ee);
					pd = new GraphDTO(0, ep.getEntityID(), ee.getEntityID(), ed.getEntityID());
					dao.insertGraph(pd,PubID);
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
					dao.insertEntity(ev,PubID);
					insertedEntity.add(ev);
					countVenue++;		
					countEntity++;
				}
		// Link Publication published in Venue
				ee = new EntityDTO(countEntity+1, "E" + Long.toString(countEdge+1), "Edge", "DirectedLink", "published in");
				dao.insertEntity(ee,PubID);
				insertedEntity.add(ee);
				pv = new GraphDTO(0, ep.getEntityID(), ee.getEntityID(), ev.getEntityID());
				dao.insertGraph(pv,PubID);
				countEdge++;
				countEntity++;
			}
		}
	}
}
