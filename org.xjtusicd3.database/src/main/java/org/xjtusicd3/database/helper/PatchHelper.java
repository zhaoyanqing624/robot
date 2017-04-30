package org.xjtusicd3.database.helper;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.PatchPersistenceMapper;
import org.xjtusicd3.database.model.PatchPersistence;

public class PatchHelper {
	/*
	 * spider_补丁的增加
	 */
	public static void save(PatchPersistence patchSpiderPersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PatchPersistenceMapper mapper = session.getMapper(PatchPersistenceMapper.class);
		mapper.save(patchSpiderPersistence);
		session.close();
	}
}
