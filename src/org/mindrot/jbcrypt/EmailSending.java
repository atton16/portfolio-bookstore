package org.mindrot.jbcrypt;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSending {

    public String createRSSContent(String content){
        StringBuilder builder = new StringBuilder();
        //header
        builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n ");
        builder.append("<rss version=\"2.0\">\n");
        builder.append("<channel>\n");
        //title
        builder.append("<h3>\n");
        builder.append("FoundIT Job Feeding.\n");
        builder.append("</h3>\n");
        builder.append("<link>http://localhost:8080/foundITApp/</link>\n");
        builder.append("<language>en-us</language>\n");
        //content
        builder.append(content);
        //bottom
        builder.append("</channel>\n");
        builder.append("</rss>\n");

        return builder.toString();
    }


    public void sendEmail(String to, String from, String content){
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

    public  String getDefaultConent(){
        StringBuilder builder = new StringBuilder();
        builder.append("<item>\n");
        builder.append("<title>One thing not to do before a presentation</title>\n");
        builder.append("<link>http://www.javablogs.com/Jump.jspa?id=20740</link>\n");
        builder.append("<description>Just a helpful hint:...</description>\n");
        builder.append("</item>\n");
        return builder.toString();
    }

}
