package com.sixppl.dao;

public interface AdminUserBanDAO {
	
	public boolean isBanned(Integer UserID);
	
	public boolean ban(Integer UserID);
	
	public boolean unban(Integer UserID);
}
