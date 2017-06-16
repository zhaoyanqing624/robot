package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.MessagePersistence;

public interface MessagePersistenceMapper extends IBaseDao<MessagePersistence, String>{
	/*
	 * zyq_message_查看用户的消息记录
	 */
	@Select("SELECT * FROM TBL_Message WHERE GETUSERID=#{0} AND MESSAGESTATE=#{1} ORDER BY MESSAGETIME")
	List<MessagePersistence> getMessage(String userId, int state);
	/*
	 * zyq_message_查看用户列表
	 */
	@Select("SELECT POSTUSERID FROM TBL_Message WHERE GETUSERID=#{0} AND (MESSAGESTATE=#{1} || MESSAGESTATE=#{2}) GROUP BY POSTUSERID")
	List<MessagePersistence> getUserList(String userId, int state,int state2);
	@Select("SELECT * FROM TBL_Message WHERE POSTUSERID=#{0} AND GETUSERID=#{1} AND MESSAGESTATE=#{2} ORDER BY MESSAGETIME DESC")
	List<MessagePersistence> getMessageUser(String userid, String userId2, int state);
	/*
	 * zyq_message_ajax_查询私信内容
	 */
	@Select("SELECT * FROM TBL_Message WHERE POSTUSERID=#{0} AND GETUSERID=#{1} AND MESSAGESTATE=#{2} ORDER BY MESSAGETIME")
	List<MessagePersistence> getMessageContent(String postuserId, String getuserId, int state);
	@Select("SELECT * FROM TBL_Message WHERE STR_TO_DATE(MESSAGETIME,'%Y-%m-%d %H:%i:%s')>STR_TO_DATE(#{3},'%Y-%m-%d %H:%i:%s') AND ((POSTUSERID=#{0} AND GETUSERID=#{1}) OR (POSTUSERID=#{1} AND GETUSERID=#{0})) AND  MESSAGESTATE=#{2} ORDER BY MESSAGETIME")
	List<MessagePersistence> getMessageContent_time(String postuserId, String getuserId, int state,String time);
	/*
	 * zyq_message_ajax_更改私信的状态
	 */
	@Update("UPDATE TBL_Message SET TBL_Message.MESSAGESTATE=#{1} WHERE MESSAGEID=#{0}")
	void updateMessageState(String messageId, int state);
	/*
	 * zyq_message_ajax_查询私信内容_LIMIT
	 */
	@Select("SELECT * FROM TBL_Message WHERE ((POSTUSERID=#{0} AND GETUSERID=#{1}) OR (POSTUSERID=#{1} AND GETUSERID=#{0})) AND MESSAGESTATE=#{2} ORDER BY MESSAGETIME DESC")
	List<MessagePersistence> getMessageContent1(String postuserId, String getuserId, int state);
	//比较时间
	@Select("SELECT * FROM TBL_Message WHERE STR_TO_DATE(MESSAGETIME,'%Y-%m-%d %H:%i:%s')>STR_TO_DATE(#{3},'%Y-%m-%d %H:%i:%s') AND ((POSTUSERID=#{0} AND GETUSERID=#{1}) OR (POSTUSERID=#{1} AND GETUSERID=#{0})) AND MESSAGESTATE=#{2} ORDER BY MESSAGETIME DESC")
	List<MessagePersistence> getMessageContent1_time(String postuserId, String getuserId, int state,String time);
	@Select("SELECT * FROM TBL_Message WHERE ((POSTUSERID=#{0} AND GETUSERID=#{1}) OR (POSTUSERID=#{1} AND GETUSERID=#{0})) AND MESSAGESTATE=#{2} ORDER BY MESSAGETIME DESC LIMIT #{3},5")
	List<MessagePersistence> getMessageContent2(String postuserId, String getuserId, int state,int startnumber);
	//比较时间
	@Select("SELECT * FROM TBL_Message WHERE STR_TO_DATE(MESSAGETIME,'%Y-%m-%d %H:%i:%s')>STR_TO_DATE(#{4},'%Y-%m-%d %H:%i:%s')	AND ((POSTUSERID=#{0} AND GETUSERID=#{1}) OR (POSTUSERID=#{1} AND GETUSERID=#{0})) AND (MESSAGESTATE=#{5} || MESSAGESTATE=#{2}) ORDER BY MESSAGETIME DESC LIMIT #{3},5")
	List<MessagePersistence> getMessageContent2_time(String postuserId, String getuserId, int state,int startnumber,String time,int state2);
	/*
	 * zyq_message_ajax_查询更多私信内容_LIMIT_date
	 */
	@Select("SELECT * FROM TBL_Message WHERE STR_TO_DATE(MESSAGETIME,'%Y-%m-%d %H:%i:%s')<STR_TO_DATE(#{3},'%Y-%m-%d %H:%i:%s') AND ((POSTUSERID=#{0} AND GETUSERID=#{1}) OR (POSTUSERID=#{1} AND GETUSERID=#{0})) AND MESSAGESTATE=#{2} ORDER BY MESSAGETIME DESC")
	List<MessagePersistence> getMessageContent11(String postuserId, String getuserId, int state,String date);
	@Select("SELECT * FROM TBL_Message WHERE STR_TO_DATE(MESSAGETIME,'%Y-%m-%d %H:%i:%s')<STR_TO_DATE(#{3},'%Y-%m-%d %H:%i:%s') AND ((POSTUSERID=#{0} AND GETUSERID=#{1}) OR (POSTUSERID=#{1} AND GETUSERID=#{0}))  AND MESSAGESTATE=#{2} ORDER BY MESSAGETIME DESC LIMIT 5")
	List<MessagePersistence> getMessageContent21(String postuserId, String getuserId, int state,String date);

}
