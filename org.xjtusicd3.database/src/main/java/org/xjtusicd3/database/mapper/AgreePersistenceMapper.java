package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.AgreePersistence;

public interface AgreePersistenceMapper extends IBaseDao<AgreePersistence, String>{
	/*
	 * zyq_question2_对于答案点赞
	 */
	@Insert("INSERT INTO TBL_Agree(AGREEID,COMMUNITYANSWERID,USERID,TOUSERID,TIME) VALUES (#{0},#{1},#{2},#{3},#{4})")
	void saveAgree(String agreeid, String communityanswerId, String userid,String touserid,String time);
	/*
	 * zyq_question2_查看点赞表
	 */
	@Select("SELECT * FROM TBL_Agree WHERE COMMUNITYANSWERID=#{0} AND USERID=#{1}")
	List<AgreePersistence> getAgree(String communityanswerId, String userid);
	@Select("SELECT * FROM TBL_Agree WHERE COMMUNITYANSWERID=#{0}")
	List<AgreePersistence> getAgree_id(String communityanswerId);
	/*
	 * zyq_question2_删除点赞
	 */
	@Delete("DELETE FROM TBL_Agree WHERE AGREEID=#{0}")
	void deleteAgree(String agreeid);
	/*
	 * zyq_question_查看用户点赞
	 */
	@Select("SELECT * FROM TBL_Agree WHERE USERID=#{0}")
	List<AgreePersistence> getAgreebyUserId(String userid);
	
}
