package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.CommentPersistence;

public interface CommentPersistenceMapper extends IBaseDao<CommentPersistence, String>{
	/*
	 * zyq_faq3_ajax_添加评论
	 */
	@Insert("INSERT INTO TBL_Comment(COMMENTID,FAQQUESTIONID,COMMUNITYQUESTIONID,USERID,COMMENTCONTENT,COMMENTTIME,COMMENTPARENTID) VALUES (#{0},#{1},#{2},#{3},#{4},#{5},#{6})")
	void saveComment(String commentid, String faqquestionid, String communityquestionid, String userid,String commentcont, String commenttime, String commentparentid);
	/*
	 * zyq_faq3_查看评论
	 */
	@Select("SELECT * FROM TBL_Comment WHERE FAQQUESTIONID=#{0}")
	List<CommentPersistence> getComment(String faqquestionid);

}
