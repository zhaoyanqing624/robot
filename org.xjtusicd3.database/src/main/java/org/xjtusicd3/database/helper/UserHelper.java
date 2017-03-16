package org.xjtusicd3.database.helper;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.UserPersistenceMapper;

public class UserHelper {
	//校验邮箱是否被注册
	public static String getEmail(String email){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		String e = mapper.getEmail(email);
		session.close();
		return e;
	}
}
