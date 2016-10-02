package com.sixppl.main.support;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailSending {
	
	public static void main(String[] args) {
		new EmailSending().sendEmail("admin", "a@a.com", "test", "test");
	}

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
			Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	private static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		return matcher.find();
	}
	
     public void sendEmail(String to, String from, String subject, String content){
    	final String username = "asst2unsw@gmail.com";
 		final String password = "asst22016";
 		
 		if(!validate(from)){
 			System.out.println("EmailSending: Invalid sender email");
 			return;
 		}
 		if(!validate(to)){
 			System.out.println("EmailSending: Invalid receiver email");
 			return;
 		}

 		Properties props = new Properties();
 		props.put("mail.smtp.auth", "true");
 		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
 		props.put("mail.smtp.starttls.enable", "true");
 		props.put("mail.smtp.host", "smtp.gmail.com");
 		props.put("mail.smtp.port", "587");

 		Session session = Session.getInstance(props,
 		  new javax.mail.Authenticator() {
 			protected PasswordAuthentication getPasswordAuthentication() {
 				return new PasswordAuthentication(username, password);
 			}
 		  });

 		try {

 			Message message = new MimeMessage(session);
 			message.setFrom(new InternetAddress(from));
 			message.setRecipients(Message.RecipientType.TO,
 				InternetAddress.parse(to));
 			message.setSubject(subject);
 			message.setContent(content, "text/html; charset=utf-8");
 			System.out.println("EmailSending: Sending...");
 			Transport.send(message);
 			System.out.println("EmailSending: Done");

 		} catch (MessagingException e) {
 			throw new RuntimeException(e);
 		}
 	}
}
