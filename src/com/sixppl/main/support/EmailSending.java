package com.sixppl.main.support;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailSending {

     public void sendEmail(String to, String from, String subject, String content){
    	final String username = "asst2unsw@gmail.com";
 		final String password = "asst22016";

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
