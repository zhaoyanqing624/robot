package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ConfigurePersistence;

public interface ConfigurePersistenceMapper extends IBaseDao<ConfigurePersistence, String>{

	/*
	 * zpz_get part of the information of configure
	 */
	@Select("SELECT CONFIGURENAME,CONFIGURETYPE,CONFIGUREPRODUCER FROM TBL_Configure LIMIT 200")
	public List<ConfigurePersistence> getPartConfig();
	/*
	 * zyq_spider_按名字查看设备
	 */
	@Select("SELECT * FROM TBL_Configure WHERE CONFIGURENAME=#{0}")
	public List<ConfigurePersistence> getConfigure(String configurename);
	/*
	 * zyq_spider_更新软件
	 */
	@Update("UPDATE TBL_Configure SET FILESIZE=#{1},URL=#{2},DOWNLOADTIMES=#{3},PRODUCER=#{4},CONFIGURETIME=#{5} WHERE CONFIGURENAME=#{0}")
	public List<ConfigurePersistence> update_Configure(String configurename,String filesize,String url,String downloadtimes,String producer,String configuretime);

	
}
