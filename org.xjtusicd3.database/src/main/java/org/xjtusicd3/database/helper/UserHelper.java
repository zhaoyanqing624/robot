package org.xjtusicd3.database.helper;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.UserPersistenceMapper;
import org.xjtusicd3.database.model.UserPersistence;

public class UserHelper {
	/*
	 * login_ajax_注册
	 */
	public static void login_register(String userid,String email,String password,String username,int userstate,String userregister){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.login_register(userid, email, password, username, userstate, userregister);
		session.close();
	}
	//校验邮箱是否被注册
	public static String getEmail(String email){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		String e = mapper.getEmail(email);
		session.close();
		return e;
	}
}
