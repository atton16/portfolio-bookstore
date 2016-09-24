package com.sixppl.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.sixppl.dto.GraphDTO;
import com.sixppl.dto.GraphOutputDTO;

public interface GraphDAO {
	void dropTable() throws SQLException;
	void createTable() throws SQLException;
	void insertGraph(GraphDTO graph) throws SQLException;
	void updateGraph(GraphDTO graph) throws SQLException;
	void deleteGraph(long ID) throws SQLException;
	// ========== Note - Input Mapping for Graph ==========
	// Type = Publication/Author/School/Venue etc.
	// Keyword = Caption
	ArrayList<GraphOutputDTO> findGraphOutput(String node) throws SQLException;
}
