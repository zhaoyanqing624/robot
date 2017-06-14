package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ServerPersistence;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface ServerPersistenceMapper extends IBaseDao<ServerPersistence, String>{

	//zpz_获取用户所有信息
		@Select("SELECT * FROM TBL_Server")
		List<ServerPersistence> getServer();
}
