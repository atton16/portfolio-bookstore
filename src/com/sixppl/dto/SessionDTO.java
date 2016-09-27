package com.sixppl.dto;

public class SessionDTO {

	int userID ;
	String sessionID ;
	public SessionDTO(){
		
	}
	public SessionDTO(int userID, String sessionID) {
		super();
		this.userID = userID;
		this.sessionID = sessionID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getSessionID() {
		return sessionID;
	}
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

}
