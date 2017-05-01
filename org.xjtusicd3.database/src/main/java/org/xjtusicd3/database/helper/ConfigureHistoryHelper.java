package org.xjtusicd3.database.helper;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.ConfigureHistoryPersistenceMapper;
import org.xjtusicd3.database.model.ConfigureHistoryPersistence;

public class ConfigureHistoryHelper {
	/*
	 * zyq_spider_添加爬虫爬取的配置
	 */
	public static void save_ConfigureHistory(ConfigureHistoryPersistence configureHistoryPersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigureHistoryPersistenceMapper mapper = session.getMapper(ConfigureHistoryPersistenceMapper.class);
		mapper.save(configureHistoryPersistence);
		session.close();
	}
}
