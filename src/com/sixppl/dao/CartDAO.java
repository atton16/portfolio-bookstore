package com.sixppl.dao;

import java.util.*;
import com.sixppl.dto.*;

public interface CartDAO {

	public ArrayList<ListingDTO> viewCart(int userID);
	
	public List<CartDTO> getByUserID(int userID);
	
	public int addCart(int pubID,int userID);
	
	public void removeCart(int userID,ArrayList<Integer> pubIDs);
	
	public Boolean isInCart(int pubID, int userID);
	
	public void removeAll(int userID);
	
	public void removeAllMatchedPubID(int pubID);
}
