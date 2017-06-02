package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.CommentPersistence;

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


}
