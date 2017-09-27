package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.UserPersistence;

public interface UserPersistenceMapper extends IBaseDao<UserPersistence, String>{
	/*
	 * zyq_login_ajax_注册
	 */
	@Insert("INSERT INTO TBL_User(TBL_User.USERID,TBL_User.USEREMAIL,TBL_User.USERPASSWORD,TBL_User.USERNAME,TBL_User.USERSTATE,TBL_User.VERIFICATIONCODE,TBL_User.TIMEREMARKS,TBL_User.AVATAR) VALUES (#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7})")
	public void login_register(String userid,String email,String password,String username,int userstate,String identification_number,String time_stamp,String userimage);
	//zyq_校验邮箱是否被注册
	@Select("SELECT * FROM TBL_User WHERE USEREMAIL=#{0}")
	List<UserPersistence> getEmail(String useremail);
	@Select("SELECT * FROM TBL_User WHERE USERNAME=#{0}")
	List<UserPersistence> getEmail_name(String username);
	@Select("SELECT * FROM TBL_User WHERE USERID=#{0}")
	List<UserPersistence> getEmail_id(String userid);
	@Select("SELECT * FROM TBL_User WHERE USEREMAIL=#{0} AND USERPASSWORD=#{1}")
	List<UserPersistence> getEmail2(String param1,String param2);
	@Select("SELECT * FROM TBL_User WHERE USEREMAIL=#{0} AND VERIFICATIONCODE=#{1}")
	List<UserPersistence> getEmail3(String param1,String param2);
	//zyq_验证码通过
	@Update("UPDATE TBL_User SET TBL_User.USERSTATE='1' WHERE USEREMAIL=#{0}")
	public void updateUserState(String useremail);
	//zyq_验证码没有通过 删除
	@Delete("DELETE FROM TBL_User WHERE TBL_User.USEREMAIL=#{0}")
	public void deleteUser(String useremail);
	 
	//zpz_获取用户部分信息
	@Select("SELECT USERNAME,USERPASSWORD,USEREMAIL FROM TBL_User")
	List<UserPersistence> getUser();
	//zpz_获取用户所有信息
	@Select("SELECT * FROM TBL_User")
	List<UserPersistence> getAllUserInfo();

	
	//zyq_上传图片
	@Update("UPDATE TBL_User SET TBL_User.AVATAR=#{1} WHERE USERNAME=#{0}")
	public void updateUserImage(String username, String path);
	//zyq_用户个人信息完善
	@Update("UPDATE TBL_User SET USERNAME=#{1},GENDER=#{2},USERBIRTHDAY=#{3},USERADDRESS=#{4},USERSIGNATURE=#{5} WHERE USEREMAIL=#{0}")
	public void updateUserInfo(String email, String username, String usersex, String userbirthday, String address,String userbrief);
	//zyq_用户个人密码修改
	@Update("UPDATE TBL_User SET USERPASSWORD=#{1} WHERE USEREMAIL=#{0}")
	public void updateUserPassword(String email, String password);
	/*
	 * zyq_message_ajax_获得用户基本信息
	 */
	@Select("SELECT * FROM TBL_User WHERE USERNAME=#{0}")
	public List<UserPersistence> getUserInfo(String username);
	//zpz edit user information
//	@Update("UPDATE TBL_User SET USERPASSWORD=#{1} WHERE USEREMAIL=#{0}")
//	public List<UserPersistence> updateUserInfo(String username,String email,String address,String Createtime);
	@Update("UPDATE TBL_User SET USERNAME=#{1} WHERE USERID=#{0}")
	public void updateUser(String userid,String username);
	
	//zpz_通过id获取用户名
	@Select("SELECT * FROM TBL_User WHERE USERID=#{0}")
	public List<UserPersistence> getUserNameById(String UserId);
	/**
	 * author:zhaoyanqing
	 * abstract:注册的用户信息同时添加到普通用户表
	 * data:2017年9月19日 19:49:45
	 */
	public List<UserPersistence> addGeneralUser(String userid);
	
	@Insert("INSERT INTO TBL_User(TBL_User.USERID,TBL_User.USERPASSWORD,TBL_User.USERNAME,TBL_User.USERSTATE,TBL_User.AVATAR,TBL_User.ROLEID) VALUES (#{0},#{1},#{2},#{3},#{4},#{5})")
	public void login_register2(String userid, String password, String username, int userstate, String userimage,
			String roleid);
	/**
	 * author:zzl
	 * abstract:判断用户是否登录成功
	 * data:2017年9月21日09:39:35
	 */
	@Select("SELECT * FROM TBL_User WHERE USERNAME=#{0} AND USERPASSWORD=#{1}")
	public List<UserPersistence> isLogin(String username, String password);
	
	/**
	 * author:zzl
	 * abstract:获取登录用户id
	 * data:2017年9月21日10:18:57
	 */
	@Select("SELECT USERID FROM TBL_User WHERE USERNAME=#{0} OR USEREMAIL=#{0}")
	public String loginUserInfo(String nameOrEmail);
	
	/**
	 * author:zzl
	 * abstract:获取登录用户名
	 * data:2017年9月21日10:21:20
	 */
	@Select("SELECT * FROM TBL_User WHERE USERNAME=#{0} OR USEREMAIL=#{0}")
	public List<UserPersistence> getUserNameById2(String nameOrEmail);
	
	/**
	 * author:zzl
	 * abstract:修改用户信息
	 * data:2017年9月21日10:21:20
	 */
	@Update("UPDATE TBL_User SET USERNAME=#{1},GENDER=#{2},USERBIRTHDAY=#{3},USERADDRESS=#{4},USERSIGNATURE=#{5} WHERE USERID=#{0}")
	public void updateUserInfo2(String userid, String username, String usersex, String userbirthday, String address,String userbrief);
	
	/**
	 * author:zzl
	 * abstract:修改密码
	 * data:2017年9月21日17:46:18
	 */
	@Update("UPDATE TBL_User SET USERPASSWORD=#{1} WHERE USERNAME=#{0}")
	public void updateUserPassword2(String userid, String password);
	
	/**
	 * author:zzl
	 * abstract:获取登录用户信息
	 * data:2017年9月26日16:48:15
	 */
	@Select("SELECT * FROM TBL_User WHERE (USERNAME=#{0} OR USEREMAIL=#{0}) AND USERPASSWORD=#{1}")
	public List<UserPersistence> loginUser(String nameOrEmail, String password);
}
