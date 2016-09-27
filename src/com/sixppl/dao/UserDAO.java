package com.sixppl.dao;

import com.sixppl.dto.UserDTO;


public interface UserDAO {
	
	public boolean addUser(UserDTO user);
	public boolean updateUser(UserDTO user);
	public UserDTO findUserByName(String username);
	UserDTO findUserByToken(String token);
	UserDTO findUserByEmail(String email);
	UserDTO findUserByUserID(int userId);


}
