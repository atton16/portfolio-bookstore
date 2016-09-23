package com.sixppl.dao;

import java.util.ArrayList;
import com.sixppl.dto.EntityDTO;

public interface EntityDAO {
	void insertEntity(EntityDTO entity);
	void updateEntity(EntityDTO entity);
	void deleteEntity(long ID);
	ArrayList<EntityDTO> findEntity(String type, String keyword);	// Type = Publication/Author/School/Venue etc., Keyword = Caption
}