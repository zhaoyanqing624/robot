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
	 * zyq_question2_ajax_添加收藏
	 */
	@Insert("INSERT INTO TBL_Collection(COLLECTIONID,COMMUNITYANSWERID,USERID,TIME) VALUES (#{0},#{1},#{2},#{3})")
	void saveCollection(String collectionid, String communityanswerId, String userid, String time);
	/*
	 * zyq_question2_删除收藏
	 */
	@Delete("DELETE FROM TBL_Collection WHERE COLLECTIONID=#{0}")
	void deleteCollection(String collectionid);
	
}
