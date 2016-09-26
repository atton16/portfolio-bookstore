package com.sixppl.dao;

import com.sixppl.dto.*;
import java.util.*;
public interface AdminUserActivityDAO {
	public UserDTO getUserDetails(Integer UserID);
	
	public List<CustomerActivityDTO> getBuyingHistory(Integer UserID);
	
	public List<AdminCartDTO> getCartHistory(Integer UserID);
}
