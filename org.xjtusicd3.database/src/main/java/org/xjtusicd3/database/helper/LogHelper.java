package org.xjtusicd3.database.helper;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.AdvisePersistenceMapper;
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
//	public static void getLog(String logId,String userId,String logPermission,String logTime)
//	{
//		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
//		LogPersistenceMapper mapper = session.getMapper(LogPersistenceMapper.class);
//		mapper.log_info(logId, userId, logPermission, logTime);
//		session.close();
//	}
	
	
	/*
	 * 添加日志_2017年9月14日22:20:58
	 */
//	public static void addLog(String logId, String userId, String faqPath, String logTime) {
//		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
//		LogPersistenceMapper mapper = session.getMapper(LogPersistenceMapper.class);
//		mapper.addLog(logId, userId,  faqPath, logTime);
//		session.close();
//	}

	/**
	 * author:zzl
	 * abstract:获取用户日志
	 * data:2017年9月15日09:16:32
	 * @param userid 
	 */
	public static List<LogPersistence> getLogs(String userid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		LogPersistenceMapper mapper = session.getMapper(LogPersistenceMapper.class);
		List<LogPersistence> log = mapper.getLogs(userid);
		session.close();
		return log;
	}

	public static void insertLog(LogPersistence myLog) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		LogPersistenceMapper mapper = session.getMapper(LogPersistenceMapper.class);
		mapper.save(myLog);
		session.close();
		
	}

	

}