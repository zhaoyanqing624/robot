package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.MessageHistoryPersistence;

public interface MessageHistoryPersistenceMapper extends IBaseDao<MessageHistoryPersistence, String >{
	/*
	 * zyq_message_查看私信历史列表
	 */
	@Select("SELECT * FROM TBL_MessageHistory WHERE POSTUSERID=#{0} AND GETUSERID=#{1}")
	List<MessageHistoryPersistence> getMessageHistoryList(String postuserId, String getuserId);
	/*
	 * zyq_message_更改私信的时间
	 */
	@Update("UPDATE TBL_MessageHistory SET TIMEMARK=#{1} WHERE MESSAGEHISTORYID=#{0}")
	void updateMessageHistory(String messagehistoryid, String time);

}
