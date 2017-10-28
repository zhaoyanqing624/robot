package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.UserPersistenceMapper;
import org.xjtusicd3.database.mapper.UserQuestionPersistenceMapper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.database.model.UserQuestionPersistence;

public class UserQuestionHelper
{
	public static void main(String[] args)
	{
		System.out.println(getUserQuestion());
	}
	//获取用户问题信息
		public static List<UserQuestionPersistence> getUserQuestion()
		{
			SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
			UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
			List<UserQuestionPersistence> userlist = mapper.getUserQuestion();
			session.close();
			return userlist;
		}
		
		//获取用户问题信息
				public static List<UserQuestionPersistence> getUserQuestion(String UserQuestionId)
				{
					SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
					UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
					List<UserQuestionPersistence> userlist = mapper.getUserQuestionById(UserQuestionId);
					session.close();
					return userlist;
				}
		
		//删除用户问题信息
		public static void deleteUserQuestion(String userquestionid) 
		{
			SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
			UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
			mapper.deleteUserQuestion(userquestionid);  
			session.close();
			
		}
		
		/**
		 * author:zzl
		 * abstract:记录用户提问记录
		 * data:2017年10月22日18:43:47
		 */
		public static void addUserQuestion(String userQuestionId, String userQuestionTitle, String time, int isFaq, String userId) {
			SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
			UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
			mapper.addUserQuestion(userQuestionId,userQuestionTitle,time,isFaq,userId);
			session.close();
			
		}
	
}
