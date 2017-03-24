package org.xjtusicd3.database.helper;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.DriversSpiderPersistenceMapper;
import org.xjtusicd3.database.model.DriversSpiderPersistence;

public class DriversSpiderHelper {
	public static void sava(DriversSpiderPersistence driversSpiderPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		DriversSpiderPersistenceMapper mapper = session.getMapper(DriversSpiderPersistenceMapper.class);
		mapper.save(driversSpiderPersistence);
		session.close();
	}
}
