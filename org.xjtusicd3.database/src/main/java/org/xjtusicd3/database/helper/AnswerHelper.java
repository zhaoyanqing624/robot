package org.xjtusicd3.database.helper;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.AnswerPersistenceMapper;
import org.xjtusicd3.database.model.AnswerPersistence;

public class AnswerHelper {
	/*
	 * spider_知识库答案的添加
	 */
	public static void save(AnswerPersistence answerPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AnswerPersistenceMapper mapper = session.getMapper(AnswerPersistenceMapper.class);
		mapper.save(answerPersistence);
		session.close();
	}
}
