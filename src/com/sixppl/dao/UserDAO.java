package com.sixppl.dao;

import java.util.List;

import com.sixppl.dto.UserDTO;


public interface UserDAO {
	
	public boolean addUser(UserDTO user);
	public boolean updateUser(UserDTO user);
	public UserDTO findUserByName(String usrname);
	UserDTO findUserByToken(String token);


}
