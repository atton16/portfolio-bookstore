package com.sixppl.dto;

public class SessionDTO {

	int userID ;
	int sessionID ;
	public SessionDTO(){
		
	}
	public SessionDTO(int userID, int sessionID) {
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
	public int getSessionID() {
		return sessionID;
	}
	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

}
