package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.ITPersistenceMapper;
import org.xjtusicd3.database.model.ITPersistence;

public class ITHelper {
	/*
	 * zyq_personal2_查看运维人员
	 */
	public static List<ITPersistence> IT(String userid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		ITPersistenceMapper mapper = session.getMapper(ITPersistenceMapper.class);
		List<ITPersistence> list = mapper.IT(userid);
		return list;
	}
}
