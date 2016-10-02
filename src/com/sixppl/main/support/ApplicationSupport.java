package com.sixppl.main.support;

public class ApplicationSupport {
	public static String RegistrationEmailSubject() {
		return "DBLP: Confirm Registration";
	}
	public static String ChangeEmailSubject() {
		return "DBLP: Email Cahange Confirmation";
	}
	
	public static String RegistrationEmailContent(String tokenLink) {
		return "Please click the link below to confirm your registration:<br>"+tokenLink;
	}
	public static String ChangeEmailContent(String tokenLink) {
		return "Please click the link below to confirm your email:<br>"+tokenLink;
	}
}
