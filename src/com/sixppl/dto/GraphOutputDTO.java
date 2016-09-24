package com.sixppl.dto;

import java.util.ArrayList;
import java.util.Stack;

public class GraphOutputDTO {
	private long ID;
	private long nodeFromID;
	private String nodeFrom;
	private String nodeFromCaption;
	private long edgeID;
	private String edge;
	private String edgeCaption;
	private long nodeToID;
	private String nodeTo;
	private String nodeToCaption;
	
	public GraphOutputDTO() {
		this.ID = 0;
		this.nodeFromID = 0;
		this.nodeFrom = null;
		this.nodeFromCaption = null;
		this.edgeID = 0;
		this.edge = null;
		this.edgeCaption = null;
		this.nodeToID = 0;
		this.nodeTo = null;
		this.nodeToCaption = null;
	}
	
	public GraphOutputDTO(long ID, long nodeFromID, String nodeFrom, String nodeFromCaption,
			long edgeID, String edge, String edgeCaption,
			long nodeToID, String nodeTo, String nodeToCaption) {
		this.ID = ID;
		this.nodeFromID = nodeFromID;
		this.nodeFrom = nodeFrom;
		this.nodeFromCaption = nodeFromCaption;
		this.edgeID = edgeID;
		this.edge = edge;
		this.edgeCaption = edgeCaption;
		this.nodeToID = nodeToID;
		this.nodeTo = nodeTo;
		this.nodeToCaption = nodeToCaption;
	}
	
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public long getNodeFromID() {
		return nodeFromID;
	}

	public void setNodeFromID(long nodeFromID) {
		this.nodeFromID = nodeFromID;
	}

	public String getNodeFrom() {
		return nodeFrom;
	}

	public void setNodeFrom(String nodeFrom) {
		this.nodeFrom = nodeFrom;
	}

	public String getNodeFromCaption() {
		return nodeFromCaption;
	}

	public void setNodeFromCaption(String nodeFromCaption) {
		this.nodeFromCaption = nodeFromCaption;
	}

	public long getEdgeID() {
		return edgeID;
	}

	public void setEdgeID(long edgeID) {
		this.edgeID = edgeID;
	}

	public String getEdge() {
		return edge;
	}

	public void setEdge(String edge) {
		this.edge = edge;
	}

	public String getEdgeCaption() {
		return edgeCaption;
	}

	public void setEdgeCaption(String edgeCaption) {
		this.edgeCaption = edgeCaption;
	}

	public long getNodeToID() {
		return nodeToID;
	}

	public void setNodeToID(long nodeToID) {
		this.nodeToID = nodeToID;
	}

	public String getNodeTo() {
		return nodeTo;
	}

	public void setNodeTo(String nodeTo) {
		this.nodeTo = nodeTo;
	}

	public String getNodeToCaption() {
		return nodeToCaption;
	}

	public void setNodeToCaption(String nodeToCaption) {
		this.nodeToCaption = nodeToCaption;
	}

	public static boolean containsID(ArrayList<GraphOutputDTO> graphList, long ID) {
	    for (GraphOutputDTO graph : graphList) {
	        if (graph.getID() == ID) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean containsID(Stack<GraphOutputDTO> graphList, long ID) {
	    for (GraphOutputDTO graph : graphList) {
	        if (graph.getID() == ID) {
	            return true;
	        }
	    }
	    return false;
	}
}
