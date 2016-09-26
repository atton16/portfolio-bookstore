package com.sixppl.dao;

import com.sixppl.dto.AdminAnalyticsDTO;
import java.util.*;

public interface AdminAnalyticsDAO {
	public List<AdminAnalyticsDTO> getPageHits();
	public AdminAnalyticsDTO getUsersActive();
	public List<AdminAnalyticsDTO> getTopViewTitles();
	public List<AdminAnalyticsDTO> getTopAddedCart();
	
	public Boolean updatePageHits(String URI);
	public Boolean updateTopViewTitles(String Title);
	public Boolean updateTopAddedCart(String Title);
}
