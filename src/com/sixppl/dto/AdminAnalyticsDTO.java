package com.sixppl.dto;

public class AdminAnalyticsDTO {
	public String URI;
	public Integer PageHits;
	public Integer NumberUniqueIP;
	public Integer NumberActiveSessions;
	public String MostViewedPubTitle;
	public Integer MostViewCount;
	public String MostAddedCartPubTitle;
	public Integer MostAddedCount;
	
	public void setURI(String uri){
		this.URI=uri;
	}
	public String getURI(){
		return this.URI;
	}
	
	public void setPageHits(Integer hits){
		this.PageHits=hits;
	}
	
	public Integer getPageHits(){
		return this.PageHits;
	}
	
	public void setNumberUniqueIP(Integer nup){
		this.NumberUniqueIP=nup;
	}
	
	public Integer getNumberUniqueIP(){
		return this.NumberUniqueIP;
	}
	
	public void setNumberActiveSessions(Integer nas){
		this.NumberActiveSessions=nas;
	}
	
	public  Integer getNumberActiveSession(){
		return this.NumberActiveSessions;
	}
	
	public void setMostViewedPubTitle(String mvpt){
		this.MostViewedPubTitle=mvpt;
	}
	
	public String getMostViewedPubTitle(){
		return this.MostViewedPubTitle;
	}
	
	public void setMostViewCount(Integer mvc){
		this.MostViewCount=mvc;
	}
	
	public Integer getMostViewCount(){
		return this.MostViewCount;
	}
	
	public void setMostAddedToCartPubTitle(String matcpt){
		this.MostAddedCartPubTitle=matcpt;
	}
	
	public String getMostAddedToCartPubTitle(){
		return this.MostAddedCartPubTitle;
	}
	
	public void setMostAddedCount(Integer mac){
		this.MostAddedCount=mac;
	}
	
	public Integer getMostAddedCount(){
		return this.MostAddedCount;
	}
}
