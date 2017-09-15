package org.xjtusicd3.portal.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.portal.filter.ValidateEmail;

import com.alibaba.fastjson.JSONObject;

public class UserService {
    //获取用户信息
//	public static String getUser()
//	{
//		String user = "";
//		List<UserPersistence> userlist = UserHelper.getUser();
//		for (int i = 0; i < userlist.size(); i++)
//		{
//			JSONObject jsonObject = new JSONObject();
//			jsonObject.put("id",i+1);
//			jsonObject.put("userName", userlist.get(i).getUSERNAME());
//			jsonObject.put("userPassword", userlist.get(i).getUSERPASSWORD());
//			jsonObject.put("userEmail", userlist.get(i).getUSEREMAIL());
//			user += JsonUtil.toJsonString(jsonObject);
//			if(i < userlist.size()-1)
//			{
//				user += ",";
//			}else
//			{
//				user += "";
//			}
//		}
//		System.out.println(user);
//		return user;
//		
//	}
//	
//	public static void main(String[] args)
//	{
//		getUser();
//	}
	
	public static List<UserPersistence> getAllUserList() 
	{
		List<UserPersistence> userlist = UserHelper.getAllUserInfo();
		return userlist;
		
	}
	
	public static void login_register(String email, String password)
	{
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
		UserHelper.login_register(uuid.toString(),email,password,username,0,identification_number,time_stamp,"");
		
	}
	public static boolean validateUserState(String email)
	{
		// TODO Auto-generated method stub
		return false;
	}
	public static boolean validateEmail(String email)
	{
		// TODO Auto-generated method stub
		return false;
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
}
