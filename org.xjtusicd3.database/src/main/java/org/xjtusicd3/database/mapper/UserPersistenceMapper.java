package org.xjtusicd3.database.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.UserPersistence;

public interface UserPersistenceMapper extends IBaseDao<UserPersistence, String>{
	/*
	 * login_ajax_注册
	 */
	@Insert("INSERT INTO `User`(`User`.UserId,`User`.UserEmail,`User`.UserPassword,`User`.UserName,`User`.UserState,`User`.UserRegister) VALUES (#{0},#{1},#{2},#{3},#{4},#{5})")
	public void login_register(String param1,String param2,String param3,String param4,int param5,String param6);
	//校验邮箱是否被注册
	@Select("Select userEmail from user where userEmail=#{email}")
	String getEmail(String email);
}
