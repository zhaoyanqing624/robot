package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.CurrentEquipmentPersistenceMapper;
import org.xjtusicd3.database.mapper.EquipmentPersistenceMapper;
import org.xjtusicd3.database.model.CurrentEquipmentPersistence;
import org.xjtusicd3.database.model.EquipmentPersistence;

public class EquipmentHelper {
	/**
	 * author:zhaoyanqing
	 * date:2017年9月9日 21:12:28
	 * abstract:根据MAC地址查看是否在当前配置表中
	 */
	public static List<CurrentEquipmentPersistence> getCurrentEquipmentList(String mac){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentEquipmentPersistenceMapper mapper = session.getMapper(CurrentEquipmentPersistenceMapper.class);
		List<CurrentEquipmentPersistence> list = mapper.getCurrentEquipment(mac);
		session.close();
		return list;
	}
	/**
	 * author:zhaoyanqing
	 * date:2017年9月9日 21:08:18
	 * abstract:根据MAC地址查看是否存在配置表中
	 */
	public static List<EquipmentPersistence> getEquipmentList(String mac){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		EquipmentPersistenceMapper mapper = session.getMapper(EquipmentPersistenceMapper.class);
		List<EquipmentPersistence> list = mapper.getEquipmentList(mac);
		session.close();
		return list;
	}
}
