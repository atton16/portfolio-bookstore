package com.sixppl.dao;
import java.util.*;
import com.sixppl.dto.*;

public interface AdminUserDAO {
	public List<UserDTO> findByNickname(String Nickname, int offset, int limit);
	
	public List<UserDTO> findByFirstname(String Firstname, int offset, int limit);
	
	public List<UserDTO> findByLastname(String Lastname, int offset, int limit);
	
	public List<UserDTO> findByEmail(String Email, int offset, int limit);
	
	public List<UserDTO> findAllCustomers(int offset, int limit);
	
	public List<UserDTO> findAllSellers(int offset, int limit);
}
