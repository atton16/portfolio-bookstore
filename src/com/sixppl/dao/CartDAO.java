package com.sixppl.dao;

import java.util.ArrayList;

import com.sixppl.dto.ListingDTO;

public interface CartDAO {

	public ArrayList<ListingDTO> viewCart(int userID);
}
