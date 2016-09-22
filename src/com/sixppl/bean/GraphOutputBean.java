package com.sixppl.bean;

public class GraphOutputBean {
	private long ID;
	private String nodeFrom;
	private String nodeFromCaption;
	private String edge;
	private String edgeCaption;
	private String nodeTo;
	private String nodeToCaption;
	
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

	public String getNodeFromCaption() {
		return nodeFromCaption;
	}

	public void setNodeFromCaption(String nodeFromCaption) {
		this.nodeFromCaption = nodeFromCaption;
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

	public GraphOutputBean() {
		// TODO Auto-generated constructor stub
	}

}
