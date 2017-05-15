package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.CommentPersistenceMapper;
import org.xjtusicd3.database.model.CommentPersistence;

public class CommentHelper {
	/*
	 * zyq_faq3_ajax_添加评论
	 */
	public static void saveComment(String commentid,String faqquestionid,String communityquestionid,String userid,String commentcont,String commenttime,String commentparentid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		mapper.saveComment(commentid,faqquestionid,communityquestionid,userid,commentcont,commenttime,commentparentid);
		session.close();
	}
	/*
	 * zyq_faq3_查看评论
	 */
	public static List<CommentPersistence> getComment(String faqquestionid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.getComment(faqquestionid);
		session.close();
		return list;
	}
}