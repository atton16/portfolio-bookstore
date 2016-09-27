package com.sixppl.dao;
import java.util.*;
import com.sixppl.dto.*;

public interface AdminUserDAO {
	public List<UserDTO> findByNickname(String Nickname);
	
	public List<UserDTO> findByFirstname(String Firstname);
	
	public List<UserDTO> findByLastname(String Lastname);
	
	public List<UserDTO> findByEmail(String Email);
	
	public List<UserDTO> findAllCustomers();
	
	public List<UserDTO> findAllSellers();
}
