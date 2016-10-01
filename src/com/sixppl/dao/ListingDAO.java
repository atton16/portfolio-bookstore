package com.sixppl.dao;

import java.util.ArrayList;
import java.util.List;

import com.sixppl.dto.ListingDTO;

public interface ListingDAO {

	public ArrayList<ListingDTO> emptySearch(String sessionId);
	public ArrayList<ListingDTO> Search(ListingDTO pubKey, String sessionId);// See only Listed
	public ArrayList<ListingDTO> searchAll(ListingDTO pubKey, String sessionId);// See Everything
	public boolean addListing(ListingDTO pubSell);
	public void setList(int pubID);
	public void setUnlist(int pubID);
	public Integer getListingCount();
	public Integer getListingCount(Integer userId);
	public List<Integer> getYearsAscending();
	public List<Integer> getYearsDescending();
	public int getTotal();
	public List<ListingDTO> getRandomPub(String sessionId);
}
