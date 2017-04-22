import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class testzhao {

	public static void main(String[] args) throws Exception {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress("sicd_xiaoduo@163.com", "testzhao", "UTF-8"));
			message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("77528570@qq.com","DUO","UTF-8"));
			message.setSubject("我就是试一试", "UTF-8");
			message.setContent("TEST这是邮件正文。。。", "text/html;charset=UTF-8");
			message.setSentDate(new Date());
			message.saveChanges();
			OutputStream outputStream  = new FileOutputStream("MyEmail.eml");
			message.writeTo(outputStream);
			outputStream.flush();
			outputStream.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
