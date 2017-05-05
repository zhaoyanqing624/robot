package org.xjtusicd3.partner.filter;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ValidateEmail {
	/*
	 * 邮件的发送
	 */
    public static void validateEmail(String email,String username,String identification_number) throws Exception{
          String myEmailAccount = "sicd_xiaoduo@163.com";
          String myEmailPassword = "hyhzjz06030922";
          String myEmailSMTPHost = "smtp.163.com";
          String receiveMailAccount = email;
          Properties props = new Properties();                  
          props.setProperty("mail.transport.protocol", "smtp");   
          props.setProperty("mail.smtp.host", myEmailSMTPHost);   
          props.setProperty("mail.smtp.auth", "true");            
          Session session = Session.getDefaultInstance(props);
          session.setDebug(true);
          MimeMessage message2 = createMimeMessage2(session, myEmailAccount, myEmailAccount,username,identification_number);
          MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount,username,identification_number);
          Transport transport = session.getTransport();
          transport.connect(myEmailSMTPHost,myEmailAccount, myEmailPassword);
          transport.sendMessage(message2, message2.getAllRecipients());
          transport.sendMessage(message, message.getAllRecipients());
          
          transport.close();
    }
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String username,String identification_number) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "小朵", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "zhao用户", "UTF-8"));
        message.setSubject(username, "UTF-8");
        message.setContent("username", "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
    public static MimeMessage createMimeMessage2(Session session, String sendMail, String receiveMail,String username,String identification_number) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "小朵", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "zhao用户", "UTF-8"));
        message.setSubject(username, "UTF-8");
        message.setContent("username", "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
    
}
