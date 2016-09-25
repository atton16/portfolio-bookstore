package com.sixppl.dao;

import com.sixppl.dto.SessionDTO;
import com.sixppl.dto.UserDTO;


public interface SessionDAO {
	public  boolean addSession(SessionDTO session);
	public  boolean delSession(SessionDTO session);
	public  int finduserIDbySession(SessionDTO session);
}
