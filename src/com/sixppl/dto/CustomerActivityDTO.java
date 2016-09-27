package com.sixppl.dto;

public class CustomerActivityDTO {
	public String Title;
	public String Timestamp;
	public String Nickname;
	public int Price;

	public CustomerActivityDTO() {
		this.Title="";
		this.Timestamp="";
		this.Nickname="";
		this.Price=0;
	}

	public void setTitle(String title){
		this.Title=title;
	}

	public void setTimestamp(String timestamp){
		this.Timestamp=timestamp;
	}

	public void setNickname(String nickname){
		this.Nickname=nickname;
	}

	public void setPrice(Integer price){
		this.Price=price;
	}

	public String getTitle(){
		return this.Title;
	}
	public String getTimestamp(){
		return this.Timestamp;
	}
	public String getNickname(){
		return this.Nickname;
	}
	public Integer getPrice(){
		return this.Price;
	}
}
