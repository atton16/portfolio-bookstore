package com.sixppl.dto;

public class ListingStatisticsDTO {
	private Integer pubID;
	private Integer addedToCart;
	private Integer viewed;
	public ListingStatisticsDTO() {
		pubID = 0;
		addedToCart = 0;
		viewed = 0;
	}
	
	public void setPubID(Integer pubID) {
		this.pubID = pubID;
	}
	
	public void setAddedToCart(Integer addedToCart) {
		this.addedToCart = addedToCart;
	}
	
	public void setViewed(Integer viewed) {
		this.viewed = viewed;
	}
	
	public Integer getPubID() {
		return pubID;
	}
	
	public Integer getAddedToCart() {
		return addedToCart;
	}
	
	public Integer getViewed() {
		return viewed;
	}
}
