package org.xjtusicd3.database.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.SoftPersistence;

public interface SoftPersistenceMapper extends IBaseDao<SoftPersistence, String>{
	/*
	 * zyq_spider_配置信息更新
	 */
	@Update("UPDATE TBL_Configure SET LOGO=#{1},INTRODUCTION=#{2},DESCRIPTION=#{3},VERSIONTYPE=#{4},VERSION=#{5},NEWVERSIONINTRODUCTION=#{6},NEWVERSIONDESCRIPTION=#{7},SOFTTYPE=#{8},SPAREURL=#{9},OS=#{10},WEBSITE=#{11},SCORE=#{12} WHERE CONFIGUREID=#{0}")
	List<SoftPersistence> update_Soft(String softId,String logo,String introduce,String descrip,String versiontype,String version,String new_introduce,String new_descrip,String softtype,String spareurl,String os,String website,String score);

}
