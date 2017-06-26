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
	/*
	 * zyq_notice_pushlet_查看评论的回复
	 */
	public static List<CommentPersistence> notice_getComment(String communityquestionId,String commentId,int isnotice){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.notice_getComment(communityquestionId,commentId,isnotice);
		session.close();
		return list;
	}
	/*
	 * zyq_notice_查看FAQ的评论
	 */
	public static List<CommentPersistence> notice_getFaqComment(String faqquestionId,String parentId,int isnotice){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.notice_getFaqComment(faqquestionId,parentId,isnotice);
		session.close();
		return list;
	}
	public static List<CommentPersistence> notice_getFaqComment2(String userId,String parentId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.notice_getFaqComment2(userId,parentId);
		session.close();
		return list;
	}
	/*
	 * zyq_notice_pushlet_查看评论的回复的回复
	 */
	public static List<CommentPersistence> notice_getReply(String communityquestionId,String commentId,int isnotice){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.notice_getReply(communityquestionId,commentId,isnotice);
		session.close();
		return list;
	}
	/*
	 * zyq_notice_查看FAQ评论的回复
	 */
	public static List<CommentPersistence> notice_getFaqReply(String parentId,int isnotice) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.notice_getFaqReply(parentId,isnotice);
		session.close();
		return list;
	}
	public static List<CommentPersistence> notice_getFaqReply2(String parentId,int isnotice) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.notice_getFaqReply2(parentId,isnotice);
		session.close();
		return list;
	}
	/*
	 * zyq_ajax_更改消息通知的被查阅后的状态
	 */
	//更改知识库的评论、回复、回复的回复以及问吧的回复、回复的回复
	public static void updateNotice(String id) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		mapper.updateNotice(2,id);
		session.close();
	}
	//更改问吧的评论
	public static void updateNotice2(String id) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		mapper.updateNotice2(2,id);
		session.close();
	}
	//删除消息通知
	public static void deleteNotice(String id) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		mapper.deleteNotice(0,id);
		session.close();
	}
	public static void deleteNotice2(String id) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		mapper.deleteNotice2(0,id);
		session.close();
	}
	/*
	 * zyq_personal2_ajax_获取FAQ的评论
	 */
	public static List<CommentPersistence> personal2_getFaqComment_Limit(String userId,String parentId,int startNumber,int number) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CommentPersistenceMapper mapper = session.getMapper(CommentPersistenceMapper.class);
		List<CommentPersistence> list = mapper.personal2_getFaqComment_Limit(userId,parentId,startNumber,number);
		session.close();
		return list;
	}
}
