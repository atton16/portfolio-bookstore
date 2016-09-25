package com.sixppl.dto;

import java.util.ArrayList;
import java.util.Stack;

public class GraphDTO {
	private long ID;
	private String nodeFrom;
	private String edge;
	private String nodeTo;
	
	public GraphDTO() {
		this.ID = 0;
		this.nodeFrom = null;
		this.edge = null;
		this.nodeTo = null;
	}
	
	public GraphDTO(long ID, String nodeFrom, String edge, String nodeTo) {
		this.ID = ID;
		this.nodeFrom = nodeFrom;
		this.edge = edge;
		this.nodeTo = nodeTo;
	}
	
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNodeFrom() {
		return nodeFrom;
	}

	public void setNodeFrom(String nodeFrom) {
		this.nodeFrom = nodeFrom;
	}

	public String getEdge() {
		return edge;
	}

	public void setEdge(String edge) {
		this.edge = edge;
	}

	public String getNodeTo() {
		return nodeTo;
	}

	public void setNodeTo(String nodeTo) {
		this.nodeTo = nodeTo;
	}
	
	public static boolean containsID(ArrayList<GraphDTO> graphList, long ID) {
	    for (GraphDTO graph : graphList) {
	        if (graph.getID() == ID) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean containsEdgeID(ArrayList<GraphDTO> graphList, String edgeID) {
	    for (GraphDTO graph : graphList) {
	        if (graph.getEdge().equals(edgeID)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean containsID(Stack<GraphDTO> graphList, long ID) {
	    for (GraphDTO graph : graphList) {
	        if (graph.getID() == ID) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean containsEdgeID(Stack<GraphDTO> graphList, String edgeID) {
	    for (GraphDTO graph : graphList) {
	        if (graph.getEdge().equals(edgeID)) {
	            return true;
	        }
	    }
	    return false;
	}
}
