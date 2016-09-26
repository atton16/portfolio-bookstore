package com.sixppl.dto;

import java.sql.*;

public class AdminLoginDTO {
	private Integer UserID;
	private Integer Level;
	private java.sql.Timestamp Timestamp;
	
	public void setUserID(Integer userID){
		this.UserID=userID;
	}
	
	public void setLevel(Integer level){
		this.Level=level;
	}
	
	public void setTimestamp(Timestamp timestamp){
		this.Timestamp=timestamp;
	}
	
	public Integer getUserID(){
		return this.UserID;
	}
	
	public Integer getLevel(){
		return this.Level;
	}
	
	public Timestamp getTimestamp(){
		return this.Timestamp;
	}
}
