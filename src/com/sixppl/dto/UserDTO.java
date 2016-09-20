package com.sixppl.dto;

public class UserDTO {
	private String userID = "";
    private String username = "";
    private String password = "";
    private String nickname = "";
    private String firstname = "";
    private String lastname = "";
    private String email = "";
    private String birthyear = "";
    private String addr = "";
    private String cardno = "";


	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDTO(String userID, String username, String password,
			String nickname, String firstname, String lastname, String email,
			String birthyear, String addr, String cardno) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.setBirthyear(birthyear);
		this.addr = addr;
		this.cardno = cardno;
	}
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getBirthyear() {
		return birthyear;
	}
	public void setBirthyear(String birthyear) {
		this.birthyear = birthyear;
	}

}
