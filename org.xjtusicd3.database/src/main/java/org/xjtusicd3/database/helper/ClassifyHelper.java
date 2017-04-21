package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.ClassifyPersistenceMapper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;

public class ClassifyHelper {
	/*
	 * spider_分类的添加
	 */
	public static void save(ClassifyPersistence classifyPersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		mapper.save(classifyPersistence);
		session.close();
	}
	/*
	 * spider_按照分类名称查找
	 */
	public static List<ClassifyPersistence> spider_ClassifyListByName(String ClassifyName){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.spider_ClassifyListByName(ClassifyName);
		session.close();
		return list;
	}
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
	public static List<ClassifyPersistence> classifyName2(String ParentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.SecondClassify_robot(ParentId);
		session.close();
		return list;
	}

	/*
	 * faq、faq1_上侧的第二级分类
	 */
	public static List<ClassifyPersistence> faq1_ClassifyName(String parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.SecondClassify_robot2(parentId);
		session.close();
		return list;
	}
	/*
	 * faq1_下面4栏推荐_按照浏览量
	 */
	public static List<ClassifyPersistence> faq1_SecondClassify(String parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.faq1_SecondClassify(parentId);
		session.close();
		return list;
	}
	public static List<QuestionPersistence> faq1_faqPersistences(String faqClassify){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq1_faqPersistences(faqClassify);
		session.close();
		return list;
	}
	public static List<QuestionPersistence> faq1_faqPersistences2(String faqClassify){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq1_faqPersistences2(faqClassify);
		session.close();
		return list;
	}
	/*
	 * faq2_获取第二类分类的名称、第一类分类的名称
	 */
	public static List<ClassifyPersistence> faq2_classify(String ClassifyId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.faq2_classify(ClassifyId);
		session.close();
		return list;
	}
	public static String faq2_classifyParentId(String ClassifyId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		String classifyParentId = mapper.faq2_classifyParentId(ClassifyId);
		session.close();
		return classifyParentId;
	}
}
