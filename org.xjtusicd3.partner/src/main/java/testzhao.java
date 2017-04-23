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

public class testzhao {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<String> results=genCodes(6,1);
        System.out.println(results.get(0));
    }
 
    public static List<String> genCodes(int length,long num){
         
        List<String> results=new ArrayList<String>();
         
        for(int j=0;j<num;j++){
            String val = "";     
                      
            Random random = new Random();     
            for(int i = 0; i < length; i++)     
            {     
                String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num"; // 输出字母还是数字     
                          
                if("char".equalsIgnoreCase(charOrNum)) // 字符串     
                {     
                    int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; //取得大写字母还是小写字母     
                    val += (char) (choice + random.nextInt(26));     
                }     
                else if("num".equalsIgnoreCase(charOrNum)) // 数字     
                {     
                    val += String.valueOf(random.nextInt(10));     
                }     
            }
            val=val.toLowerCase();
            if(results.contains(val)){
                continue;
            }else{
                results.add(val);
            }
        }
        return results;
                      
               
        }   

}
