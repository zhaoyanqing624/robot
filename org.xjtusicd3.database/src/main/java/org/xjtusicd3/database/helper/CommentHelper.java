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
	public static void saveComment(String commentid,String faqquestionid,String communityquestionid,String userid,String commentcont,String commenttime,String commentparentid,int isnotice,String touserid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		mapper.saveComment(commentid,faqquestionid,communityquestionid,userid,commentcont,commenttime,commentparentid,isnotice,touserid);
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
	/*
	 * zyq_faq3_查看faq下评论的数量
	 */
	public static List<CommentPersistence> getComment2(String faqquestionid,String parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.getComment2(faqquestionid,parentId);
		session.close();
		return list;
	}
	/*
	 * zyq_faq3_查看评论_查看更多
	 */
	public static List<CommentPersistence> getCommentMore(String faqquestionid,int startnumber,String parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.getCommentMore(faqquestionid,startnumber,parentId);
		session.close();
		return list;
	}
	/*
	 * zyq_question2_查看回复
	 */
	public static List<CommentPersistence> question2_getComment(String questionid,String parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.question2_getComment(questionid,parentId);
		session.close();
		return list;
	}
	/*
	 * zyq_question2_查看回复_前五条
	 */
	public static List<CommentPersistence> question2_getComment_Limit(String questionid,String parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.question2_getComment_Limit(questionid,parentId);
		session.close();
		return list;
	}
	/*
	 * zyq_question2_查看回复
	 */
	public static List<CommentPersistence> question2_getComment2(String answerId,String userId,String content,String questionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.question2_getComment2(answerId,userId,content,questionId);
		session.close();
		return list;
	}
	/*
	 * zyq_question2_查看回复的回复
	 */
	public static List<CommentPersistence> question2_getComment3(String answerId,String userId,String content,String questionId,String touserId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.question2_getComment3(answerId,userId,content,questionId,touserId);
		session.close();
		return list;
	}
	/*
	 * zyq_faq3_查看评论
	 */
	public static List<CommentPersistence> faq3_getComment(String commentId,String userId,String content,String questionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.faq3_getComment(commentId, userId, content, questionId);
		session.close();
		return list;
	}
	/*
	 * zyq_question2_获得更多的回复
	 */
	public static List<CommentPersistence> question2_getMoreComment(String questionId,String answerId,int startnumber){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.question2_getMoreComment(questionId,answerId,startnumber);
		session.close();
		return list;
	}
	/*
	 * zyq_faq3_查看评论byID
	 */
	public static List<CommentPersistence> faq3_getCommentById(String commentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.faq3_getCommentById(commentId);
		session.close();
		return list;
	}
	/*
	 * zyq_faq3_查看子评论下的回复
	 */
	public static List<CommentPersistence> faq3_getCommentReply(String parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.faq3_getCommentReply(parentId);
		session.close();
		return list;
	}
	/*
	 * zyq_faq3_查看子评论下的回复_更多回复
	 */
	public static List<CommentPersistence> faq3_getCommentReply_Limit(String parentId,int startnumber){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.faq3_getCommentReply_Limit(parentId,startnumber);
		session.close();
		return list;
	}
	/*
	 * zyq_faq3_ajax_删除自己的回复
	 */
	public static void deleteReply(String commentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		mapper.deleteReply(commentId);
		session.close();
	} 
}
