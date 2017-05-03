package org.xjtusicd3.portal.service;

import java.util.List;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.UserPersistence;

import com.alibaba.fastjson.JSONObject;

public class UserService {

	public static String getUser()
	{
		String user = "";
		List<UserPersistence> userlist = UserHelper.getUser();
		for (int i = 0; i < userlist.size(); i++)
		{
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id",i+1);
			jsonObject.put("userName", userlist.get(i).getUSERNAME());
			jsonObject.put("userPassword", userlist.get(i).getUSERPASSWORD());
			jsonObject.put("userEmail", userlist.get(i).getUSEREMAIL());
			user += JsonUtil.toJsonString(jsonObject);
			if(i < userlist.size()-1)
			{
				user += ",";
			}else
			{
				user += "";
			}
		}
		System.out.println(user);
		return user;
		
	}
	public static void main(String[] args)
	{
		getUser();
	}
}
