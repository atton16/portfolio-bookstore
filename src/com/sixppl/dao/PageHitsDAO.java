package com.sixppl.dao;

import com.sixppl.dto.PageHitsDTO;

import java.util.*;

public interface PageHitsDAO {
	public List<PageHitsDTO> getPageHits();
	public void incrementHitCount(String Title);
}
