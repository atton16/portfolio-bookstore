package com.sixppl.dao;

import java.util.List;

import com.sixppl.dto.UserDTO;


public interface UserDAO {
	
	public Boolean addUser(UserDTO user);
	public Boolean updateUser(UserDTO user);
	public UserDTO findUserByName(String usrname);


}
