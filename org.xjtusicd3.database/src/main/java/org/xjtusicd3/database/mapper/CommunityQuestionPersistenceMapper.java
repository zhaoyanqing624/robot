package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
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
	/*
	 * zyq_ajax_question的增加
	 */
	@Insert("INSERT INTO TBL_CommunityQuestion(COMMUNITYQUESTIONID,TIME,TITLE,CONTENT,CLASSIFYID,USERID,SCAN,USERQUESTIONID,ISANSWER) VALUES (#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8})")
	void saveCommunityQuestion(String id, String time, String title, String content, String classifyid, String userid, String scan, String userquestionid,int isanswer);
	/*
	 * zyq_question_问题展示_根据类别ID
	 */
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE CLASSIFYID=#{0} ORDER BY TIME DESC")
	List<CommunityQuestionPersistence> question_getCommunity(String classifyid);
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE CLASSIFYID=#{0} AND ISANSWER=#{1} ORDER BY TIME DESC")
	List<CommunityQuestionPersistence> question_getCommunity2(String classifyid,int isanswer);
	/*
	 * zyq_question_问题展示_根据是否有答案
	 */
	@Select("SELECT * FROM TBL_CommunityQuestion  ORDER BY TIME DESC LIMIT #{0},5")
	List<CommunityQuestionPersistence> question_getCommunity_isanswer(int startnumber);
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE ISANSWER=#{1} ORDER BY TIME DESC")
	List<CommunityQuestionPersistence> question_getCommunity2_isanswer(int isanswer);
	/*
	 * zyq_question2_问题内容详情
	 */
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE COMMUNITYQUESTIONID=#{0}")
	List<CommunityQuestionPersistence> question2_getCommunity(String questionId);
	/*
	 * zyq_notice_查询用户的提问
	 */
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE USERID=#{0}")
	List<CommunityQuestionPersistence> notice_CommunityQuestion(String userid);
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE USERID=#{0} ORDER BY TIME DESC LIMIT #{1},#{2}")
	List<CommunityQuestionPersistence> notice_CommunityQuestion_Limit(String userid,int startNumber,int number);
	@Select("SELECT * FROM TBL_CommunityQuestion WHERE USERID=#{0} AND STR_TO_DATE(TIME,'%Y-%m-%d %H:%i')<STR_TO_DATE(#{3},'%Y-%m-%d %H:%i') ORDER BY TIME DESC LIMIT #{1},#{2}")
	List<CommunityQuestionPersistence> notice_CommunityQuestion_Limit_Time(String userid,int startNumber,int number,String time);
	/*
	 * zpz_get all community question
	 */
	@Select("SELECT * FROM TBL_CommunityQuestion")
	List<CommunityQuestionPersistence> getAllCommunityQuestion();
}
