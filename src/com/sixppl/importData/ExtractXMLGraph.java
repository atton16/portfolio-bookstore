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
		// TODO Auto-generated constructor stub
	}
	
	// Randomly select 100 publications from XML file and insert into database
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
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
				ArrayList<PublicationDTO> random_publications = new ArrayList<PublicationDTO>(); // search results
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
					ep.setEntityID("P"+ Long.toString(countPublication+1));
					ep.setEntityClass("Node");
					ep.setEntityType("Publication");
					ep.setEntityCaption(p.getTitle());
					dao.insertEntity(ep);
					countPublication++;
					// Extract School
					if (p.getSchool() != null) {
						
						es.setEntityID("S" + Long.toString(countSchool+1));
						es.setEntityClass("Node");
						es.setEntityType("School");
						es.setEntityCaption(p.getSchool());
						dao.insertEntity(es);
						countSchool++;
					}
					// Extract author
					if (!p.getAuthor().isEmpty()){
						for (String author : p.getAuthor()) {
							ea.setEntityID("A" + Long.toString(countAuthor+1));
							ea.setEntityClass("Node");
							ea.setEntityType("Author");
							ea.setEntityCaption(author);
							dao.insertEntity(ea);
							countAuthor++;
							// Link Publication authored by Author
							ee.setEntityID("E" + Long.toString(countEdge+1));
							ee.setEntityClass("Edge");
							ee.setEntityType("DirectedLink");
							ee.setEntityCaption("authored by");
							dao.insertEntity(ee);
							pa.setNodeFrom(ep.getEntityID());
							pa.setEdge(ee.getEntityID());
							pa.setNodeTo(ea.getEntityID());
							dao.insertGraph(pa);
							countEdge++;
							// Link Author affiliated in School 
							if(p.getSchool() != null) {
								ee.setEntityID("E" + Long.toString(countEdge+1));
								ee.setEntityClass("Edge");
								ee.setEntityType("DirectedLink");
								ee.setEntityCaption("affiliated in");
								dao.insertEntity(ee);
								as.setNodeFrom(ea.getEntityID());
								as.setEdge(ee.getEntityID());
								as.setNodeTo(es.getEntityID());
								dao.insertGraph(as);
								countEdge++;
							}
						}
					}
					// Extract Editor
					if (!p.getEditor().isEmpty()){
						for (String editor : p.getEditor()) {
							ed.setEntityID("A" + Long.toString(countAuthor+1));
							ed.setEntityClass("Node");
							ed.setEntityType("Author");
							ed.setEntityCaption(editor);
							dao.insertEntity(ed);
							countAuthor++;
							// Link Publication edited by Editor
							ee.setEntityID("E" + Long.toString(countEdge+1));
							ee.setEntityClass("Edge");
							ee.setEntityType("DirectedLink");
							ee.setEntityCaption("edited by");
							dao.insertEntity(ee);
							pd.setNodeFrom(ep.getEntityID());
							pd.setEdge(ee.getEntityID());
							pd.setNodeTo(ed.getEntityID());
							dao.insertGraph(pd);
							countEdge++;
						}
					}
					// Extract Venue
					if (p.getJournal() != null) {
						ev.setEntityID("V" + Long.toString(countVenue+1));
						ev.setEntityClass("Node");
						ev.setEntityType("Venue");
						ev.setEntityCaption(p.getJournal());
						dao.insertEntity(ev);
						countVenue++;
						// Link Publication published in Venue
						ee.setEntityID("E" + Long.toString(countEdge+1));
						ee.setEntityClass("Edge");
						ee.setEntityType("DirectedLink");
						ee.setEntityCaption("published in");
						dao.insertEntity(ee);
						pv.setNodeFrom(ep.getEntityID());
						pv.setEdge(ee.getEntityID());
						pv.setNodeTo(ev.getEntityID());
						dao.insertGraph(pv);
						countEdge++;
					}
				}
	}

}
