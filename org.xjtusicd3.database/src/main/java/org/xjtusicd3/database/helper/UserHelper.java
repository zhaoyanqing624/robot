package org.xjtusicd3.database.helper;

import java.sql.Savepoint;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.GeneraluserPersistenceMapper;
import org.xjtusicd3.database.mapper.UserPersistenceMapper;
import org.xjtusicd3.database.model.GeneraluserPersistence;
import org.xjtusicd3.database.model.UserPersistence;

public class UserHelper {
	/*
	 * zyq_login_ajax_注册
	 */
	public static void login_register(String userid,String email,String password,String username,int userstate,String identification_number,String time_stamp,String userimage){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.login_register(userid, email, password, username, userstate, identification_number,time_stamp,userimage);
		session.close();
	}
	//zyq_校验邮箱是否被注册
	public static List<UserPersistence> getEmail(String useremail){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> list = mapper.getEmail(useremail);
		session.close();
		return list;
	}
	public static List<UserPersistence> getEmail_name(String username){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> list = mapper.getEmail_name(username);
		session.close();
		return list;
	}
	public static List<UserPersistence> getEmail_id(String userid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> list = mapper.getEmail_id(userid);
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
	//zyq_验证码通过--update
	public static void  updateUserState(String email){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.updateUserState(email);
		session.close();
	}
	//zyq_验证码失败--delete
	public static void deleteUser(String email){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.deleteUser(email);  
		session.close();
	} 
	  
	//zyq_获取用户信息
	public static List<UserPersistence> getUser()
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> userlist = mapper.getUser();
		session.close();
		return userlist;
		

	}
	//zyq_图片上传
	public static void updateUserImage(String email,String path){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.updateUserImage(email,path);
		session.close();
	}
	//zyq_用户个人信息完善
	public static void updateUserInfo(String email,String username,String usersex,String userbirthday,String address,String userbrief){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.updateUserInfo(email,username,usersex,userbirthday,address,userbrief);
		session.close();
	}
	//zyq_个人密码修改
	public static void updateUserPassword(String email,String password){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.updateUserPassword(email,password);
		session.close();
	}
	/*
	 * zyq_message_ajax_获得用户基本信息
	 */
	public static List<UserPersistence> getUserInfo(String username){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> list = mapper.getUserInfo(username);
		session.close();
		return list;
	}
	
	//zpz_获取用户所有信息
	public static List<UserPersistence> getAllUserInfo()
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> userlist = mapper.getAllUserInfo();
		session.close();
		return userlist;
	}
	
	//修改用户名
	//zyq_个人密码修改
	public static void updateUser(String userid, String username){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.updateUser(userid, username);
		session.close();
	}
	/*
	 *	通过用户ID获取到用户名 
	 */
	public static List<UserPersistence> getUserNameById(String UserId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		List<UserPersistence> list = mapper.getEmail_id(UserId);
		session.close();
		return list;
	}
	/**
	 * author:zhaoyanqing
	 * abstract:注册的用户信息同时添加到普通用户表
	 * data:2017年9月19日 19:49:45
	 */
	public static void addGeneralUser(GeneraluserPersistence generaluserPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		GeneraluserPersistenceMapper mapper = session.getMapper(GeneraluserPersistenceMapper.class);
		mapper.save(generaluserPersistence);
		session.close();
	}

	/**
	 * author:zhaoyanqing
	 * abstract:用户实体的save()方法
	 * data:2017年9月20日 09:52:13
	 * @return 
	 */
	public static void save(UserPersistence userPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.save(userPersistence);
		session.close();
	}
	//TEST
	public static void login_register2(String userid,String password,String username,int userstate,String userimage,String roleid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		UserPersistenceMapper mapper = session.getMapper(UserPersistenceMapper.class);
		mapper.login_register2(userid, password, username, userstate,userimage,roleid);
		session.close();
	}
}