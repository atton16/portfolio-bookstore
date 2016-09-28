package com.sixppl.importData;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sixppl.dto.EntityDTO;
import com.sixppl.dto.GraphDTO;

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
	// 2. Drop existing Table
		try {
			ImportGraph dao = new ImportGraph();
			dao.dropEntityTable();
			dao.dropGraphTable();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	// 3. Create Table
		try {
			ImportGraph dao = new ImportGraph();
			dao.createEntityTable();
			dao.createGraphTable();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	// 4. Insert into database
		long countPublication = 0;
		long countAuthor = 0;
		long countVenue = 0;
		long countSchool = 0;
		long countEdge = 0;
		long countEntity = 0;
		ArrayList<EntityDTO> insertedEntity = new ArrayList<EntityDTO>();
		for (PublicationDTO p : random_publications) {
			ImportGraph dao = new ImportGraph();
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
				dao.insertEntity(ep);
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
					dao.insertEntity(es);
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
		// Link Author affiliated in School 
					if(p.getSchool() != null) {
						ee = new EntityDTO(countEntity+1, "E" + Long.toString(countEdge+1), "Edge", "DirectedLink", "affiliated in");
						dao.insertEntity(ee);
						insertedEntity.add(ee);
						as = new GraphDTO(0, ea.getEntityID(), ee.getEntityID(), es.getEntityID());
						dao.insertGraph(as);
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
}
