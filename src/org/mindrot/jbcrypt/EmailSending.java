package org.mindrot.jbcrypt;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailSending {




    public void sendemail(String to, String from, String content){
        // Sender's email ID needs to be mentioned
    	// Sender's email ID needs to be mentioned

        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";
        // Get system properties
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        // Setup mail server
//        properties.setProperty("mail.smtp.host", host);
//        properties.setProperty("mail.user", "soagroupsunny");
//        properties.setProperty("mail.password", "soa12341234");
        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // Set Subject: header field
            message.setSubject("This is a job feeding from FoundIT Server.");
            // Send the actual HTML message, as big as you like
            message.setContent(content, "text/html");
            // Send message
            Transport transport = session.getTransport("smtp");
            transport.connect(host,"foundITManager", "soasoasoa");
            transport.sendMessage(message, message.getAllRecipients());
//            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

     public void sendEmail(String to, String from, String content){
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
 			message.setSubject("Confirm by DBLP");
 			message.setText("Dear Customer,"
 				+ "\n\n please confirm your registration, please!");
 			message.setContent(content, "text/html");
 			System.out.println("before send message");
 			Transport.send(message);
 			//Transport.sendMessage(message, message.getAllRecipients());
//          Transport.send(message);
 			System.out.println("Done");

 		} catch (MessagingException e) {
 			throw new RuntimeException(e);
 		}
 	}
}
