package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.CommunityQuestionPersistenceMapper;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;

public class CommunityQuestionHelper{
	/*
	 * zyq_ajax_question的增加
	 */
	public static void saveCommunityQuestion(String id,String time,String title,String content,String classifyid,String userid,String scan,String userquestionid,int isanswer){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		mapper.saveCommunityQuestion(id,time,title,content,classifyid,userid,scan,userquestionid,isanswer);
		session.close();
	}
	/*
	 * zyq_ajax_question校验是否重复添加
	 */
	public static List<CommunityQuestionPersistence> question_iscurrent(String userid,String questiontitle){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.question_iscurrent(userid,questiontitle);
		session.close();
		return list;
	}
	/*
	 * zyq_question_问题展示_根据类别ID
	 */
	public static List<CommunityQuestionPersistence> question_getCommunity(String classifyid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.question_getCommunity(classifyid);
		session.close();
		return list;
	}
	public static List<CommunityQuestionPersistence> question_getCommunity2(String classifyid,int isanswer){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.question_getCommunity2(classifyid,isanswer);
		session.close();
		return list;
	}
	/*
	 * zyq_question_问题展示_根据是否有答案
	 */
	public static List<CommunityQuestionPersistence> question_getCommunity_isanswer(int startnumber){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.question_getCommunity_isanswer(startnumber);
		session.close();
		return list;
	}
	public static List<CommunityQuestionPersistence> question_getCommunity2_isanswer(int isanswer){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.question_getCommunity2_isanswer(isanswer);
		session.close();
		return list;
	}
	/*
	 * zyq_question2_问题内容详情
	 */
	public static List<CommunityQuestionPersistence> question2_getCommunity(String questionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.question2_getCommunity(questionId);
		session.close();
		return list;
	}
	/*
	 * zyq_notice_查询用户的提问
	 */
	public static List<CommunityQuestionPersistence> notice_CommunityQuestion(String userid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.notice_CommunityQuestion(userid);
		session.close();
		return list;
	}
	public static List<CommunityQuestionPersistence> notice_CommunityQuestion_Limit(String userid,int startNumber,int number){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.notice_CommunityQuestion_Limit(userid,startNumber,number);
		session.close();
		return list;
	}
	public static List<CommunityQuestionPersistence> notice_CommunityQuestion_Limit_Time(String userid,int startNumber,int number,String time){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.notice_CommunityQuestion_Limit_Time(userid,startNumber,number,time);
		session.close();
		return list;
	}
	//zpz_获取社区问题
		public static List<CommunityQuestionPersistence> getAllCommunityQuestion()
		{
			SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
			CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
			List<CommunityQuestionPersistence> communityquestionlist = mapper.getAllCommunityQuestion();
			session.close();
			return communityquestionlist;
			

		}
		
	//zpz_get community problem by ID
			public static List<CommunityQuestionPersistence> getCommunityQuestionById(String communityProblemId)
			{
				SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
				CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
				List<CommunityQuestionPersistence> communityquestionlist = mapper.question2_getCommunity(communityProblemId);
				session.close();
				return communityquestionlist;
				

			}
	/*
	 * zyq_question2_设为最佳答案
	 */
	public static void updateBestAnswer(String questionId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		mapper.updateBestAnswer(questionId);
		session.close();
	}
		
	
}