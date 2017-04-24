import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

public class testzhao {
    public static void main(String[] args) {
    	Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
        String dateStart = "2013-02-19 09:29:58";
        

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(time);

            DateTime dt1 = new DateTime(d1);
            DateTime dt2 = new DateTime(d2);

            System.out.print("两个时间相差：");
            System.out.print(Days.daysBetween(dt1, dt2).getDays() + " 天, ");
            System.out.print(Hours.hoursBetween(dt1, dt2).getHours() % 24
                    + " 小时, ");
            System.out.print(Minutes.minutesBetween(dt1, dt2).getMinutes() % 60
                    + " 分钟, ");
            System.out.print(Seconds.secondsBetween(dt1, dt2).getSeconds() % 60
                    + " 秒.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
