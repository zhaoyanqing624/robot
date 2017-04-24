package org.xjtusicd3.database.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.UserPersistenceMapper;
import org.xjtusicd3.database.model.UserPersistence;

public class UserHelper {
	/*
	 * login_ajax_注册
	 */
	public static void login_register(String userid,String email,String password,String username,int userstate,String identification_number,String time_stamp){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.login_register(userid, email, password, username, userstate, identification_number,time_stamp);
		session.close();
	}
	//校验邮箱是否被注册
	public static List<UserPersistence> getEmail(String useremail){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> list = mapper.getEmail(useremail);
		session.close();
		return list;
	}
}
