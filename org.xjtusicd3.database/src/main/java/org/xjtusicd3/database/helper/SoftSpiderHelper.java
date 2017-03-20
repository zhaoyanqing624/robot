package org.xjtusicd3.database.helper;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.SoftSpiderPersistenceMapper;
import org.xjtusicd3.database.model.SoftSpiderPersistence;

public class SoftSpiderHelper {
	public static void sava(SoftSpiderPersistence softSpiderPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SoftSpiderPersistenceMapper mapper = session.getMapper(SoftSpiderPersistenceMapper.class);
		mapper.save(softSpiderPersistence);
		session.close();
	}
}
