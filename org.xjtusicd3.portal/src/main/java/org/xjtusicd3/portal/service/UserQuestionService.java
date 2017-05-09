package org.xjtusicd3.portal.service;

import java.util.List;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.UserQuestionHelper;
import org.xjtusicd3.database.model.UserQuestionPersistence;

import com.alibaba.fastjson.JSONObject;

public class UserQuestionService
{
	//获取用户信息
		public static String getUserQuestion()
		{
			String user = "";
			List<UserQuestionPersistence> userlist = UserQuestionHelper.getUserQuestion();
			for (int i = 0; i < userlist.size(); i++)
			{
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id",i+1);
				jsonObject.put("questiontitle", userlist.get(i).getQUESTIONTITLE());
				jsonObject.put("questiontime", userlist.get(i).getQUESTIONTIME());
				jsonObject.put("isFAQ", userlist.get(i).getISFAQ());
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
			getUserQuestion();
		}
}
