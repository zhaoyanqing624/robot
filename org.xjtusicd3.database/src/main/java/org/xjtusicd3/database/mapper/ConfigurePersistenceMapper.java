package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ConfigurePersistence;

public interface ConfigurePersistenceMapper extends IBaseDao<ConfigurePersistence, String>
{

	/*
	 * ��ȡ���е��豸��Ϣ
	 */
	@Select("SELECT * FROM Configure")
	public List<ConfigurePersistence> getAllConfig();
}
