package org.xjtusicd3.database.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.AgreePersistenceMapper;
import org.xjtusicd3.database.model.AgreePersistence;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.UserPersistence;

public class AgreeHelper {
	/*
	 * zyq_question2_对于答案点赞
	 */
	public static void saveAgree(String username,String communityanswerId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AgreePersistenceMapper mapper = session.getMapper(AgreePersistenceMapper.class);
		List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
		Date date=new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
	    List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswerId(communityanswerId);
	    //判断这个赞是否为自己的
	    int isnotice = 0;
	    if (userPersistences.get(0).getUSERID().equals(communityAnswerPersistences.get(0).getUSERID())) {
			isnotice = 0;
		}else {
			isnotice = 1;
		}
		mapper.saveAgree(UUID.randomUUID().toString(),communityanswerId,userPersistences.get(0).getUSERID(),communityAnswerPersistences.get(0).getUSERID(),time,isnotice);
		session.close();
	}


	/**
	 * author:zzl
	 * abstract:question2_查看点赞表
	 * data:2017年9月22日14:47:45
	 */
	public static List<AgreePersistence> getAgree(String username,String communityanswerId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AgreePersistenceMapper mapper = session.getMapper(AgreePersistenceMapper.class);
		List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
		List<AgreePersistence> list = mapper.getAgree(communityanswerId,userPersistences.get(0).getUSERID());
		session.close();
		return list;
	}

	public static List<AgreePersistence> getAgree_id(String communityanswerId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AgreePersistenceMapper mapper = session.getMapper(AgreePersistenceMapper.class);
		List<AgreePersistence> list = mapper.getAgree_id(communityanswerId);
		session.close();
		return list;
	}
	/*
	 * zyq_question2_删除点赞
	 */
	public static void deleteAgree(String agreeid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AgreePersistenceMapper mapper = session.getMapper(AgreePersistenceMapper.class);
		mapper.deleteAgree(agreeid);
		session.close();
	}
	/*
	 * zyq_question_查看用户点赞
	 */
	public static List<AgreePersistence> getAgreebyUserId(String userid){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		AgreePersistenceMapper mapper = session.getMapper(AgreePersistenceMapper.class);
		List<AgreePersistence> list = mapper.getAgreebyUserId(userid);
		session.close();
		return list;
	}
}	
