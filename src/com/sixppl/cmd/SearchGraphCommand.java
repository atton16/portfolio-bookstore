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

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String keyword = request.getParameter("keyword");
		if(keyword != null) {
			keyword = keyword.toLowerCase();
		}
	// !!!debug search input
		//System.out.println("graph input: type = "+type+"; keyword = "+keyword);
		EntityDAO entityDAO = new EntityDAOImpl();
		ArrayList<EntityDTO> keywordnodes = new ArrayList<EntityDTO>();
		ArrayList<EntityDTO> nodes = new ArrayList<EntityDTO>();
		ArrayList<GraphOutputDTO> edges = new ArrayList<GraphOutputDTO>();
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
					GraphDAO graphDAO = new GraphDAOImpl();
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
	// 5. Export output as GraphJSON format
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
				data += node.getEntityCaption();
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
		}
	// debug for checking JSON output
		//System.out.println(data);
		request.setAttribute("data", data);
	}
}
