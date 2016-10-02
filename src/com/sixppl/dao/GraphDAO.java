package com.sixppl.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.sixppl.dto.GraphDTO;
import com.sixppl.dto.GraphOutputDTO;

public interface GraphDAO {
	void dropGraphTable() throws SQLException;
	void createGraphTable() throws SQLException;
	void insertGraph(GraphDTO graph, long PubID) throws SQLException;
	void updateGraph(GraphDTO graph, long PubID) throws SQLException;
	void deleteGraph(long PubID) throws SQLException;
	ArrayList<GraphOutputDTO> findGraphOutput(String node) throws SQLException;
	ArrayList<GraphOutputDTO> findAllGraphOutput() throws SQLException;
	public ArrayList<String> findDuplicatedNodeTo(int pubID);
}
