package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.EquipmentPersistenceMapper;
import org.xjtusicd3.database.model.EquipmentPersistence;

public class EquipmentHelper {
	/*
	 * zyq_personal3_查询equipment表是否存在
	 */
	public static List<EquipmentPersistence> getEquipmentList(String mac){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		EquipmentPersistenceMapper mapper = session.getMapper(EquipmentPersistenceMapper.class);
		List<EquipmentPersistence> list = mapper.getEquipmentList(mac);
		session.close();
		return list;
	}
}
