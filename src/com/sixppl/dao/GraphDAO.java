package com.sixppl.dao;

import java.util.ArrayList;

import com.sixppl.dto.GraphDTO;
import com.sixppl.dto.GraphOutputDTO;

public interface GraphDAO {
	void insertGraph(GraphDTO graph);
	void updateGraph(GraphDTO graph);
	void deleteGraph(long ID);
	// ========== Note - Input Mapping for Graph ==========
	// Type = Publication/Author/School/Venue etc.
	// Keyword = Caption
	ArrayList<GraphDTO> findGraph(String type, String keyword);
	ArrayList<GraphOutputDTO> findGraphOutput(String type, String keyword);
}
