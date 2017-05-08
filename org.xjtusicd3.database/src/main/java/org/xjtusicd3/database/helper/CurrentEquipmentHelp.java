package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.CurrentEquipmentPersistenceMapper;
import org.xjtusicd3.database.model.CurrentEquipmentPersistence;

public class CurrentEquipmentHelp {
	/*
	 * zyq_personal3_检验是否存在当前设备表
	 */
	public static List<CurrentEquipmentPersistence> currentEquipment(String macaddress){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentEquipmentPersistenceMapper mapper = session.getMapper(CurrentEquipmentPersistenceMapper.class);
		List<CurrentEquipmentPersistence> list = mapper.currentEquipment(macaddress);
		session.close();
		return list;
	}
	/*
	 * zyq_personal3_存入当前设备表
	 */
	public static void save(CurrentEquipmentPersistence currentEquipmentPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentEquipmentPersistenceMapper mapper = session.getMapper(CurrentEquipmentPersistenceMapper.class);
		mapper.save(currentEquipmentPersistence);
		session.close();
	}
	/*
	 * zyq_peisonal3_更新当前设备表
	 */
	public static void update(CurrentEquipmentPersistence currentEquipmentPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CurrentEquipmentPersistenceMapper mapper = session.getMapper(CurrentEquipmentPersistenceMapper.class);
		mapper.update(currentEquipmentPersistence);
		session.close();
	}
}
