package org.xjtusicd3.database.helper;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.PatchSpiderPersistenceMapper;
import org.xjtusicd3.database.model.PatchSpiderPersistence;

public class PatchSpiderHelper {
	public static void save(PatchSpiderPersistence patchSpiderPersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PatchSpiderPersistenceMapper mapper = session.getMapper(PatchSpiderPersistenceMapper.class);
		mapper.save(patchSpiderPersistence);
		session.close();
	}
}
