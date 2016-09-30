package com.sixppl.dto;

public class AdminCartDTO {
	int userID;
	String title;
	long addtime;
	long removetime;

	
	public AdminCartDTO(){
		this.userID = 0;
		this.title = "";
		this.removetime = 0;
		this.addtime = 0;
	}
	
	public void setUserID(int UserID){
		this.userID=UserID;
	}
	
	public void setTitle(String Title){
		this.title=Title;
	}
	
	public void setAddTime(long AddTime){
		this.addtime=AddTime;
	}
	
	public void setRemoveTime(long RemoveTime){
		this.removetime=RemoveTime;
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
	public String getTitle(){
		return title;
	}

}
