package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.User_Equipment_HistoryPersistenceMapper;
import org.xjtusicd3.database.model.User_Equipment_HistoryPersistence;

public class User_Equipment_HistoryHelper {
	/*
	 * zyq_personal_查看此表信息
	 */
	public static List<User_Equipment_HistoryPersistence> User_Equipment_HistoryList(String equipmentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		User_Equipment_HistoryPersistenceMapper mapper =  session.getMapper(User_Equipment_HistoryPersistenceMapper.class);
		List<User_Equipment_HistoryPersistence> list = mapper.User_Equipment_HistoryList(equipmentId);
		session.close();
		return list;
	}
}
