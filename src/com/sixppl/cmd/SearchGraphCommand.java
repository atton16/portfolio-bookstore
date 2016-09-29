package com.sixppl.cmd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sixppl.dao.EntityDAO;
import com.sixppl.dao.GraphDAO;
import com.sixppl.dto.EntityDTO;
import com.sixppl.dto.GraphOutputDTO;
import com.sixppl.main.Application;

public class SearchGraphCommand implements Command {

	private String data = "{\"nodes\": [],\"edges\": []}";
	private String matchedNode = "{\"nodes\": []}";
	private EntityDAO entityDao;
	private GraphDAO graphDao;
	
	public SearchGraphCommand() {
		entityDao = Application.getSharedInstance().getDAOFactory().getEntityDAO();
		graphDao = Application.getSharedInstance().getDAOFactory().getGraphDAO();
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
	// CONFIG - Set node amount to randomly selected here
		int RANDOM_SELECT = 3;
	// DEBUG search input
		//System.out.println("graph input: type = "+type+"; keyword = "+keyword);
		ArrayList<EntityDTO> nodes = new ArrayList<EntityDTO>();
		ArrayList<GraphOutputDTO> edges = new ArrayList<GraphOutputDTO>();
		ArrayList<EntityDTO> keywordnodes = new ArrayList<EntityDTO>();
	// 1. Get all possible nodes by keyword
		// Home - Display randomly <RANDOM_SELECT> nodes and their relations
		if(type == null) {
			try {
				keywordnodes = entityDao.getRandomNodes(RANDOM_SELECT);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// When specify input or submit
		else {
			if(keyword != null) {
				keyword = keyword.toLowerCase();
			}
			try {
				keywordnodes = entityDao.findEntity(type, keyword);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Stack<String> queryList = new Stack<String>();
	// 2. Add all possible nodes by keyword to result node list
		if (!keywordnodes.isEmpty()) {
			for (EntityDTO node : keywordnodes) {
				queryList.push(node.getEntityID());
				nodes.add(node);
			}
		// Found at least 1 node by keyword
			if (!queryList.isEmpty()) {
	// 3. Find all reachable nodes
				while (!queryList.isEmpty()) {
					String query = queryList.pop();
					ArrayList<String> tempNodeIDList = new ArrayList<String>();
					try {
						tempNodeIDList = entityDao.findLinkedEntity(query);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					for (String tempNodeID : tempNodeIDList) {
						// add new related nodes into result node list
						if (!EntityDTO.containsEntityID(nodes,tempNodeID)) {
							EntityDTO tempNode = new EntityDTO();
							try {
								tempNode = entityDao.findEntityByEntityId(tempNodeID);
							} catch (SQLException e) {
								e.printStackTrace();
							}
							if (tempNode.getEntityID() != null) {
								nodes.add(tempNode);
							}
						}
					}
				}
	// 4. Find all edges from node list
				for (EntityDTO node : nodes) {
					ArrayList<GraphOutputDTO> tempEdgeList = new ArrayList<GraphOutputDTO>();
					try {
						tempEdgeList = graphDao.findGraphOutput(node.getEntityID());
					} catch (SQLException e) {
						e.printStackTrace();
					}
					for (GraphOutputDTO edge : tempEdgeList) {
					// add new edges into result edge list
						if (!GraphOutputDTO.containsID(edges, edge.getID())) {
							edges.add(edge);
						}
					}
				}
			}
		}
	// 5. Export output as GraphJSON format
		// 5.1 All keyword nodes network
		data = "{ \"nodes\": [";
		Iterator<EntityDTO> iterN = nodes.iterator();
		while (iterN.hasNext()) {
			EntityDTO node = iterN.next();
			data += "{";
			data += "\"id\": \"";
			data += node.getID();
			data += "\",\"label\": \"";
			data += node.getEntityType();
			data += "\",\"caption\": \"";
			data += node.getEntityCaption().replace("\"", "\\\"");
			if (iterN.hasNext()) {
				data += "\"},";
			}
			else {
				data += "\"}";
			}
		}
		data += "],";
		data += "\"edges\": [";
		Iterator<GraphOutputDTO> iterE = edges.iterator();
		while (iterE.hasNext()) {
			GraphOutputDTO edge = iterE.next();
			data += "{";
			data += "\"source\": \"";
			data += edge.getNodeFromID();
			data += "\",\"target\": \"";
			data += edge.getNodeToID();
			data += "\",\"caption\": \"";
			data += edge.getEdgeCaption();
			if (iterE.hasNext()) {
				data += "\"},";
			}
			else {
				data += "\"}";
			}
		}
		data += "]}";
		// 5.2 Keyword nodes only
		matchedNode = "{ \"nodes\": [";
		Iterator<EntityDTO> iterK = keywordnodes.iterator();
		while (iterK.hasNext()) {
			EntityDTO node = iterK.next();
			matchedNode += "{";
			matchedNode += "\"id\": \"";
			matchedNode += node.getID();
			matchedNode += "\",\"label\": \"";
			matchedNode += node.getEntityType();
			matchedNode += "\",\"caption\": \"";
			matchedNode += node.getEntityCaption().replace("\"", "\\\"");
			if (iterK.hasNext()) {
				matchedNode += "\"},";
			}
			else {
				matchedNode += "\"}";
			}
		}
		matchedNode += "]}";
	// DEBUG for checking JSON output
		//System.out.println(data);
		//System.out.println(matchedNode);
		request.setAttribute("data", data);
		request.setAttribute("matchedNode", matchedNode);
	}
}
