package org.xjtusicd3.database.helper;

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
	public static List<UserPersistence> getEmail2(String useremail,String password){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> list = mapper.getEmail2(useremail,password);
		session.close();
		return list;
	}
	public static List<UserPersistence> getEmail3(String useremail,String identificationnumber){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> list = mapper.getEmail3(useremail,identificationnumber);
		session.close();
		return list;
	}
	//验证码通过--update
	public static void  updateUserState(String email){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.updateUserState(email);
		session.close();
	}
	//验证码失败--delete
	public static void deleteUser(String email){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.deleteUser(email);  
		session.close();
	} 
	  
	//获取用户信息
	public static List<UserPersistence> getUser()
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> userlist = mapper.getUser();
		session.close();
		return userlist;
		
	}
}