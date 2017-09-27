package org.xjtusicd3.database.helper;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.QuestionPersistenceMapper;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;

public class QuestionHelper {
	/*
	 * spider_知识库问题的添加
	 */
	public static void save(QuestionPersistence questionPersistence){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		mapper.save(questionPersistence);
		session.close();
	}
	/*
	 * robot-分类
	 */
	public static List<QuestionPersistence> SecondClassify_robot(String ClassifyId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.SecondClassify_robot(ClassifyId);
		session.close();
		return list;
	}
	/*
	 * faq2_知识列表
	 */
	
	public static List<QuestionPersistence> faq2_faqlist(String ClassifyId,int pageNow){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		int a  = (pageNow-1)*5;
		List<QuestionPersistence> list = mapper.faq2_faqlist(ClassifyId,a);
		session.close();
		return list;
	}
	public static List<UserPersistence> faq2_userlist(String UserId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<UserPersistence> list = mapper.faq2_userlist(UserId);
		session.close();
		return list;
	}
	public static int pageTotal(String ClassifyId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		int pageTotal = mapper.pageTotal(ClassifyId);
		session.close();
		return pageTotal;
	}
	public static String faq2_userId(String QuestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		String UserId = mapper.faq2_UserId(QuestionId);
		session.close();
		return UserId;
	}
	/*
	 * faq3_知识内容
	 */
	public static List<QuestionPersistence> faq3_faqcontent(String faqId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq3_faqcontent(faqId);
		session.close();
		return list;
	}
	public static List<QuestionPersistence> faq3_faqcontent_title(String faqtitle){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq3_faqcontent_title(faqtitle);
		session.close();
		return list;
	}
	/*
	 * faqadd_校验知识是否重复增添
	 */
	public static List<QuestionPersistence> faqadd_iscurrent(String faqtitle,String useremail){
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faqadd_iscurrent(faqtitle,userPersistences.get(0).getUSERID());
		session.close();
		return list;
	}
	
	/**
	 * author:zzl
	 * abstract:faqadd_校验知识是否重复增添
	 * data:2017年9月22日11:54:01
	 */
	public static List<QuestionPersistence> faqadd_iscurrent2(String faqtitle,String username){
		List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faqadd_iscurrent(faqtitle,userPersistences.get(0).getUSERID());
		session.close();
		return list;
	}
	
	
	
	/*
	 * faq3_根据知识ID找类型classify
	 */
	public static String faqclassify(String QuestionId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		String ClassifyId = mapper.faq3_faqclassifyId(QuestionId);
		session.close();
		return ClassifyId;
	}
	/*
	 * zpz_get faq information
	 */
	public static List<QuestionPersistence> getFaq(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.getFaq();
		session.close();
		return list;
	}
	/*
	 * zpz_delete faq
	 */
	public static void deleteFAQ(String faqId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		mapper.deleteFAQquestion(faqId);  
		session.close();
	}
	
	/*
	 * zyq_personal2_查看自己是否有FAQ
	 */
	public static List<QuestionPersistence> personal2_faq(String userId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.personal2_faq(userId);
		session.close();
		return list;
	}
	public static List<QuestionPersistence> personal2_faq_Limit(String userId,int startNumber,int number){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.personal2_faq_Limit(userId,startNumber,number);
		session.close();
		return list;
	}
	public static List<QuestionPersistence> personal2_faq_Limit_Time(String userId,int startNumber,int number,String time){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.personal2_faq_Limit_Time(userId,startNumber,number,time);
		session.close();
		return list;
	}
	//判断是创建知识还是修改知识
	public static List<QuestionPersistence> personal2_Ismodify(String faqquestionid, String modifynumber) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.personal2_Ismodify(faqquestionid,modifynumber);
		session.close();
		return list;
	}

	
	/*
	 * zpz_get faq count
	 */
	public static int getFaqTotal1(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		int faqTotal = mapper.FaqTotal();
		session.close();
		return faqTotal;
	}

	/*
	 * zyq_robot_查看所以faq的信息
	 */
	public static List<QuestionPersistence> getFaqTotal(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.getFaqTotal();
		session.close();
		return list;

	}

	
	/*
	 * zyq_faq_查看用户动态
	 */
	public static List<QuestionPersistence> faq_userDynamics(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq_userDynamics();
		session.close();
		return list;
	}
	/*
	 * faq_按时间推荐
	 */
	public static List<QuestionPersistence> faq_recommend_Limit(int startnum) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faq_recommend_Limit(startnum);
		session.close();
		return list;
	}
	
	/**
	 * author:zzl
	 * abstract:获取分类下faq具体信息
	 * data:2017年9月15日10:27:38
	 */
	public static List<QuestionPersistence> questionView(String parentId, int startnum) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.questionView(parentId,startnum);
		session.close();
		return list;
	}

	/**
	 * author:zzl
	 * abstract:通过一个问题分类获取其父分类下的所有子分类
	 * data:2017年9月15日10:07:11
	 */
//	public static String faqclassifies(String faq_classifyId) {
//		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
//		ClassifyPersistenceMapper mapper = session.getMapper(ClassifyPersistenceMapper.class);
//		List<ClassifyPersistence> list = mapper.faq_classifyIds(parentId);
//		session.close();
//		return list;
//	}

	
//	/*
//	 * 2017年9月14日23:00:08
//	 */
//	public static List<QuestionPersistence> user_recommend_Limit(String userid, int startnum) {
//		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
//		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
//		List<QuestionPersistence> list = mapper.user_recommend_Limit(userid,startnum);
//		session.close();
//		return list;
//	}
	
	/**
	 * author:zzl
	 * abstract:推荐知识_根据收藏量推荐前4个
	 * data:2017年9月17日19:53:14
	 */
	public static List<QuestionPersistence> faqInfo_limit(String faqParentId) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		List<QuestionPersistence> list = mapper.faqInfo_limit(faqParentId);
		session.close();
		return list;
	}
	/**
	 * author:zhaoyanqing
	 * abstract:对访问FAQ页面的浏览量进行增加
	 * data:2017年9月18日 16:12:59
	 */
	public static void updateFAQScan(String faqquestionid){
		List<QuestionPersistence> questionPersistences = QuestionHelper.faq3_faqcontent(faqquestionid);
		int faqScan = Integer.parseInt(questionPersistences.get(0).getSCAN());
		faqScan++;
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		QuestionPersistenceMapper mapper = session.getMapper(QuestionPersistenceMapper.class);
		mapper.updateFAQScan(faqquestionid,Integer.toString(faqScan));
		session.close();
	}
}
