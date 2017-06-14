package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ComputerPersistence;

public interface ComputerPersistenceMapper extends IBaseDao<ComputerPersistence,String>
{
	//zpz_获取用户部分信息
		@Select("SELECT * FROM TBL_Computer")
		List<ComputerPersistence> getComputer();
}
