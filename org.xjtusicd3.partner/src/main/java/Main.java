import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;  
  
public class Main {  
	public static void main(String[] args) {
		final Logger logger = LoggerFactory.getLogger(Main.class);
		//logger.info("测试：{}", "输出日志");
		for (int i=1;i<11;i++){
			UUID uuid = UUID.randomUUID();
			//logger.info("测试：{}","meiguo");
			System.out.println(uuid.toString());
		}
		
//    	  Date date=new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time = format.format(date);
//        System.out.println(time);
	}
}  