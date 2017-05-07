package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.CurrentEquipmentPersistence;

public interface CurrentEquipmentPersistenceMapper extends IBaseDao<CurrentEquipmentPersistence, String>{
	/*
	 * zyq_personal3_查询列表
	 */
	@Select("SELECT * FROM TBL_CurrentEquipment WHERE MACADDRESS=#{0}")
	List<CurrentEquipmentPersistence> currentEquipment(String macaddress);

}
