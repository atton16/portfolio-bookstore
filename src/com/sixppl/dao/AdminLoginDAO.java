package com.sixppl.dao;

import com.sixppl.dto.*;

public interface AdminLoginDAO {
	//public AdminLoginDTO findAdmin(Integer UserID);
	
	public AdminLoginDTO isAnAdmin(Integer UserID);
	
	public Integer returnUID(String Username, String Password);
	
	public boolean isAdmin(Integer UserID);
	
	public boolean registerAdminSession(Integer UserID, String SessionID);
	
	public boolean removeAdminSession(String SessionID);
}
