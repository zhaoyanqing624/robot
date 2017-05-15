package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.CommunityAnswerPersistenceMapper;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;

public class CommunityAnswerHelper{
	/*
	 * zyq_question_问题展示
	 */
	public static List<CommunityAnswerPersistence> question_CommunityAnswer(String communityquestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.question_CommunityAnswer(communityquestionId);
		session.close();
		return list;
	}
	/*
	 * zyq_question_判断问题是否有最佳答案
	 */
	public static List<CommunityAnswerPersistence> question_iscurrentAnswer(String questionid,int isbest){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.question_iscurrentAnswer(questionid,isbest);
		session.close();
		return list;
	}
	/*
	 * zyq_question_获取用户点赞数量
	 */
	public static int likesNumber(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		int likesNumber = mapper.likesNumber();
		session.close();
		return likesNumber;
	}
	/*
	 * zyq_question_获取用户点评论数
	 */
	public static List<CommunityAnswerPersistence> question_CommunityAnswer_userId(String userid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.question_CommunityAnswer_userId(userid);
		session.close();
		return list;
	}
}
