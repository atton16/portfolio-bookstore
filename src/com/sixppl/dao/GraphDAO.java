package com.sixppl.dao;

import java.util.ArrayList;

import com.sixppl.bean.GraphBean;

public interface GraphDAO {
	void insertGraph(GraphBean graph);
	void updateGraph(GraphBean graph);
	void deleteGraph(long ID);
	ArrayList<GraphBean> findGraph(String type, String keyword);	// Type = Publication/Author/School/Venue etc., Keyword = Caption
}
