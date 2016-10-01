package com.sixppl.dto;

public class PageHitsDTO {
	private String title;
	private Integer hitCount;
	public PageHitsDTO() {
		this.title = null;
		this.hitCount = 0;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setHitCount(Integer hitCount) {
		this.hitCount = hitCount;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public Integer getHitCount() {
		return this.hitCount;
	}
}
