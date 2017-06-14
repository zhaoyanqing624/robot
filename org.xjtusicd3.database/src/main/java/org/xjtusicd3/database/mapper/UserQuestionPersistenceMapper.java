package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.database.model.UserQuestionPersistence;

public interface UserQuestionPersistenceMapper
{
	//zpz_获取用户问题
		@Select("SELECT * FROM TBL_UserQuestion")
		List<UserQuestionPersistence> getUserQuestion();
		
	//zpz _find userQuestion by UserQuestionId
				@Select("SELECT * FROM TBL_UserQuestion WHERE USERQUESTIONID=#{0}")
				List<UserQuestionPersistence> getUserQuestionById(String userQuestionId);
		
	//zpz_删除用户问题信息
		@Delete("DELETE FROM TBL_UserQuestion WHERE TBL_User.USERQUESTIONID=#{0}")
		public void deleteUserQuestion(String userQuestionId);
		
		
}
