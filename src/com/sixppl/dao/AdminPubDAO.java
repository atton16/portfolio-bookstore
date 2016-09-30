package com.sixppl.dao;

import org.json.simple.JSONArray;

public interface AdminPubDAO{
	public JSONArray findPub(Integer PubID);
	
	public Boolean removePub(Integer PubID);
}
