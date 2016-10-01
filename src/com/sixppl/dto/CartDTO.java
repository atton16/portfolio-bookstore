package com.sixppl.dto;

import java.sql.Timestamp;

public class CartDTO {
	int pubID;
	int userID;
	Timestamp addtime;
	Timestamp removetime;
	
	public CartDTO(){
		this.pubID = 0;
		this.userID = 0;
		this.addtime = null;
		this.removetime = null;
	}
	
	public void setAttributes(int pubID,int userID,Timestamp addtime,Timestamp removetime){
		this.pubID = pubID;
		this.userID = userID;
		this.addtime = addtime;
		this.removetime = removetime;
	}
	
	public int getPubID(){
		return pubID;
	}
	public int getUserID(){
		return userID;
	}
	public Timestamp getAddtime(){
		return addtime;
	}
	public Timestamp getRemovetime(){
		return removetime;
	}
}
