package com.sixppl.dao.support;

import com.sixppl.dao.DummyDAO;

public class DummyDAOImpl implements DummyDAO {
	private static final String HARDCODE_PWD_HASH = "$2a$10$MjqTcppNsgwONMZDlip7zuhZeFhtT3w.YSFHgyU7T3Xr6/AorZohy";
	public DummyDAOImpl() {
		
	}

	@Override
	public String getPasswordHash(String username) {
		return HARDCODE_PWD_HASH;
	}
}
