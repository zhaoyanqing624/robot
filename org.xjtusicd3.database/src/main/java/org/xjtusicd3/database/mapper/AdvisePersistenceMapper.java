package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao; 
import org.xjtusicd3.database.model.AdvisePersistence;

public interface  AdvisePersistenceMapper extends IBaseDao<AdvisePersistence,String>
{
	/*
	 * zpz_get information of TBL_Advise
	 */
	@Select("SELECT * TBL_Advise")
	public List<AdvisePersistence> getAdvise();
}
