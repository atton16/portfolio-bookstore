package com.sixppl.dao;

import java.util.HashMap;
import java.util.Map;

import com.sixppl.dao.support.*;

public class DAOFactory {
	private static final String DUMMY_DAO = "dummyDAO";
	private static final String FOO_DAO = "fooDAO";
	
	private Map<String, Object> daos;
	
	public DAOFactory() {
		daos = new HashMap<String, Object>();
		daos.put(DUMMY_DAO, new DummyDAOImpl());
		daos.put(FOO_DAO, new FooDAOImpl());
	}
	
	public DummyDAO getDummyDAO() {
		return (DummyDAO) daos.get(DUMMY_DAO);
	}
	
	public FooDAO getFooDAO() {
		return (FooDAO) daos.get(FOO_DAO);
	}
}
