package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.ComputerPersistenceMapper; 
import org.xjtusicd3.database.model.ComputerPersistence;

public class ComputerHelper {

	//zpz_get computer information
		public static List<ComputerPersistence> getComputer()
		{
			SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
			ComputerPersistenceMapper mapper = session.getMapper(ComputerPersistenceMapper.class);
			List<ComputerPersistence> computerlist = mapper.getComputer();
			session.close();
			return computerlist;
			

		}
	
}
