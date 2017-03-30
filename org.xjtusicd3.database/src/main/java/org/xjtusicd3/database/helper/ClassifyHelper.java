package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.ClassifyPersistenceMapper;
import org.xjtusicd3.database.model.ClassifyPersistence;

public class ClassifyHelper {
	/*
	 * robot-分类
	 */
	public static List<ClassifyPersistence> FirstClassify_robot(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.FirstClassify_robot();
		session.close();
		return list;
	}
	
	public static List<ClassifyPersistence> FirstClassify_robot2(int parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
		List<ClassifyPersistence> list = mapper.FirstClassify_robot2(parentId);
		session.close();
		return list;
	}
}
