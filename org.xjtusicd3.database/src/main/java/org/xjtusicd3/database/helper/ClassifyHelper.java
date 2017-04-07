package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.ClassifyPersistenceMapper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.FaqPersistence;

public class ClassifyHelper {
	/*
	 * robot-分类
	 */
	public static List<ClassifyPersistence> classifyName1(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.FirstClassify_robot();
		session.close();
		return list;
	}
	public static List<ClassifyPersistence> classifyName2(int parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.SecondClassify_robot(parentId);
		session.close();
		return list;
	}
	/*
	 * faq、faq1_上侧的第二级分类
	 */
	public static List<ClassifyPersistence> classifyName2_2(int parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.SecondClassify_robot2(parentId);
		session.close();
		return list;
	}
	/*
	 * faq1_下面4栏推荐_按照浏览量
	 */
	public static List<ClassifyPersistence> SecondClassify_faq1(int parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.SecondClassify_faq1(parentId);
		session.close();
		return list;
	}
	public static List<FaqPersistence> faqPersistences_faq1(int faqClassify){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<FaqPersistence> list = mapper.faqPersistences_faq1(faqClassify);
		session.close();
		return list;
	}
	public static List<FaqPersistence> faqPersistences2_faq1(int faqClassify){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<FaqPersistence> list = mapper.faqPersistences2_faq1(faqClassify);
		session.close();
		return list;
	}
	/*
	 * faq2_获取第二类分类的名称、第一类分类的名称
	 */
	public static List<ClassifyPersistence> classify(int classifyId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.classify(classifyId);
		session.close();
		return list;
	}
	public static int classifyParentId(int classifyId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		int classifyParentId = mapper.classifyParentId(classifyId);
		session.close();
		return classifyParentId;
	}
}
