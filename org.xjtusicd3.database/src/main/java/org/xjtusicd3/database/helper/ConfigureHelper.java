package org.xjtusicd3.database.helper;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.ConfigurePersistenceMapper;
import org.xjtusicd3.database.model.ConfigurePersistence;

public class ConfigureHelper {
	/*
	 * spider_补丁的增加
	 */
	public static void save_Patch(ConfigurePersistence ConfigurePersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		mapper.save(ConfigurePersistence);
		session.close();
	}
	/*
	 * spider_软件的增加
	 */
	public static void save_Soft(ConfigurePersistence configurePersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		mapper.save(configurePersistence);
		session.close();
	}
	/*
	 * spider_驱动的增加
	 */
	public static void save_Driver(ConfigurePersistence configurePersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		mapper.save(configurePersistence);
		session.close();
	}
}
