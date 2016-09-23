package com.sixppl.bean;

import java.util.ArrayList;

import com.sixppl.dto.EntityDTO;

public class NodeBean {
	private ArrayList<EntityDTO> nodeList = new ArrayList<EntityDTO>();

	public ArrayList<EntityDTO> getNodeList() {
		return nodeList;
	}

	public void setNodeList(ArrayList<EntityDTO> nodeList) {
		this.nodeList = nodeList;
	}

	public NodeBean() {
		// TODO Auto-generated constructor stub
		this.nodeList = new ArrayList<EntityDTO>();
	}

}
