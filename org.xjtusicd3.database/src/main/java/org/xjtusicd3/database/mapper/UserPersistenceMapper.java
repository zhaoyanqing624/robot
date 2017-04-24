package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.UserPersistence;

public interface UserPersistenceMapper extends IBaseDao<UserPersistence, String>{
	/*
	 * login_ajax_注册
	 */
	@Insert("INSERT INTO `User`(`User`.UserId,`User`.UserEmail,`User`.UserPassword,`User`.UserName,`User`.UserState,`User`.Identification_number,`User`.Time_stamp) VALUES (#{0},#{1},#{2},#{3},#{4},#{5},#{6})")
	public void login_register(String param1,String param2,String param3,String param4,int param5,String param6,String param7);
	//校验邮箱是否被注册
	@Select("SELECT * FROM User WHERE UserEmail=#{0}")
	List<UserPersistence> getEmail(String useremail);
}
