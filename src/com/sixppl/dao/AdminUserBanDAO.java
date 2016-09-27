package com.sixppl.dao;

public interface AdminUserBanDAO {
	
	public boolean isBanned(Integer UserID);
	
	public boolean userBan(Integer UserID);
	
	public boolean userUnban(Integer UserID);
}
