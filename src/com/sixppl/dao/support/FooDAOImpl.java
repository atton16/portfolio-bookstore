package com.sixppl.dao.support;

import com.sixppl.dao.FooDAO;

public class FooDAOImpl implements FooDAO {
	private static final String MY_TEXT = "Hello World";
	
	public FooDAOImpl() {
		
	}

	@Override
	public String getText() {
		return MY_TEXT;
	}

}
