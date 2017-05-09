package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ITPersistence;

public interface ITPersistenceMapper extends IBaseDao<ITPersistence, String>{
	/*
	 * zyq_personal2_查看运维人员
	 */
	@Select("SELECT * FROM TBL_IT WHERE USERID=#{0}")
	List<ITPersistence> IT(String userid);
	
}
