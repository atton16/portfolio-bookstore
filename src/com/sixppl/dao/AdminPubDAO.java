package com.sixppl.dao;
import java.util.*;

import org.json.simple.JSONArray;
import java.util.*;

public interface AdminPubDAO{
	public JSONArray findPub(Integer PubID);
	
	public Boolean removePub(Integer PubID, Integer SellerID);
}
