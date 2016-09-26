package com.sixppl.dao;

public interface AdminUserBanDAO {
	
	public boolean checkBan(Integer UserID);
	
	public boolean userBan(Integer UserID);
	
	public boolean userUnban(Integer UserID);
}
