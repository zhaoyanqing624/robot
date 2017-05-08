package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.User_Equipment_HistoryPersistence;

public interface User_Equipment_HistoryPersistenceMapper extends IBaseDao<User_Equipment_HistoryPersistence, String>{
	/*
	 * zyq_personal_查看此表信息
	 */
	@Select("SELECT * FROM TBL_User_Equipment_History WHERE EQUIPMENTID=#{0}")
	List<User_Equipment_HistoryPersistence> User_Equipment_HistoryList(String equipmentId);

}
