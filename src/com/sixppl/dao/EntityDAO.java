package com.sixppl.dao;

import java.util.ArrayList;

import com.sixppl.bean.EntityBean;

public interface EntityDAO {
	void insertEntity(EntityBean entity);
	void updateEntity(EntityBean entity);
	void deleteEntity(long ID);
	ArrayList<EntityBean> findEntity(String type, String keyword);	// Type = Publication/Author/School/Venue etc., Keyword = Caption
}