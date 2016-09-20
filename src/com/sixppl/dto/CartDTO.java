package com.sixppl.dto;

import java.util.Date;

public class CartDTO {
	int pubID;
	int userID;
	long addtime;
	long removetime;
	Date addDate;
	Date removeDate;
	
	public CartDTO(){
		this.pubID = 0;
		this.userID = 0;
		this.addtime = 0;
		this.removetime = 0;
		this.addDate = new Date(addtime);
		this.removeDate = new Date(removetime);
	}
	
	public void setAttributes(int pubID,int userID,long addtime,long removetime){
		this.pubID = pubID;
		this.userID = userID;
		this.addtime = addtime;
		this.addDate = new Date(addtime);
		this.removeDate = new Date(removetime);
	}
	
	public int getPubID(){
		return pubID;
	}
	public int getUserID(){
		return userID;
	}
	public long getAddtime(){
		return addtime;
	}
	public long getRemovetime(){
		return removetime;
	}
	public Date getAddDate(){
		return addDate;
	}
	public Date getRemoveDate(){
		return removeDate;
	}
}
