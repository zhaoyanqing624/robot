package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.UserQuestionPersistenceMapper;
import org.xjtusicd3.database.model.UserQuestionPersistence;

public class UserQuestionHelper
{
	//获取用户问题信息
		public static List<UserQuestionPersistence> getUserQuestion()
		{
			SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
			UserQuestionPersistenceMapper mapper = session.getMapper(UserQuestionPersistenceMapper.class);
			List<UserQuestionPersistence> userlist = mapper.getUserQuestion();
			session.close();
			return userlist;
			

		}
}
