package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.CollectionPersistence;

public interface CollectionPersistenceMapper extends IBaseDao<CollectionPersistence, String>{
	/*
	 * zyq_question2_ajax_收藏
	 */
	@Select("SELECT * FROM TBL_Collection WHERE USERID=#{0} AND COMMUNITYANSWERID=#{1}")
	List<CollectionPersistence> getCollection(String userid, String answerId);
	/*
	 * zyq_faq3_ajax_收藏
	 */
	@Select("SELECT * FROM TBL_Collection WHERE USERID=#{0} AND FAQQUESTIONID=#{1}")
	List<CollectionPersistence> getCollection2(String userid, String questionId);
	/*
	 * zyq_question2_ajax_添加收藏
	 */
	@Insert("INSERT INTO TBL_Collection(COLLECTIONID,COMMUNITYANSWERID,USERID,TIME,ISNOTICE) VALUES (#{0},#{1},#{2},#{3},#{4})")
	void saveCollection(String collectionid, String communityanswerId, String userid, String time,int isnotice);
	/*
	 * zyq_faq3_ajax_添加收藏
	 */
	@Insert("INSERT INTO TBL_Collection(COLLECTIONID,FAQQUESTIONID,USERID,TIME,ISNOTICE) VALUES (#{0},#{1},#{2},#{3},#{4})")
	void saveCollection2(String collectionid, String questionId, String userid, String time,int isnotice);
	/*
	 * zyq_question2_faq3_删除收藏
	 */
	@Delete("DELETE FROM TBL_Collection WHERE COLLECTIONID=#{0}")
	void deleteCollection(String collectionid);
	/*
	 * zyq_personal2_ajax_获取收藏FAQ
	 */
	@Select("SELECT * FROM TBL_Collection WHERE USERID=#{0} AND COMMUNITYANSWERID IS NULL ORDER BY TIME DESC LIMIT #{1},#{2}")
	List<CollectionPersistence> getCollectionFaq(String userid,int startNumber,int number);
	/*
	 * zyq_personal2_ajax_获取收藏根据Id
	 */
	@Select("SELECT * FROM TBL_Collection WHERE FAQQUESTIONID=#{0} AND COMMUNITYANSWERID IS NULL")
	List<CollectionPersistence> getCollectionFaqList(String faqId);
	
}
