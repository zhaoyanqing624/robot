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
	 * zyq_question_问题展示_best
	 */
	public static List<CommunityAnswerPersistence> question_CommunityAnswer_best(String communityquestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.question_CommunityAnswer_best(communityquestionId);
		session.close();
		return list;
	}
	/*
	 * zyq_question_问题展示_other	
	 */
	public static List<CommunityAnswerPersistence> question_CommunityAnswer_other(String communityquestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.question_CommunityAnswer_other(communityquestionId);
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
	 * zyq_question_获取用户点评论数
	 */
	public static List<CommunityAnswerPersistence> question_CommunityAnswer_userId(String userid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.question_CommunityAnswer_userId(userid);
		session.close();
		return list;
	}
	/*
	 * zyq_question_判断评论是否重复提交
	 */
	public static List<CommunityAnswerPersistence> question_IsCommunityAnswer(String userid,String content,String questionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.question_IsCommunityAnswer(userid,content,questionId);
		session.close();
		return list;
	}
	/*
	 * zyq_question_ajax_添加评论
	 */
	public static void addComment(CommunityAnswerPersistence communityAnswerPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		mapper.save(communityAnswerPersistence);
		session.close();
	}
	//查看用户被点赞数量
	public static List<CommunityAnswerPersistence> getCommunityAnswerLike(String userId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.getCommunityAnswerLike(userId);
		session.close();
		return list;
	}
	/*
	 * zyq_question2_ajax_设置为最佳答案
	 */
	public static void saveBestAnswer(String answerId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		mapper.saveBestAnswer(answerId);
		session.close();
	}
	/*
	 * zyq_question_问题展示_byAnswerID
	 */
	public static List<CommunityAnswerPersistence> question_CommunityAnswerId(String communityanswerId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityAnswerPersistenceMapper mapper = session.getMapper(CommunityAnswerPersistenceMapper.class);
		List<CommunityAnswerPersistence> list = mapper.question_CommunityAnswerId(communityanswerId);
		session.close();
		return list;
	}
}
