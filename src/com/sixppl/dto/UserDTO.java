package com.sixppl.dto;

public class UserDTO {
	private int userID;
    private String username = "";
    private String password = "";
    private String nickname = "";
    private String firstname = "";
    private String lastname = "";
    private String email = "";
    private String newemail = "";
    private Boolean isBanned;
    private Boolean isAdmin;
    private Boolean isCustomer;
    public String getNewemail() {
		return newemail;
	}
	public void setNewemail(String newemail) {
		this.newemail = newemail;
	}
	public String getTokenstring() {
		return tokenstring;
	}
	public void setTokenstring(String tokenstring) {
		this.tokenstring = tokenstring;
	}
	private int birthyear ;
    private String addr = "";
    private String cardno = "";
    private String tokenstring = "";



	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDTO(int userID, String username, String password,
			String nickname, String firstname, String lastname, String email,
			int birthyear, String addr, String cardno) {
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
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public UserDTO() {
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
	public int getBirthyear() {
		return birthyear;
	}
	public void setBirthyear(int birthyear) {
		this.birthyear = birthyear;
	}
	public void setIsBanned(Boolean flag) {
		this.isBanned = flag;
	}
	public Boolean isBanned(){
		return this.isBanned;
	}
	public void setIsAdmin(Boolean flag) {
		this.isAdmin = flag;
	}
	public Boolean isAdmin(){
		return this.isAdmin;
	}
	public void setIsCustomer(Boolean flag) {
		this.isCustomer = flag;
	}
	public Boolean isCustomer(){
		return this.isCustomer;
	}
	

}
