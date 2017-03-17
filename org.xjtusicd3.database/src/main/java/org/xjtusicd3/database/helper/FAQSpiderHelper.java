package org.xjtusicd3.database.helper;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.FAQSpiderPersistenceMapper;
import org.xjtusicd3.database.model.FAQSpiderPersistence;

public class FAQSpiderHelper {
	public static void save(FAQSpiderPersistence faqSpiderPersistence) throws Exception{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		FAQSpiderPersistenceMapper mapper = session.getMapper(FAQSpiderPersistenceMapper.class);
		mapper.save(faqSpiderPersistence);
		session.close();
	}
}
