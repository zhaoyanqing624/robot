package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.SharePersistence;

public interface SharePersistenceMapper extends IBaseDao<SharePersistence, String>{
	/*
	 * zyq_personal2_个人信息页面_查看分享的List
	 */
	@Select("SELECT * FROM TBL_Share WHERE USERID=#{0}")
	List<SharePersistence> getShareList(String userId);
	/*
	 * zyq_faq3_ajax_查看分享byID
	 */
	@Select("SELECT * FROM TBL_Share WHERE USERID=#{0} AND FAQQUESTIONID=#{1}")
	List<SharePersistence> getShareList_ID(String userId, String questionId);
	@Select("SELECT * FROM TBL_Share WHERE USERID=#{0} AND COMMUNITYQUESTIONID=#{1}")
	List<SharePersistence> getShareList_ID2(String userId, String questionId);
	/*
	 * zyq_faq3_ajax_删除分享byID
	 */
	@Delete("DELETE FROM TBL_Share WHERE SHAREID=#{0}")
	void deleteShare(String shareid);
	/*
	 * zyq_faq3_ajax_分享的增加
	 */
	@Insert("INSERT INTO TBL_Share(TBL_Share.SHAREID,TBL_Share.USERID,TBL_Share.TIME,TBL_Share.FAQQUESTIONID,TBL_Share.COMMUNITYQUESTIONID) VALUES (#{0},#{1},#{2},#{3},NULL)")
	void saveShare(String shareId, String userId, String time, String faqquestionId);
	@Insert("INSERT INTO TBL_Share(TBL_Share.SHAREID,TBL_Share.USERID,TBL_Share.TIME,TBL_Share.FAQQUESTIONID,TBL_Share.COMMUNITYQUESTIONID) VALUES (#{0},#{1},#{2},NULL,#{3})")
	void saveShare2(String shareId, String userId, String time, String faqquestionId);
	/*
	 * zyq_personal2_个人信息_查看关注人推荐的FAQ
	 */
	@Select("SELECT * FROM TBL_Share WHERE USERID=#{0} AND COMMUNITYQUESTIONID IS NULL")
	List<SharePersistence> getShareList_FAQ(String userId);
	@Select("SELECT * FROM TBL_Share WHERE USERID=#{0} AND COMMUNITYQUESTIONID IS NULL ORDER BY TIME DESC LIMIT #{1},#{2}")
	List<SharePersistence> getShareList_FAQ_Limit(String userId,int startNumber,int number);
	@Select("SELECT * FROM TBL_Share WHERE USERID=#{0} AND COMMUNITYQUESTIONID IS NULL AND STR_TO_DATE(TIME,'%Y-%m-%d %H:%i')>STR_TO_DATE(#{3},'%Y-%m-%d %H:%i') ORDER BY TIME DESC LIMIT #{1},#{2}")
	List<SharePersistence> getShareList_FAQ_Limit_Time(String userId,int startNumber,int number,String time);
	/*
	 * zyq_personal2_个人信息_查看关注人推荐的communityQuestion
	 */
	@Select("SELECT * FROM TBL_Share WHERE USERID=#{0} AND FAQQUESTIONID IS NULL")
	List<SharePersistence> getShareList_community(String userId);
	@Select("SELECT * FROM TBL_Share WHERE USERID=#{0} AND FAQQUESTIONID IS NULL ORDER BY TIME DESC LIMIT #{1},#{2}")
	List<SharePersistence> getShareList_community_Limit(String userId,int startNumber,int number);
	@Select("SELECT * FROM TBL_Share WHERE USERID=#{0} AND FAQQUESTIONID IS NULL AND STR_TO_DATE(TIME,'%Y-%m-%d %H:%i')>STR_TO_DATE(#{3},'%Y-%m-%d %H:%i') ORDER BY TIME DESC LIMIT #{1},#{2}")
	List<SharePersistence> getShareList_community_Limit_Time(String userId,int startNumber,int number,String time);
}
