package com.sixppl.dao;

import com.sixppl.dto.SessionDTO;


public interface SessionDAO {
	public  boolean addSession(SessionDTO session);
	public  boolean delSession(SessionDTO session);
	public  int finduserIDbySession(SessionDTO session);
	public  int findAdminUserIDbySession(SessionDTO session);
}
