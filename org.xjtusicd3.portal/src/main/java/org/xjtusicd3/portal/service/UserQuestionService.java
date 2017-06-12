package org.xjtusicd3.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.helper.UserQuestionHelper;
import org.xjtusicd3.database.mapper.UserQuestionPersistenceMapper;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.database.model.UserQuestionPersistence;
import org.xjtusicd3.portal.view.IncidentindexView;

import com.alibaba.fastjson.JSONObject;

public class UserQuestionService
{
	public static List<UserQuestionPersistence> getUserQuestion() 
	{
		List<UserQuestionPersistence> userquestionlist = UserQuestionHelper.getUserQuestion();
		return userquestionlist;
		
	}
	/*
	 * incidentindex_userQuestion展示
	 */
	public static List<IncidentindexView> incidentindexViews() 
	{
		List<IncidentindexView> incidentindexViews = new ArrayList<IncidentindexView>();
		List<UserQuestionPersistence> userQuestionPersistences = UserQuestionHelper.getUserQuestion();
		for (UserQuestionPersistence userQuestionPersistence : userQuestionPersistences)
		{
			IncidentindexView incidentindexView = new IncidentindexView();
			incidentindexView.setUserQuestionTime(userQuestionPersistence.getQUESTIONTIME());
			incidentindexView.setUserQuestionId(userQuestionPersistence.getUSERQUESTIONID());
			incidentindexView.setUserQuestionTitle(userQuestionPersistence.getQUESTIONTITLE());
			List<UserPersistence> userPersistences =UserHelper.getUserNameById(userQuestionPersistence.getUSERID());
			incidentindexView.setUserName(userPersistences.get(0).getUSERNAME());
			incidentindexViews.add(incidentindexView);
		}
		
		
		return incidentindexViews;
	}
	
	/*
	 * incidentindex_userQuestion详细信息展示
	 */
	public static List<IncidentindexView> getUserQuestionDetail(String UserQuestionId) 
	{
		List<IncidentindexView> incidentindexViews = new ArrayList<IncidentindexView>();
		List<UserQuestionPersistence> userQuestionPersistences = UserQuestionHelper.getUserQuestion(UserQuestionId);
		for (UserQuestionPersistence userQuestionPersistence : userQuestionPersistences)
		{
			IncidentindexView incidentindexView = new IncidentindexView();
			incidentindexView.setUserQuestionTime(userQuestionPersistence.getQUESTIONTIME());
			incidentindexView.setUserQuestionId(userQuestionPersistence.getUSERQUESTIONID());
			incidentindexView.setUserQuestionTitle(userQuestionPersistence.getQUESTIONTITLE());
			List<UserPersistence> userPersistences =UserHelper.getUserNameById(userQuestionPersistence.getUSERID());
			incidentindexView.setUserName(userPersistences.get(0).getUSERNAME());
			incidentindexViews.add(incidentindexView);
		}
		
		
		return incidentindexViews;
	}
	
//	//获取用户信息
//		public static String getUserQuestion()
//		{
//			String user = "";
//			List<UserQuestionPersistence> userlist = UserQuestionHelper.getUserQuestion();
//			for (int i = 0; i < userlist.size(); i++)
//			{
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.put("id",i+1);
//				jsonObject.put("questiontitle", userlist.get(i).getQUESTIONTITLE());
//				jsonObject.put("questiontime", userlist.get(i).getQUESTIONTIME());
//				jsonObject.put("isFAQ", userlist.get(i).getISFAQ());
//				user += JsonUtil.toJsonString(jsonObject);
//				if(i < userlist.size()-1)
//				{
//					user += ",";
//				}else
//				{
//					user += "";
//				}
//			}
//			System.out.println(user);
//			return user;
//			
//		}
//		public static void main(String[] args)
//		{
//			getUserQuestion();
//		}
}
