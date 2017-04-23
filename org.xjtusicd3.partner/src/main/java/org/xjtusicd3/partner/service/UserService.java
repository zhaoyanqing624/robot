package org.xjtusicd3.partner.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.partner.filter.ValidateEmail;

public class UserService {
	/*
	 * login_ajax_注册
	 */
	public static void login_register(String email,String password,String username){
		UUID uuid = UUID.randomUUID();
		String userregister =genCodes(8, 1).get(0);
		//发送邮件验证信息
		ValidateEmail validateEmail = new ValidateEmail();
		try {
			validateEmail.validateEmail(email,username,userregister);
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserHelper.login_register(uuid.toString(),email,password,username,0,userregister);
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
	//校验邮箱是否被注册
	public static String getEmail(String email){
		String a = UserHelper.getEmail(email);
		return a;
	}
}
