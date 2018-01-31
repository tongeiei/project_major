package com.major.dao;

import java.io.Serializable;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.major.bean.UserBean;

public class MailSender implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7278201554025770035L;

	public void sendMail(UserBean bean) throws AddressException, MessagingException {
         System.out.println("test send mail");
		Properties props = System.getProperties();
		 props.put("mail.smtp.host", "smtp.gmail.com");
	        
	        
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.port", "465");
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
         props.put("mail.smtp.socketFactory.port", "465");
         props.put("mail.smtp.socketFactory.fallback", "false");
		

		Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(true);

		Message mailMessage = new MimeMessage(mailSession);

		mailMessage.setFrom(new InternetAddress("tongeiei101@gmail.com"));
		mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(bean.getEmail()));
		mailMessage.setContent("Welcome To Major Arena ", "text/html");
		mailMessage.setSubject("Major Arena");

		Transport transport = mailSession.getTransport("smtp");
		transport.connect("smtp.gmail.com", bean.getUser_name(), bean.getUser_pass());
		transport.sendMessage(mailMessage, mailMessage.getAllRecipients());

	}

}
