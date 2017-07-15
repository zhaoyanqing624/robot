package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.PermissionPersistenceMapper;
import org.xjtusicd3.database.mapper.QuestionPersistenceMapper;
import org.xjtusicd3.database.model.PermissionPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;

public class PermissionHelper
{
	/*
	 * get permission
	 */
	public static  PermissionPersistence getPermission(String PermissionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		PermissionPersistenceMapper mapper = session.getMapper(PermissionPersistenceMapper.class);
		 PermissionPersistence list = mapper.getPermission(PermissionId);
		session.close();
		return list;
	}
}
