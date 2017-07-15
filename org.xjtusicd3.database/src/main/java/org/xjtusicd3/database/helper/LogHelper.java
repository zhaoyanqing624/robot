package org.xjtusicd3.database.helper;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.LogPersistenceMapper;
import org.xjtusicd3.database.model.LogPersistence;

public class LogHelper
{
	/*
	 * zpz_get information of advise
	 */
	public static List<LogPersistence> getLog()
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		LogPersistenceMapper mapper = session.getMapper(LogPersistenceMapper.class);
		List<LogPersistence> log = mapper.getLog();
		session.close();
		return log;
		
	}
	
	/*
	 * zyq_get log
	 */
	public static void getLog(String logId,String userId,String logPermission,String logTime)
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		LogPersistenceMapper mapper = session.getMapper(LogPersistenceMapper.class);
		mapper.log_info(logId, userId, logPermission, logTime);
		session.close();
	}
}