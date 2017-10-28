package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.ConfigurePersistence;

public interface ConfigurePersistenceMapper extends IBaseDao<ConfigurePersistence, String>{

	/*
	 * zpz_get all of information of configure
	 */
	@Select("SELECT * FROM TBL_Configure LIMIT 200")
	public List<ConfigurePersistence> getAllConfig();
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
	
	/**
	 * author:
	 * abstract:获取配置信息
	 * data:2017年10月12日18:03:19
	 * @param startNumber 
	 */
	@Select("SELECT * FROM TBL_Configure ORDER BY CONFIGURETIME DESC LIMIT #{0},100")
	public List<ConfigurePersistence> getCfgs(int startNumber);

	
}
