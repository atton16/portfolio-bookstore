package com.sixppl.dao;

public interface AdminDAO {
	Boolean promote(int userId);
	Boolean demote(int userId, int promoterId);
	Integer getLevel(int userId);
}