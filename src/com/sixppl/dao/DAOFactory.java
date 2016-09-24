package com.sixppl.dao;

import java.util.HashMap;
import java.util.Map;

import com.sixppl.dao.support.*;

public class DAOFactory {
	private static final String DUMMY_DAO = "dummyDAO";
	private static final String FOO_DAO = "fooDAO";
	private static final String USER_DAO = "userDAO";
	private static final String SESSION_DAO = "sessionDAO";
	
	private Map<String, Object> daos;
	
	public DAOFactory() {
		daos = new HashMap<String, Object>();
		daos.put(DUMMY_DAO, new DummyDAOImpl());
		daos.put(FOO_DAO, new FooDAOImpl());
		daos.put(USER_DAO, new UserDAOImpl());
		daos.put(SESSION_DAO, new SessionDAOImpl());
	}
	
	public DummyDAO getDummyDAO() {
		return (DummyDAO) daos.get(DUMMY_DAO);
	}
	
	public FooDAO getFooDAO() {
		return (FooDAO) daos.get(FOO_DAO);
	}
	public UserDAO getUserDAO() {
		return (UserDAO) daos.get(USER_DAO);
	}
	public SessionDAO getSessionDAO(){
		return (SessionDAO) daos.get(SESSION_DAO);
	}
}
