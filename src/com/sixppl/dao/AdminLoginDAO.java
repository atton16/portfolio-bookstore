package com.sixppl.dao;

import com.sixppl.dto.*;

public interface AdminLoginDAO {
	
	public AdminLoginDTO getAdmin(Integer UserID);
	
	public boolean isLoggedIn(Integer UserID);
	
	public boolean login(Integer UserID, String SessionID);
	
	public boolean logout(String SessionID);
}
