package org.xjtusicd3.database.helper;

import java.util.List;

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
	/*
	 * zyq_personal3_查看当前配置表
	 */
	public static List<CurrentConfigurePersistence> getCurrentConfigure(String equipmentId,String type){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentConfigurePersistenceMapper mapper = session.getMapper(CurrentConfigurePersistenceMapper.class);
		List<CurrentConfigurePersistence> list = mapper.getCurrentConfigure(equipmentId,type);
		session.close();
		return list;
	}
	/**
	 * author:zhaoyanqing
	 * date:2017年9月10日 09:19:59
	 * abstract:根据设备ID 查看当前设备配置表
	 */
	public static List<CurrentConfigurePersistence> getCurrentConfigure(String equipmentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentConfigurePersistenceMapper mapper = session.getMapper(CurrentConfigurePersistenceMapper.class);
		List<CurrentConfigurePersistence> list = mapper.getCurrentConfigureOfSoftware(equipmentId);
		session.close();
		return list;
	}
	/**
	 * author:zhaoyanqing
	 * date:2017年9月11日 23:01:08
	 * abstract:根据设备ID 删除当前设备配置表
	 */
	public static void deleteCurrentConfigure(String equipmentid,String type) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentConfigurePersistenceMapper mapper = session.getMapper(CurrentConfigurePersistenceMapper.class);
		mapper.deleteCurrentConfigure(equipmentid,type);
		session.close();
	}
}