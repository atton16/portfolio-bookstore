package com.sixppl.dto;

public class GraphDTO {
	private long ID;
	private String nodeFrom;
	private String edge;
	private String nodeTo;
	
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

	public GraphDTO() {
		// TODO Auto-generated constructor stub
	}

}
