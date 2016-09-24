package com.sixppl.dao;

import java.util.ArrayList;

import com.sixppl.dto.ListingDTO;

public interface ListingDAO {

	public ArrayList<ListingDTO> emptySearch();
	public ArrayList<ListingDTO> Search(ListingDTO pubKey);
	public boolean addListing(ListingDTO pubSell);
	public void setList(int pubID);
	public void setUnlist(int pubID);
}
