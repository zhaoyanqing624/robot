package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.EquipmentPersistence;

public interface EquipmentPersistenceMapper extends IBaseDao<EquipmentPersistence, String>{
	/*
	 * zyq_personal3_查询equipment表是否存在
	 */
	@Select("SELECT * FROM TBL_Equipment WHERE MACADDRESS=#{0}")
	List<EquipmentPersistence> getEquipmentList(String macaddress);
}
