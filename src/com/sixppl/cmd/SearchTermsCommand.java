package com.sixppl.cmd;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
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
			//System.out.println("graph input: type = "+type+"; keyword = "+keyword);
			EntityDAO entity = new EntityDAOImpl();
			ArrayList<EntityDTO> keywordnodes = new ArrayList<EntityDTO>();
			ArrayList<EntityDTO> nodes = new ArrayList<EntityDTO>();
			ArrayList<GraphOutputDTO> edges = new ArrayList<GraphOutputDTO>();
			// get all possible nodes by keyword
			try {
				keywordnodes = entity.findEntity(type, keyword);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
						try {
							tempNodeIDList = entity.findLinkedEntity(query);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for (String tempNodeID : tempNodeIDList) {
							// add new related nodes into result node list
							if (!nodes.contains(tempNodeID)) {
								EntityDTO tempNode;
								try {
									tempNode = entity.findEntityByEntityId(tempNodeID);
									nodes.add(tempNode);
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
					// find all edges from node list
					for (EntityDTO node : nodes) {
						GraphDAO graph = new GraphDAOImpl();
						ArrayList<GraphOutputDTO> tempEdgeList = new ArrayList<GraphOutputDTO>();
						try {
							tempEdgeList = graph.findGraphOutput(node.getEntityID());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for (GraphOutputDTO edge : tempEdgeList) {
							// add new edges into result edge list
							if (!edges.contains(edge.getID())) {
								edges.add(edge);
							}
						}
					}
				}
				// print GraphJSON output
				System.out.println("{");
				System.out.println("\"nodes\": [");
				Iterator<EntityDTO> iterN = nodes.iterator();
				while (iterN.hasNext()) {
					EntityDTO node = iterN.next();
					System.out.println("{");
					System.out.println("\"id\": " + node.getID());
					System.out.println("\"label\": " + node.getEntityType());
					System.out.println("\"caption\": " + node.getEntityCaption());
					if (iterN.hasNext()) {
						System.out.println("},");
					}
					else {
						System.out.println("}");
					}
				}
				System.out.println("],");
				System.out.println("\"edges\": [");
				Iterator<GraphOutputDTO> iterE = edges.iterator();
				while (iterE.hasNext()) {
					GraphOutputDTO edge = iterE.next();
					System.out.println("{");
					System.out.println("\"source\": " + edge.getNodeFromID());
					System.out.println("\"target\": " + edge.getNodeToID());
					System.out.println("\"caption\": " + edge.getEdgeCaption());
					if (iterE.hasNext()) {
						System.out.println("},");
					}
					else {
						System.out.println("}");
					}
				}
				System.out.println("}");
			}
		}
		request.setAttribute("searchTerms", searchTerms);
	}

}
