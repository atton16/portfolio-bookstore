package com.sixppl.dto;

import java.util.ArrayList;
import java.util.Stack;

public class GraphDTO {
	private long ID;
	private String nodeFrom;
	private String edge;
	private String nodeTo;
	
	private ArrayList<GraphDTO> edgeList;
	private Stack<GraphDTO> edgeStack;
	
	public GraphDTO() {
		// TODO Auto-generated constructor stub
		this.ID = 0;
		this.nodeFrom = null;
		this.edge = null;
		this.nodeTo = null;
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
	
	public ArrayList<GraphDTO> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(ArrayList<GraphDTO> edgeList) {
		this.edgeList = edgeList;
	}

	public Stack<GraphDTO> getEdgeStack() {
		return edgeStack;
	}

	public void setEdgeStack(Stack<GraphDTO> edgeStack) {
		this.edgeStack = edgeStack;
	}

	public static boolean containsID(ArrayList<GraphDTO> graphList, long ID) {
	    for (GraphDTO graph : graphList) {
	        if (graph.getID() == ID) {
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
}
