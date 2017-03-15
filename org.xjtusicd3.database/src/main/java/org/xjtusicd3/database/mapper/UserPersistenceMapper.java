package org.xjtusicd3.database.mapper;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.UserPersistence;

public interface UserPersistenceMapper extends IBaseDao<UserPersistence, String>{
	//校验邮箱是否被注册
	@Select("Select userEmail from user where userEmail=#{email}")
	String getEmail(String email);
}
