package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;

public interface CommunityQuestionPersistenceMapper extends IBaseDao<CommunityQuestionPersistence, String>
{
	/*
	 * zyq_ajax_question校验是否重复添加
	 */
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE USERID=#{0} AND TITLE=#{1}")
	List<CommunityQuestionPersistence> question_iscurrent(String userid, String questiontitle);

}
