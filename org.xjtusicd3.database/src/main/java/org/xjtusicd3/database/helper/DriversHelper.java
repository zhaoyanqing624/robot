package org.xjtusicd3.database.helper;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.DriversPersistenceMapper;
import org.xjtusicd3.database.model.DriverPersistence;

public class DriversHelper {
	/*
	 * spider_驱动的增加
	 */
	public static void sava(DriverPersistence driversPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		DriversPersistenceMapper mapper = session.getMapper(DriversPersistenceMapper.class);
		mapper.save(driversPersistence);
		session.close();
	}
}
