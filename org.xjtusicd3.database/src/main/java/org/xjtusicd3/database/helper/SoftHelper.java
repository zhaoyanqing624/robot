package org.xjtusicd3.database.helper;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.SoftPersistenceMapper;
import org.xjtusicd3.database.model.SoftPersistence;

public class SoftHelper {
	/*
	 * spider_软件的信息添加
	 */
	public static void sava(SoftPersistence softPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SoftPersistenceMapper mapper = session.getMapper(SoftPersistenceMapper.class);
		mapper.save(softPersistence);
		session.close();
	}
}
