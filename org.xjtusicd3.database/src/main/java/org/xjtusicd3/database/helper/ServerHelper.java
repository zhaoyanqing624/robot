package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.ServerPersistenceMapper;
import org.xjtusicd3.database.model.ServerPersistence;

public class ServerHelper {
	//zpz_get server information
			public static List<ServerPersistence> getServer()
			{
				SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
				ServerPersistenceMapper mapper = session.getMapper(ServerPersistenceMapper.class);
				List<ServerPersistence> serverlist = mapper.getServer();
				session.close();
				return serverlist;
				

			}
}
