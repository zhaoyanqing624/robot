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
          MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount,username,identification_number);
          Transport transport = session.getTransport();
          transport.connect(myEmailAccount, myEmailPassword);
          transport.sendMessage(message, message.getAllRecipients());
          transport.close();
    }
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail,String username,String identification_number) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "小朵", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "zhao用户", "UTF-8"));
        message.setSubject("验证消息", "UTF-8");
        message.setContent("<html><body><div id='contentDiv' onmouseover='getTop().stopPropagation(event);' onclick='getTop().preSwapLink(event, 'html', 'ZC4327-Fme2n5IvQObfQQJaxrMHG6b');' style='position:relative;font-size:14px;height:auto;padding:15px 15px 10px 15px;z-index:1;zoom:1;line-height:1.7;' class='body'><div id='qm_con_body'><div id='mailContentContainer' class='qmbox qm_con_body_content qqmail_webmail_only'><div style='padding:10px;'><table style=''><tbody><tr><td style='padding:39px 19px 30px;'><div style='padding-bottom:10px;border-bottom:4px solid #2a84d7;'><a><img src='http://localhost:8080/org.xjtusicd3.partner/images/logo.jpg' style='border:0;vertical-align:top;'></a></div></td></tr><tr><td style='padding:0 39px;'><table style='width:100%;margin:0;padding:0;border-collapse:collapse;border-spacing:0;color:#666;border:1px solid #aaa;background-color:#fff;'><tbody><tr><td style='padding:20px 18px 29px;font-size:20px;line-height:28px;'>"+receiveMail+"<span style='border-bottom: 1px dashed rgb(204, 204, 204); z-index: 1; position: static;' t='7' onclick='return false;' data='77528570'></span>您好！</td></tr><tr><td style='padding:0 18px 34px;font-size:16px;line-height:28px;'>恭喜你即将成为了xiaoduo的正式会员！<br><br>在xiaoduo，可通过知识库获取软硬件的各种信息，还能成为xiaoduo智囊团的一份子。<br>通过xiaoduo，尽享轻松愉快的办公吧~！</td></tr><tr><td style='padding:0 19px 19px;'><table style='width:100%;margin:0;padding:0;border-collapse:collapse;border-spacing:0;text-align:center;background-color:#ececec;'><tbody><tr><td style='padding:34px 50px 10px;font-weight:bold;font-size:18px;line-height:28px;color:#1b62ab;'>为确保账号安全，请务必认证邮箱！</td></tr><tr><td style='padding:0 50px 34px;font-size:16px;line-height:28px;color:#666;'>请在5分钟内完成验证。</td></tr><tr><td style='padding:0 50px 40px;text-align:center;'><div style='width:202px;height:48px;margin:0 auto;line-height:48px;'><a href='http://localhost:8080/org.xjtusicd3.partner/valiadateEmail.html?e="+receiveMail+"&p="+identification_number+"' style='width:200px;height:46px;display:block;margin:0 auto;border:1px solid #0e4a87;background-color:#1b62ab;-webkit-border-radius:3px;border-radius:3px;-webkit-box-shadow:0 2px 3px rgba(0,0,0,0.16);box-shadow:0 2px 3px rgba(0,0,0,0.16);vertical-align:top;text-decoration:none;color:#fff;font-size:18px;font-weight:bold;line-height:48px;' target='_blank'>点击此处验证</a></div></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></div>	<style type='text/css'>.qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {display: none !important;}</style></div></div><!-- --><style>#mailContentContainer .txt {height:auto;}</style>  </div></body></html>", "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
    /*
     * 验证码超时检测
     */
    
}
