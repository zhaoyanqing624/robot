package org.xjtusicd3.database.helper;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.CurrentConfigurePersistenceMapper;
import org.xjtusicd3.database.model.CurrentConfigurePersistence;

public class CurrentConfigureHelper {
	/*
	 * zyq_personal3_添加补丁、软件信息
	 */
	public static void saveCurrentConfigure(CurrentConfigurePersistence configurePersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentConfigurePersistenceMapper mapper = session.getMapper(CurrentConfigurePersistenceMapper.class);
		mapper.save(configurePersistence);
		session.close();
	}
	/*
	 * zyq_personal3_更新补丁、软件信息
	 */
	public static void updataCurrentConfigure(CurrentConfigurePersistence configurePersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentConfigurePersistenceMapper mapper = session.getMapper(CurrentConfigurePersistenceMapper.class);
		mapper.update(configurePersistence);
		session.close();
	}
	/*
	 * zyq_personal3_添加驱动信息
	 */
	public static void saveCurrentConfigure_driver(String configureid,String equipmentid,String version,String name,String type){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentConfigurePersistenceMapper mapper = session.getMapper(CurrentConfigurePersistenceMapper.class);
		mapper.save_Configure_driver(configureid,equipmentid,version,name,type);
		session.close();
	}
	/*
	 * zyq_personal3_更新驱动信息
	 */
	public static void updateCurrentConfigure_driver(String configureid,String equipmentid,String version,String name,String type){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentConfigurePersistenceMapper mapper = session.getMapper(CurrentConfigurePersistenceMapper.class);
		mapper.updateCurrentConfigure_driver(configureid,equipmentid,version,name,type);
		session.close();
	}
}
