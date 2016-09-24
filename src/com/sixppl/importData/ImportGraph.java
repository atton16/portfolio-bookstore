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

import com.sixppl.dao.support.EntityDAOImpl;
import com.sixppl.dao.support.GraphDAOImpl;
import com.sixppl.dto.EntityDTO;
import com.sixppl.dto.GraphDTO;

public class ImportGraph {

	public ImportGraph() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		// 1. Read XML
				NodeList nodeList = null;
				String xmlPath = "/Users/monai/Documents/workspace/asst2_importgraph/res/dblp.xml";
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
					EntityDAOImpl eDAO = new EntityDAOImpl();
					GraphDAOImpl gDAO = new GraphDAOImpl();
					eDAO.dropTable();
					gDAO.dropTable();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			// 3. Create Table
				try {
					EntityDAOImpl eDAO = new EntityDAOImpl();
					GraphDAOImpl gDAO = new GraphDAOImpl();
					eDAO.createTable();
					gDAO.createTable();
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
					EntityDAOImpl eDAO = new EntityDAOImpl();
					GraphDAOImpl gDAO = new GraphDAOImpl();
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
					eDAO.insertEntity(ep);
					countPublication++;
					// Extract School
					if (p.getSchool() != null) {
						
						es.setEntityID("S" + Long.toString(countSchool+1));
						es.setEntityClass("Node");
						es.setEntityType("School");
						es.setEntityCaption(p.getSchool());
						eDAO.insertEntity(es);
						countSchool++;
					}
					// Extract author
					if (!p.getAuthor().isEmpty()){
						for (String author : p.getAuthor()) {
							ea.setEntityID("A" + Long.toString(countAuthor+1));
							ea.setEntityClass("Node");
							ea.setEntityType("Author");
							ea.setEntityCaption(author);
							eDAO.insertEntity(ea);
							countAuthor++;
							// Link Publication authored by Author
							ee.setEntityID("E" + Long.toString(countEdge+1));
							ee.setEntityClass("Edge");
							ee.setEntityType("DirectedLink");
							ee.setEntityCaption("authored by");
							eDAO.insertEntity(ee);
							pa.setNodeFrom(ep.getEntityID());
							pa.setEdge(ee.getEntityID());
							pa.setNodeTo(ea.getEntityID());
							gDAO.insertGraph(pa);
							countEdge++;
							// Link Author affiliated in School 
							if(p.getSchool() != null) {
								ee.setEntityID("E" + Long.toString(countEdge+1));
								ee.setEntityClass("Edge");
								ee.setEntityType("DirectedLink");
								ee.setEntityCaption("affiliated in");
								eDAO.insertEntity(ee);
								as.setNodeFrom(ea.getEntityID());
								as.setEdge(ee.getEntityID());
								as.setNodeTo(es.getEntityID());
								gDAO.insertGraph(as);
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
							eDAO.insertEntity(ed);
							countAuthor++;
							// Link Publication authored by Editor
							ee.setEntityID("E" + Long.toString(countEdge+1));
							ee.setEntityClass("Edge");
							ee.setEntityType("DirectedLink");
							ee.setEntityCaption("edited by");
							eDAO.insertEntity(ee);
							pd.setNodeFrom(ep.getEntityID());
							pd.setEdge(ee.getEntityID());
							pd.setNodeTo(ed.getEntityID());
							gDAO.insertGraph(pd);
							countEdge++;
						}
					}
					// Extract Venue
					if (p.getJournal() != null) {
						ev.setEntityID("V" + Long.toString(countVenue+1));
						ev.setEntityClass("Node");
						ev.setEntityType("Venue");
						ev.setEntityCaption(p.getJournal());
						countVenue++;
						// Link Publication published in Venue
						ee.setEntityID("E" + Long.toString(countEdge+1));
						ee.setEntityClass("Edge");
						ee.setEntityType("DirectedLink");
						ee.setEntityCaption("published in");
						eDAO.insertEntity(ee);
						pv.setNodeFrom(ep.getEntityID());
						pv.setEdge(ee.getEntityID());
						pv.setNodeTo(ev.getEntityID());
						gDAO.insertGraph(pv);
						countEdge++;
					}
				}
	}

}
