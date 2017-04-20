package org.xjtusicd3.database.helper;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.QuestionPersistenceMapper;
import org.xjtusicd3.database.model.QuestionPersistence;

public class QuestionHelper {
	/*
	 * spider_知识库问题的添加
	 */
	public static void save(QuestionPersistence questionPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		mapper.save(questionPersistence);
		session.close();
	}
	/*
	 * robot-分类
	 */
	public static List<QuestionPersistence> SecondClassify_robot(String ClassifyId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.SecondClassify_robot(ClassifyId);
		session.close();
		return list;
	}
//	/*
//	 * faq2_知识列表
//	 */
//	public static List<FaqPersistence> faqlist_faq2(int faqClassify,int pageNow){
//		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
//		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
//		int a  = (pageNow-1)*5;
//		List<FaqPersistence> list = mapper.faqlist_faq2(faqClassify,a);
//		session.close();
//		return list;
//	}
//	public static List<UserPersistence> userlist_faq2(int userId){
//		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
//		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
//		List<UserPersistence> list = mapper.userlist_faq2(userId);
//		session.close();
//		return list;
//	}
//	public static int pageTotal(int faqClassify){
//		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
//		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
//		int pageTotal = mapper.pageTotal(faqClassify);
//		session.close();
//		return pageTotal;
//	}
//	/*
//	 * faq3_知识内容
//	 */
//	public static List<FaqPersistence> faqcontent_faq3(int faqId){
//		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
//		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
//		List<FaqPersistence> list = mapper.faqcontent_faq3(faqId);
//		session.close();
//		return list;
//	}
//	/*
//	 * faq3_根据知识ID找类型classify
//	 */
//	public static int faqclassify(int faqId){
//		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
//		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
//		int f = mapper.faqclassify(faqId);
//		session.close();
//		return f;
//	}
}
