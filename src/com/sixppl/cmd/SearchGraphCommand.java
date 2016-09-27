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
import com.sixppl.dao.support.EntityDAOImpl;
import com.sixppl.dao.support.GraphDAOImpl;
import com.sixppl.dto.EntityDTO;
import com.sixppl.dto.GraphOutputDTO;

public class SearchGraphCommand implements Command {

	String data = "{\"nodes\": [],\"edges\": []}";
	String matchedNode = "{\"nodes\": []}";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
	// !!!debug search input
		//System.out.println("graph input: type = "+type+"; keyword = "+keyword);
		EntityDAO entityDAO = new EntityDAOImpl();
		GraphDAO graphDAO = new GraphDAOImpl();
		ArrayList<EntityDTO> nodes = new ArrayList<EntityDTO>();
		ArrayList<GraphOutputDTO> edges = new ArrayList<GraphOutputDTO>();
		ArrayList<EntityDTO> keywordnodes = new ArrayList<EntityDTO>();
		if(type == null) {
	// Default - Display all nodes and edges
			try {
				nodes = entityDAO.findAllNodes();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				edges = graphDAO.findAllGraphOutput();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			if(keyword != null) {
				keyword = keyword.toLowerCase();
			}
	// 1. Get all possible nodes by keyword
			try {
				keywordnodes = entityDAO.findEntity(type, keyword);
			} catch (SQLException e) {
				e.printStackTrace();
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
							tempNodeIDList = entityDAO.findLinkedEntity(query);
						} catch (SQLException e) {
							e.printStackTrace();
						}
						for (String tempNodeID : tempNodeIDList) {
							// add new related nodes into result node list
							if (!EntityDTO.containsEntityID(nodes,tempNodeID)) {
								EntityDTO tempNode = new EntityDTO();
								try {
									tempNode = entityDAO.findEntityByEntityId(tempNodeID);
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
							tempEdgeList = graphDAO.findGraphOutput(node.getEntityID());
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
	// debug for checking JSON output
		//System.out.println(data);
		//System.out.println(matchedNode);
		request.setAttribute("data", data);
		request.setAttribute("matchedNode", matchedNode);
	}
}
