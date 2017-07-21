import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  
  
public class Main {  
	public static void main(String[] args) {
//		final Logger logger = LoggerFactory.getLogger(Main.class);
//		//logger.info("测试：{}", "输出日志");
//		for (int i=1;i<11;i++){
//			UUID uuid = UUID.randomUUID();
//			//logger.info("测试：{}","meiguo");
//			System.out.println(uuid.toString());
//		}
		
//    	  Date date=new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time = format.format(date);
//        System.out.println(time);
		getdate(2);
		
	}
	public static String getdate(int i){ // //获取前后日期 i为正数 向后推迟i天，负数时向前提前i天
		 Date dat = null;
		 Calendar cd = Calendar.getInstance();
		 cd.add(Calendar.DATE, i);
		 dat = cd.getTime();
		 SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String time = dformat.format(dat);
		 return time;
	 }
}  