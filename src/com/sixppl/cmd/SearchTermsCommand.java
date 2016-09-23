package com.sixppl.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

public class SearchTermsCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> searchTerms = new ArrayList<String>();
		for (String[] valueArray : request.getParameterMap().values()) {
			for (String value : valueArray) {
				searchTerms.add(value);
			}
		}
		// ===== debug check URL path =====
		//System.out.println(request.getRequestURI().substring(request.getContextPath().length()));
		// graph function
		if (request.getRequestURI().substring(request.getContextPath().length()).equalsIgnoreCase("/graph")) {
			String type = request.getParameter("type");
			String keyword = request.getParameter("keyword");
			if(keyword != null) {
				keyword = keyword.toLowerCase();
			}
			// debug search input
			System.out.println("graph input: type = "+type+"; keyword = "+keyword);
			EntityDAO entity = new EntityDAOImpl();
			ArrayList<EntityDTO> keywordnodes = new ArrayList<EntityDTO>();
			ArrayList<EntityDTO> nodes = new ArrayList<EntityDTO>();
			// get all possible nodes by keyword
			keywordnodes = entity.findEntity(type, keyword);
			Stack<String> queryList = new Stack<String>();
			// add all possible nodes by keyword to result node list
			if (!keywordnodes.isEmpty()) {
				for (EntityDTO node : keywordnodes) {
					queryList.push(node.getEntityID());
					nodes.add(node);
				}
				// Found at least 1 node by keyword
				if (!queryList.isEmpty()) {
					// find all reachable nodes
					while (!queryList.isEmpty()) {
						String query = queryList.pop();
						ArrayList<String> tempNodeIDList = new ArrayList<String>();
						tempNodeIDList = entity.findLinkedEntity(query);
						for (String tempNodeID : tempNodeIDList) {
							// add new related nodes into result node list
							if (!nodes.contains(tempNodeID)) {
								EntityDTO tempNode = entity.findEntityByEntityId(tempNodeID);
								nodes.add(tempNode);
							}
						}
					}
					// find all edges from node list
					ArrayList<GraphOutputDTO> edges = new ArrayList<GraphOutputDTO>();
					for (EntityDTO node : nodes) {
						GraphDAO graph = new GraphDAOImpl();
						ArrayList<GraphOutputDTO> tempEdgeList = new ArrayList<GraphOutputDTO>();
						tempEdgeList = graph.findGraphOutput(node.getEntityID());
						for (GraphOutputDTO edge : tempEdgeList) {
							// add new edges into result edge list
							if (!edges.contains(edge.getID())) {
								edges.add(edge);
							}
						}
					}
				}
			}
		}
		request.setAttribute("searchTerms", searchTerms);
	}

}
