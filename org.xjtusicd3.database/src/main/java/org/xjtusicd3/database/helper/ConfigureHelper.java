package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.ConfigurePersistenceMapper;
import org.xjtusicd3.database.model.ConfigurePersistence;

public class ConfigureHelper {
	/*
	 * zyq_spider_补丁的增加
	 */
	public static void save_Patch(ConfigurePersistence ConfigurePersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		mapper.save(ConfigurePersistence);
		session.close();
	}
	/*
	 * zyq_spider_软件的增加
	 */
	public static void save_Soft(ConfigurePersistence configurePersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		mapper.save(configurePersistence);
		session.close();
	}
	/*
	 *zyq_spider_驱动的增加
	 */
	public static void save_Driver(ConfigurePersistence configurePersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		mapper.save(configurePersistence);
		session.close();
	}
	/*
	 * zyq_获取所有的设备信息
	 */
	public static List<ConfigurePersistence> getPartConfig(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		List<ConfigurePersistence> list = mapper.getPartConfig();
		session.close();
		return list;
	}
	/*
	 * zyq_spider_按名称查看设备
	 */
	public static List<ConfigurePersistence> getConfigure(String configurename){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		List<ConfigurePersistence> list = mapper.getConfigure(configurename);
		session.close();
		return list;
	}
	/*
	 * zyq_spider_更新软件
	 */
	public static List<ConfigurePersistence> update_Configure(String configurename,String filesize,String url,String downloadtimes,String producer,String configuretime){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ConfigurePersistenceMapper mapper = session.getMapper(ConfigurePersistenceMapper.class);
		List<ConfigurePersistence> list = mapper.update_Configure(configurename,filesize,url,downloadtimes,producer,configuretime);
		session.close();
		return list;
	}
	
}
