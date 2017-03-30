package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.FaqPersistenceMapper;
import org.xjtusicd3.database.model.FaqPersistence;

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
}
