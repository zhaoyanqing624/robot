package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.EquipmentPersistence;

public interface EquipmentPersistenceMapper extends IBaseDao<EquipmentPersistence, String>{
	/**
	 * author:zhaoyanqing
	 * date:2017年9月9日 21:08:18
	 * abstract:根据MAC地址查看是否存在配置表中
	 */
	@Select("SELECT * FROM TBL_Equipment WHERE MACADDRESS=#{0}")
	List<EquipmentPersistence> getEquipmentList(String macaddress);
}
