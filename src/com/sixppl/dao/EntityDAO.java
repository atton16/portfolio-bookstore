package com.sixppl.dao;

import com.sixppl.bean.EntityRecordBean;

public interface EntityDAO {
	void insertEntityRecord(EntityRecordBean entityrecord);
	void updateEntityRecord(EntityRecordBean entityrecord);
	void deleteEntityRecord(String ID);
	void findEntity(String entityAttribute, String entityValue);	// Attribute = type, Value = keyword
}
