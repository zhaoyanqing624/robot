package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.CommentPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;

public interface CommentPersistenceMapper extends IBaseDao<CommentPersistence, String>{
	/*
	 * zyq_faq3_ajax_添加评论
	 */
	@Insert("INSERT INTO TBL_Comment(COMMENTID,FAQQUESTIONID,COMMUNITYQUESTIONID,USERID,COMMENTCONTENT,COMMENTTIME,COMMENTPARENTID,ISNOTICE,TOUSERID) VALUES (#{0},#{1},#{2},#{3},#{4},#{5},#{6},#{7},#{8})")
	void saveComment(String commentid, String faqquestionid, String communityquestionid, String userid,String commentcont, String commenttime, String commentparentid,int isnotice,String touserid);
	/*
	 * zyq_faq3_查看评论
	 */
	@Select("SELECT * FROM TBL_Comment WHERE FAQQUESTIONID=#{0} ORDER BY COMMENTTIME ASC")
	List<CommentPersistence> getComment(String faqquestionid);
	/*
	 * zyq_faq3_查看评论的数量
	 */
	@Select("SELECT * FROM TBL_Comment WHERE FAQQUESTIONID=#{0} AND COMMENTPARENTID=#{1} ORDER BY COMMENTTIME ASC")
	List<CommentPersistence> getComment2(String faqquestionid,String parentId);
	/*
	 * zyq_faq3_查看评论_查看更多
	 */
	@Select("SELECT * FROM TBL_Comment WHERE FAQQUESTIONID=#{0} AND COMMENTPARENTID=#{2} ORDER BY COMMENTTIME ASC LIMIT #{1},5")
	List<CommentPersistence> getCommentMore(String faqquestionid, int startnumber,String parentId);
	/*
	 * zyq_question2_查看回复
	 */
	@Select("SELECT * FROM TBL_Comment WHERE COMMUNITYQUESTIONID=#{0} AND COMMENTPARENTID=#{1} ORDER BY COMMENTTIME ASC")
	List<CommentPersistence> question2_getComment(String questionid, String parentId);
	/*
	 * zyq_question2_查看回复_前五条
	 */
	@Select("SELECT * FROM TBL_Comment WHERE COMMUNITYQUESTIONID=#{0} AND COMMENTPARENTID=#{1} ORDER BY COMMENTTIME ASC LIMIT 5")
	List<CommentPersistence> question2_getComment_Limit(String questionid, String parentId);
	/*
	 * zyq_question2_查看回复
	 */
	@Select("SELECT * FROM TBL_Comment WHERE COMMENTPARENTID=#{0} AND USERID=#{1} AND COMMENTCONTENT=#{2} AND COMMUNITYQUESTIONID=#{3} ORDER BY COMMENTTIME ASC")
	List<CommentPersistence> question2_getComment2(String answerId, String userId, String content ,String questionId);
	/*
	 * zyq_question2_查看回复的回复
	 */
	@Select("SELECT * FROM TBL_Comment WHERE COMMENTPARENTID=#{0} AND USERID=#{1} AND COMMENTCONTENT=#{2} AND COMMUNITYQUESTIONID=#{3} AND TOUSERID=#{4} ORDER BY COMMENTTIME ASC")
	List<CommentPersistence> question2_getComment3(String answerId, String userId, String content ,String questionId,String touserId);
	/*
	 * zyq_faq3_查看回复
	 */
	@Select("SELECT * FROM TBL_Comment WHERE COMMENTPARENTID=#{0} AND USERID=#{1} AND COMMENTCONTENT=#{2} AND FAQQUESTIONID=#{3} ORDER BY COMMENTTIME ASC")
	List<CommentPersistence> faq3_getComment(String commentId, String userId, String content ,String questionId);
	/*
	 * zyq_question2_获得更多的回复
	 */
	@Select("SELECT * FROM TBL_Comment WHERE COMMUNITYQUESTIONID=#{0} AND COMMENTPARENTID=#{1} ORDER BY COMMENTTIME ASC LIMIT #{2},5 ")
	List<CommentPersistence> question2_getMoreComment(String questionId, String answerId, int startnumber);
	/*
	 * zyq_faq3_查看评论byID
	 */
	@Select("SELECT * FROM TBL_Comment WHERE COMMENTID=#{0}")
	List<CommentPersistence> faq3_getCommentById(String commentId);
	/*
	 * zyq_faq3_查看子评论下的回复
	 */
	@Select("SELECT * FROM TBL_Comment WHERE COMMENTPARENTID=#{0} ORDER BY COMMENTTIME ASC ")
	List<CommentPersistence> faq3_getCommentReply(String parentId);
	/*
	 * zyq_faq3_查看子评论下的回复_更多回复
	 */
	@Select("SELECT * FROM TBL_Comment WHERE COMMENTPARENTID=#{0} ORDER BY COMMENTTIME ASC LIMIT #{1},5")
	List<CommentPersistence> faq3_getCommentReply_Limit(String parentId, int startnumber);
	/*
	 * zyq_faq3_ajax_删除自己的回复
	 */
	@Delete("DELETE FROM TBL_Comment WHERE COMMENTID=#{0}")
	void deleteReply(String commentId);
	/*
	 * zyq_notice_pushlet_查看评论的回复
	 */
	@Select("SELECT * FROM TBL_Comment WHERE COMMUNITYQUESTIONID=#{0} AND COMMENTPARENTID=#{1} AND TOUSERID IS NULL AND ISNOTICE=#{2}")
	List<CommentPersistence> notice_getComment(String communityquestionId, String commentId,int isnotice);
	/*
	 * zyq_notice_查看FAQ的评论
	 */
	@Select("SELECT * FROM TBL_Comment WHERE FAQQUESTIONID=#{0} AND COMMENTPARENTID=#{1} AND ISNOTICE=#{2}")
	List<CommentPersistence> notice_getFaqComment(String faqquestionId,String parentId,int isnotice);
	@Select("SELECT * FROM TBL_Comment WHERE USERID=#{0} AND COMMENTPARENTID=#{1}")
	List<CommentPersistence> notice_getFaqComment2(String userId,String parentId);
	/*
	 * zyq_notice_pushlet_查看评论的回复的回复
	 */
	@Select("SELECT * FROM TBL_Comment WHERE COMMUNITYQUESTIONID=#{0} AND COMMENTPARENTID=#{1} AND TOUSERID IS NOT NULL AND ISNOTICE=#{2}")
	List<CommentPersistence> notice_getReply(String communityquestionId, String commentId,int isnotice);
	/*
	 * zyq_notice_查看FAQ评论的回复
	 */
	@Select("SELECT * FROM TBL_Comment WHERE COMMENTPARENTID=#{0} AND TOUSERID IS NULL AND ISNOTICE=#{1}")
	List<CommentPersistence> notice_getFaqReply(String parentId,int isnotice);
	@Select("SELECT * FROM TBL_Comment WHERE COMMENTPARENTID=#{0} AND TOUSERID IS NOT NULL AND ISNOTICE=#{1}")
	List<CommentPersistence> notice_getFaqReply2(String parentId,int isnotice);
	/*
	 * zyq_ajax_更改消息通知的被查阅后的状态
	 */
	//更改知识库的评论、回复、回复的回复以及问吧的回复、回复的回复
	@Update("UPDATE TBL_Comment SET TBL_Comment.ISNOTICE=#{0} WHERE COMMENTID=#{1}")
	void updateNotice(int isnotice,String id);
	//更改问吧的评论
	@Update("UPDATE TBL_CommunityAnswer SET TBL_CommunityAnswer.ISNOTICE=#{0} WHERE COMMUNITYANSWERID=#{1}")
	void updateNotice2(int isnotice,String id);
	//删除消息通知
	@Update("UPDATE TBL_Comment SET TBL_Comment.ISNOTICE=#{0} WHERE COMMENTID=#{1}")
	void deleteNotice(int i, String id);
	@Update("UPDATE TBL_CommunityAnswer SET TBL_CommunityAnswer.ISNOTICE=#{0} WHERE COMMUNITYANSWERID=#{1}")
	void deleteNotice2(int i, String id);
	/*
	 * zyq_personal2_查看评论的FAQ
	 */
	@Select("SELECT * FROM TBL_Comment WHERE USERID=#{0} AND COMMENTPARENTID=#{1} ORDER BY COMMENTTIME DESC LIMIT #{2},#{3}")
	List<CommentPersistence> personal2_getFaqComment_Limit(String userId,String parentId,int startNumber,int number);
	/*
	 * zyq_faq1_查看活跃用户
	 */
	@Select("SELECT USERID,count(USERID) as NUM FROM TBL_Comment WHERE STR_TO_DATE(COMMENTTIME,'%Y-%m-%d')=STR_TO_DATE(#{0},'%Y-%m-%d') GROUP BY USERID ORDER BY NUM DESC LIMIT 10")
	List<CommentPersistence> faq1_userActive(String time);
	/*
	 * zyq_faq1_查看活跃用户_按周查询
	 */
	@Select("SELECT USERID,count(USERID) as NUM FROM TBL_Comment WHERE STR_TO_DATE(COMMENTTIME,'%Y-%m-%d')<STR_TO_DATE(#{0},'%Y-%m-%d') AND STR_TO_DATE(COMMENTTIME,'%Y-%m-%d')>STR_TO_DATE(#{1},'%Y-%m-%d') GROUP BY USERID ORDER BY NUM DESC LIMIT 10")
	List<CommentPersistence> faq1_userActiveWeek(String time, String time2);
	
	/**
	 * author:zzl
	 * abstract:获取评论数
	 * data:2017年9月15日19:22:12
	 */
	@Select("SELECT * FROM TBL_Comment WHERE FAQQUESTIONID=#{0}")
	List<CommentPersistence> commentInfo(String faqquestionId);
}
