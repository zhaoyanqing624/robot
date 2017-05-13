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
	public static void saveCommunityQuestion(String id,String time,String title,String content,String classifyid,String userid,String collection,String scan,String userquestionid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommunityQuestionPersistenceMapper mapper = session.getMapper(CommunityQuestionPersistenceMapper.class);
		mapper.saveCommunityQuestion(id,time,title,content,classifyid,userid,collection,scan,userquestionid);
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
	
}
