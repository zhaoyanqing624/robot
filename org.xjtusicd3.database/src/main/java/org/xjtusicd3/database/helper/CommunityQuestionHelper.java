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
	public static void saveCommunityQuestion(CommunityQuestionPersistence communityQuestionPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		mapper.save(communityQuestionPersistence);
		session.close();
	}
	/*
	 * zyq_ajax_question校验是否重复添加
	 */
	public static List<CommunityQuestionPersistence> question_iscurrent(String userid,String questiontitle){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		List<CommunityQuestionPersistence> list = mapper.question_iscurrent(userid,questiontitle);
		return null;
	}
	
}
