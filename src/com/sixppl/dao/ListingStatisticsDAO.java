package com.sixppl.dao;

import java.util.List;

import com.sixppl.dto.ListingDTO;

public interface ListingStatisticsDAO {
	public void incrementMostAddedToCart(Integer pubID);
	public void incrementMostViewed(Integer pubID);
	public List<ListingDTO> getMostAddedToCart(Integer limit);
	public List<ListingDTO> getMostViewed(Integer limit);
}
