package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;

public interface CommunityAnswerPersistenceMapper extends IBaseDao<CommunityAnswerPersistence, String>{
	/*
	 * zyq_question_问题展示
	 */
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} ORDER BY TIME DESC")
	List<CommunityAnswerPersistence> question_CommunityAnswer(String communityquestionId);
	/*
	 * zyq_question_判断问题是否有最佳答案
	 */
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} AND ISBESTANSWER=#{1}")
	List<CommunityAnswerPersistence> question_iscurrentAnswer(String questionid,int isbest);
	/*
	 * zyq_question_获取用户点赞数量
	 */
	@Select("SELECT SUM(TBL_CommunityAnswer.LIKES) FROM TBL_CommunityAnswer")
	int likesNumber();
	/*
	 * zyq_question_获取用户点评论数
	 */
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE USERID=#{0}")
	List<CommunityAnswerPersistence> question_CommunityAnswer_userId(String userid);
	
}
