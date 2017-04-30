package org.xjtusicd3.partner.filter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

public class IdentificationNumber {
	//超时为false
	public static boolean identificationNumber(String startTime,String endTime){
		boolean b = false;
    	Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String time=format.format(date);
        Date d1 = null;
        Date d2 = null;
        try {
            d1 = format.parse(startTime);
            d2 = format.parse(endTime);
            DateTime dt1 = new DateTime(d1);
            DateTime dt2 = new DateTime(d2);
            if ((Days.daysBetween(dt1, dt2).getDays()*86400+Hours.hoursBetween(dt1, dt2).getHours() % 24*3600+Minutes.minutesBetween(dt1, dt2).getMinutes() % 60*60+Seconds.secondsBetween(dt1, dt2).getSeconds() % 60)<=300) {
            	b = true;
            	return b;
			}else {
				b = false;
				return b;
			}

        } catch (Exception e) {
            e.printStackTrace();
        }
		return b;
	}
}
