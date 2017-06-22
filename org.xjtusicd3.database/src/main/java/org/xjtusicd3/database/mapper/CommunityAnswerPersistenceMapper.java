package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;

public interface CommunityAnswerPersistenceMapper extends IBaseDao<CommunityAnswerPersistence, String>{
	/*
	 * zyq_question_问题展示
	 */
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} ORDER BY TIME ASC")
	List<CommunityAnswerPersistence> question_CommunityAnswer(String communityquestionId);
	
	/*
	 * zyq_question_问题展示_best
	 */
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} AND ISBESTANSWER='1' ORDER BY TIME ASC")
	List<CommunityAnswerPersistence> question_CommunityAnswer_best(String communityquestionId);
	/*
	 * zyq_question_问题展示_other
	 */
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} AND ISBESTANSWER='0' ORDER BY TIME ASC LIMIT #{1},5")
	List<CommunityAnswerPersistence> question_CommunityAnswer_other(String communityquestionId,int startNumber);
	/*
	 * zyq_question_判断问题是否有最佳答案
	 */
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} AND ISBESTANSWER=#{1}")
	List<CommunityAnswerPersistence> question_iscurrentAnswer(String questionid,int isbest);

	/*
	 * zyq_question_获取用户点评论数
	 */
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE USERID=#{0}")
	List<CommunityAnswerPersistence> question_CommunityAnswer_userId(String userid);
	/*
	 * zyq_question_判断评论是否重复提交
	 */
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE USERID=#{0} AND CONTENT=#{1} AND COMMUNITYQUESTIONID=#{2}")
	List<CommunityAnswerPersistence> question_IsCommunityAnswer(String userid, String content,String questionId);
	//查看用户被点赞数量
	@Select("SELECT * FROM TBL_CommunityAnswer,TBL_Agree WHERE TBL_CommunityAnswer.COMMUNITYANSWERID=TBL_Agree.COMMUNITYANSWERID AND TBL_CommunityAnswer.USERID=#{0}")
	List<CommunityAnswerPersistence> getCommunityAnswerLike(String userId);
	/*
	 * zyq_question2_ajax_设置为最佳答案
	 */
	@Update("UPDATE TBL_CommunityAnswer SET ISBESTANSWER='1' WHERE COMMUNITYANSWERID=#{0}")
	void saveBestAnswer(String answerId);
	/*
	 * zyq_question_问题展示_byAnswerID
	 */
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYANSWERID=#{0}")
	List<CommunityAnswerPersistence> question_CommunityAnswerId(String communityanswerId);
	/*
	 * zyq_notice_查看评论是否通知
	 */
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE COMMUNITYQUESTIONID=#{0} AND ISNOTICE=#{1}")
	List<CommunityAnswerPersistence> notice_CommunityAnswer(String communityquestionid, int isnotice);
	/*
	 * zyq_personal2_查看问题的回答
	 */
	@Select("SELECT * FROM TBL_CommunityAnswer WHERE USERID=#{0} ORDER BY TIME DESC LIMIT #{1},#{2}")
	List<CommunityAnswerPersistence> personal2_ReplyCommunity(String userId,int startNumber,int number);
	
}
