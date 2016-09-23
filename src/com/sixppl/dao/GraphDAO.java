package com.sixppl.dao;

import java.util.ArrayList;

import com.sixppl.bean.GraphBean;
import com.sixppl.bean.GraphOutputBean;

public interface GraphDAO {
	void insertGraph(GraphBean graph);
	void updateGraph(GraphBean graph);
	void deleteGraph(long ID);
	// ========== Note - Input Mapping for Graph ==========
	// Type = Publication/Author/School/Venue etc.
	// Keyword = Caption
	ArrayList<GraphBean> findGraph(String type, String keyword);
	ArrayList<GraphOutputBean> findGraphOutput(String type, String keyword);
}
