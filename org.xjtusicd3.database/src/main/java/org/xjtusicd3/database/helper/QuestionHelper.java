package org.xjtusicd3.database.helper;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.QuestionPersistenceMapper;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;

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
	/*
	 * faq2_知识列表
	 */
	public static List<QuestionPersistence> faq2_faqlist(String ClassifyId,int pageNow){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		int a  = (pageNow-1)*5;
		List<QuestionPersistence> list = mapper.faq2_faqlist(ClassifyId,a);
		session.close();
		return list;
	}
	public static List<UserPersistence> faq2_userlist(String UserId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<UserPersistence> list = mapper.faq2_userlist(UserId);
		session.close();
		return list;
	}
	public static int pageTotal(String ClassifyId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		int pageTotal = mapper.pageTotal(ClassifyId);
		session.close();
		return pageTotal;
	}
	public static String faq2_userId(String QuestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		String UserId = mapper.faq2_UserId(QuestionId);
		session.close();
		return UserId;
	}
	/*
	 * faq3_知识内容
	 */
	public static List<QuestionPersistence> faq3_faqcontent(String faqId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq3_faqcontent(faqId);
		session.close();
		return list;
	}
	public static List<QuestionPersistence> faq3_faqcontent_title(String faqtitle){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq3_faqcontent_title(faqtitle);
		session.close();
		return list;
	}
	/*
	 * faqadd_校验知识是否重复增添
	 */
	public static List<QuestionPersistence> faqadd_iscurrent(String faqtitle,String useremail){
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faqadd_iscurrent(faqtitle,userPersistences.get(0).getUSERID());
		session.close();
		return list;
	}
	/*
	 * faq3_根据知识ID找类型classify
	 */
	public static String faqclassify(String QuestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		String ClassifyId = mapper.faq3_faqclassifyId(QuestionId);
		session.close();
		return ClassifyId;
	}
	/*
	 * zpz_
	 */
	public static List<QuestionPersistence> getFaq(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.getFaq();
		session.close();
		return list;
	}
}
