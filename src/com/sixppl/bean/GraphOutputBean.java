package com.sixppl.bean;

import java.util.ArrayList;

import com.sixppl.dto.GraphOutputDTO;

public class GraphOutputBean {
	private ArrayList<GraphOutputDTO> graphOutputList = new ArrayList<GraphOutputDTO>();

	public ArrayList<GraphOutputDTO> getGraphOutputList() {
		return graphOutputList;
	}

	public void setGraphOutputList(ArrayList<GraphOutputDTO> graphOutputList) {
		this.graphOutputList = graphOutputList;
	}

	public GraphOutputBean() {
		// TODO Auto-generated constructor stub
		this.graphOutputList = new ArrayList<GraphOutputDTO>();
	}

}
