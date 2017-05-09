package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.model.UserQuestionPersistence;

public interface UserQuestionPersistenceMapper
{
	//zpz_获取用户信息
		@Select("SELECT QUESTIONTITLE,QUESTIONTIME,ISFAQ FROM TBL_UserQuestion")
		List<UserQuestionPersistence> getUserQuestion();
}
