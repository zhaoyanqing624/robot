package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.filter.IdentificationNumber;
import org.xjtusicd3.partner.filter.ValidateEmail;

public class UserService {
	/*
	 * login_ajax_注册
	 */
	public static void login_register(String email,String password){
		UUID uuid = UUID.randomUUID();
		String identification_number =genCodes(8, 1).get(0);
    	Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyyMMddHHmmss");
        String time_stamp = format.format(date);
        String time = format2.format(date);
		String username = "会员"+ time + genCodes(6, 1).get(0); 
		//发送邮件验证信息
		ValidateEmail validateEmail = new ValidateEmail();
		try {
			validateEmail.validateEmail(email,username,identification_number);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String userimage = "images/user.png";
		UserHelper.login_register(uuid.toString(),email,password,username,0,identification_number,time_stamp,userimage);
	}
	//随机产生一个length位的字母+数字
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
    //校验邮箱验证码时间是否超时
    public static boolean validateEmail(String email){
    	boolean a = false;
    	List<UserPersistence> uList = UserHelper.getEmail(email);
    	String startTime = uList.get(0).getTIMEREMARKS();
    	Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTime = format.format(date);
        IdentificationNumber identificationNumber = new IdentificationNumber();
        boolean b = identificationNumber.identificationNumber(startTime, endTime);
        if (b==true) {
			return b;
		}else {
			return false;
		}
    }
    //校验用户是否已验证邮箱
    public static boolean validateUserState(String email){
    	boolean b = false;
    	List<UserPersistence> uList = UserHelper.getEmail(email);
    	if (uList.get(0).getUSERSTATE()==0) {
			return b;
		}else {
			return true;
		}
    }
}
