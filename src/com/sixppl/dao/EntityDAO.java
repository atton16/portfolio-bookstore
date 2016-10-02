package com.sixppl.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.sixppl.dto.EntityDTO;

public interface EntityDAO {
	void dropEntityTable() throws SQLException;
	void createEntityTable() throws SQLException;
	void insertEntity(EntityDTO entity, long PubID) throws SQLException;
	void updateEntity(EntityDTO entity, long PubID) throws SQLException;
	void deleteEntity(long PubID) throws SQLException;
	EntityDTO findEntityByEntityId(String entityID) throws SQLException;
	EntityDTO findEntityByCaption(String Class,String Type,String Caption) throws SQLException;
	ArrayList<EntityDTO> findEntity(String type, String keyword) throws SQLException;	// Type = Publication/Author/School/Venue etc., Keyword = Caption
	ArrayList<String> findLinkedEntity(String node) throws SQLException;
	ArrayList<EntityDTO> findAllNodes() throws SQLException;
	ArrayList<EntityDTO> getRandomNodes(int nodeAmount) throws SQLException;
	long getMaxNodeID(String Type) throws SQLException;
	long getMaxEdgeID() throws SQLException;
}