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

	public boolean contains(ArrayList<GraphOutputDTO> graphList, long ID) {
	    for (GraphOutputDTO graph : graphList) {
	        if (graph.getID() == ID) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public boolean contains(Stack<GraphOutputDTO> graphList, long ID) {
	    for (GraphOutputDTO graph : graphList) {
	        if (graph.getID() == ID) {
	            return true;
	        }
	    }
	    return false;
	}

	public GraphOutputDTO() {
		// TODO Auto-generated constructor stub
	}

}
