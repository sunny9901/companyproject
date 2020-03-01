package org.company.pkg;

//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.util.Date;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Email {
	
	public static void sendEmail
	(String host,String port,final String mail,String sender,String reciever,

			
			String subject,String message) throws Exception
	{
		
		
	
		
		
		

		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		
		Session session =Session.getInstance(properties);
		
		Message msg=new MimeMessage(session);
		
		msg.setFrom(new InternetAddress(mail,sender));
		
		InternetAddress[] toAddress= { new InternetAddress(reciever)};
		msg.setRecipients(Message.RecipientType.TO,toAddress);
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(message);
		Transport.send(msg);
		
		
		
		
		
		
		
	}
}
