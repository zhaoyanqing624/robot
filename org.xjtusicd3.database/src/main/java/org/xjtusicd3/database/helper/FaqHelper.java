package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.FaqPersistenceMapper;
import org.xjtusicd3.database.mapper.UserPersistenceMapper;
import org.xjtusicd3.database.model.FaqPersistence;
import org.xjtusicd3.database.model.UserPersistence;

public class FaqHelper {
	/*
	 * robot-分类
	 */
	public static List<FaqPersistence> SecondClassify_robot(int faqClassify){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		FaqPersistenceMapper mapper = session.getMapper(FaqPersistenceMapper.class);
		List<FaqPersistence> list = mapper.SecondClassify_robot(faqClassify);
		session.close();
		return list;
	}
	/*
	 * faq2_知识列表
	 */
	public static List<FaqPersistence> faqlist_faq2(int faqClassify,int pageNow){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		FaqPersistenceMapper mapper = session.getMapper(FaqPersistenceMapper.class);
		int a  = (pageNow-1)*5;
		List<FaqPersistence> list = mapper.faqlist_faq2(faqClassify,a);
		session.close();
		return list;
	}
	public static List<UserPersistence> userlist_faq2(int userId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		FaqPersistenceMapper mapper = session.getMapper(FaqPersistenceMapper.class);
		List<UserPersistence> list = mapper.userlist_faq2(userId);
		session.close();
		return list;
	}
	public static int pageTotal(int faqClassify){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		FaqPersistenceMapper mapper = session.getMapper(FaqPersistenceMapper.class);
		int pageTotal = mapper.pageTotal(faqClassify);
		session.close();
		return pageTotal;
	}
	/*
	 * faq3_知识内容
	 */
	public static List<FaqPersistence> faqcontent_faq3(int faqId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		FaqPersistenceMapper mapper = session.getMapper(FaqPersistenceMapper.class);
		List<FaqPersistence> list = mapper.faqcontent_faq3(faqId);
		session.close();
		return list;
	}
	/*
	 * faq3_根据知识ID找类型classify
	 */
	public static int faqclassify(int faqId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		FaqPersistenceMapper mapper = session.getMapper(FaqPersistenceMapper.class);
		int f = mapper.faqclassify(faqId);
		session.close();
		return f;
	}
}
