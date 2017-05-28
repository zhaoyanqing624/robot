package org.xjtusicd3.database.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.CollectionPersistenceMapper;
import org.xjtusicd3.database.model.CollectionPersistence;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.UserPersistence;

public class CollectionHelper {
	/*
	 * zyq_question2_ajax_收藏
	 */
	public static List<CollectionPersistence> getCollection(String useremail,String answerId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CollectionPersistenceMapper mapper = session.getMapper(CollectionPersistenceMapper.class);
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		List<CollectionPersistence> list = mapper.getCollection(userPersistences.get(0).getUSERID(),answerId);
		session.close();
		return list;
	}
	/*
	 * zyq_question2_ajax_添加收藏
	 */
	public static void saveCollection(String useremail,String communityanswerId){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CollectionPersistenceMapper mapper = session.getMapper(CollectionPersistenceMapper.class);
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
		Date date=new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
	    //判断是否为自己收藏
	    List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswerId(communityanswerId);
	    int isnotice = 0;
	    if(userPersistences.get(0).getUSERID().equals(communityAnswerPersistences.get(0).getUSERID())){
	    	isnotice = 0;
	    }else {
			isnotice = 1;
		}
		mapper.saveCollection(UUID.randomUUID().toString(),communityanswerId,userPersistences.get(0).getUSERID(),time,isnotice);
		session.close();
	}
	/*
	 * zyq_question2_删除收藏
	 */
	public static void deleteCollection(String collectionid) {
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		CollectionPersistenceMapper mapper = session.getMapper(CollectionPersistenceMapper.class);
		mapper.deleteCollection(collectionid);
		session.close();
	}
}
