package com.sixppl.dao;

import java.util.List;

import com.sixppl.bean.*;

import com.sixppl.bean.UserEntity;


public interface UserDAO {
	
	public Boolean addUser(UserEntity user);
	public Boolean updateUser(UserEntity user);
	public UserEntity findUserByName(String usrname);


}
