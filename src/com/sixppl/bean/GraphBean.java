package com.sixppl.bean;

import java.util.ArrayList;

import com.sixppl.dto.GraphDTO;

public class GraphBean {
	private ArrayList<GraphDTO> graphList = new ArrayList<GraphDTO>();
	
	public ArrayList<GraphDTO> getGraphList() {
		return graphList;
	}

	public void setGraphList(ArrayList<GraphDTO> graphList) {
		this.graphList = graphList;
	}

	public GraphBean() {
		// TODO Auto-generated constructor stub
		this.graphList = new ArrayList<GraphDTO>();
	}

}
