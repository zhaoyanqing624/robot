package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.AdvisePersistenceMapper;
import org.xjtusicd3.database.model.AdvisePersistence;

public class AdviseHelper
{
	/*
	 * zpz_get information of advise
	 */
	public static List<AdvisePersistence> getAdvise()
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AdvisePersistenceMapper mapper = session.getMapper(AdvisePersistenceMapper.class);
		List<AdvisePersistence> advise = mapper.getAdvise();
		session.close();
		return advise;
		
	}
}
