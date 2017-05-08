package org.xjtusicd3.database.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.CurrentConfigurePersistence;

public interface CurrentConfigurePersistenceMapper extends IBaseDao<CurrentConfigurePersistence, String>{
	/*
	 * zyq_personal3_添加驱动信息
	 */
	@Insert("INSERT INTO TBL_CurrentConfigure(CURRENTCONFIGUREID,EQUIPMENTID,CONFIGUREVERSION,CONFIGURENAME,CONFIGURETYPE) VALUES(#{0},#{1},#{2},#{3},#{4})")
	void save_Configure_driver(String configureid,String equipmentid,String version,String name,String type);
	/*
	 * zyq_personal3_更新驱动信息
	 */
	@Update("UPDATE TBL_CurrentConfigure SET EQUIPMENTID=#{1},CONFIGUREVERSION=#{2},CONFIGURENAME=#{3},CONFIGURETYPE=#{4} WHERE CURRENTCONFIGUREID=#{0}")
	void updateCurrentConfigure_driver(String configureid, String equipmentid, String version, String name,String type);

}
