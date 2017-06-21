package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.SharePersistenceMapper;
import org.xjtusicd3.database.model.SharePersistence;

public class ShareHelper {
	/*
	 * zyq_personal2_个人信息页面_查看分享的List
	 */
	public static List<SharePersistence> getShareList(String userId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SharePersistenceMapper mapper = session.getMapper(SharePersistenceMapper.class);
		List<SharePersistence> list = mapper.getShareList(userId);
		session.close();
		return list;
	}
	/*
	 * zyq_faq3_ajax_分享的增加
	 */
	public static void saveShare(String shareId,String userId,String time,String faqquestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SharePersistenceMapper mapper = session.getMapper(SharePersistenceMapper.class);
		mapper.saveShare(shareId,userId,time,faqquestionId);
		session.close();
	}
	public static void saveShare2(String shareId,String userId,String time,String faqquestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SharePersistenceMapper mapper = session.getMapper(SharePersistenceMapper.class);
		mapper.saveShare2(shareId,userId,time,faqquestionId);
		session.close();
	}
	/*
	 * zyq_faq3_ajax_查看分享byID
	 */
	public static List<SharePersistence> getShareList_ID(String userId,String questionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SharePersistenceMapper mapper = session.getMapper(SharePersistenceMapper.class);
		List<SharePersistence> list = mapper.getShareList_ID(userId,questionId);
		session.close();
		return list;
	}
	public static List<SharePersistence> getShareList_ID2(String userId,String questionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SharePersistenceMapper mapper = session.getMapper(SharePersistenceMapper.class);
		List<SharePersistence> list = mapper.getShareList_ID2(userId,questionId);
		session.close();
		return list;
	}
	/*
	 * zyq_faq3_ajax_删除分享byID
	 */
	public static void deleteShare(String shareid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SharePersistenceMapper mapper = session.getMapper(SharePersistenceMapper.class);
		mapper.deleteShare(shareid);
		session.close();
	}
	/*
	 * zyq_personal2_个人信息_查看关注人推荐的FAQ
	 */
	public static List<SharePersistence> getShareList_FAQ(String userId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SharePersistenceMapper mapper = session.getMapper(SharePersistenceMapper.class);
		List<SharePersistence> list = mapper.getShareList_FAQ(userId);
		session.close();
		return list;
	}
	public static List<SharePersistence> getShareList_FAQ_Limit(String userId,int startNumber,int number){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SharePersistenceMapper mapper = session.getMapper(SharePersistenceMapper.class);
		List<SharePersistence> list = mapper.getShareList_FAQ_Limit(userId,startNumber,number);
		session.close();
		return list;
	}
	public static List<SharePersistence> getShareList_FAQ_Limit_Time(String userId,int startNumber,int number,String time){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SharePersistenceMapper mapper = session.getMapper(SharePersistenceMapper.class);
		List<SharePersistence> list = mapper.getShareList_FAQ_Limit_Time(userId,startNumber,number,time);
		session.close();
		return list;
	}
	/*
	 * zyq_personal2_个人信息_查看关注人推荐的communityQuestion
	 */
	public static List<SharePersistence> getShareList_community(String userId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SharePersistenceMapper mapper = session.getMapper(SharePersistenceMapper.class);
		List<SharePersistence> list = mapper.getShareList_community(userId);
		session.close();
		return list;
	}
	public static List<SharePersistence> getShareList_community_Limit(String userId,int startNumber,int number){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SharePersistenceMapper mapper = session.getMapper(SharePersistenceMapper.class);
		List<SharePersistence> list = mapper.getShareList_community_Limit(userId,startNumber,number);
		session.close();
		return list;
	}
	public static List<SharePersistence> getShareList_community_Limit_Time(String userId,int startNumber,int number,String time){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		SharePersistenceMapper mapper = session.getMapper(SharePersistenceMapper.class);
		List<SharePersistence> list = mapper.getShareList_community_Limit_Time(userId,startNumber,number,time);
		session.close();
		return list;
	}
}
