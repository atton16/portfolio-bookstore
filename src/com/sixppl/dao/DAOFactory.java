package com.sixppl.dao;

import java.util.HashMap;
import java.util.Map;
import com.sixppl.dao.support.*;

public class DAOFactory {
	private static final String DUMMY_DAO = "dummyDAO";
	private static final String FOO_DAO = "fooDAO";
	private static final String USER_DAO = "userDAO";

	private static final String SESSION_DAO = "sessionDAO";

	private static final String ENTITY_DAO = "entityDAO";
	private static final String GRAPH_DAO = "graphDAO";

	private Map<String, Object> daos;
	
	public DAOFactory() {
		daos = new HashMap<String, Object>();
		daos.put(DUMMY_DAO, new DummyDAOImpl());
		daos.put(FOO_DAO, new FooDAOImpl());
		daos.put(USER_DAO, new UserDAOImpl());

		daos.put(SESSION_DAO, new SessionDAOImpl());

		daos.put(ENTITY_DAO, new EntityDAOImpl());
		daos.put(GRAPH_DAO, new GraphDAOImpl());

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

	public EntityDAO getEntityDAO() {
		return (EntityDAO) daos.get(ENTITY_DAO);
	}
	public GraphDAO getGraphDAO() {
		return (GraphDAO) daos.get(GRAPH_DAO);
	}
}
