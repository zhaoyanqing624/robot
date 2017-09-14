package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao; 
import org.xjtusicd3.database.model.LogPersistence;

public interface LogPersistenceMapper extends IBaseDao<LogPersistence,String>
{
	/*
	 * zpz_get information of TBL_Log
	 */
	@Select("SELECT * FROM TBL_Log")
	public List<LogPersistence> getLog();
	
	
	/*
	 * record log info
	 */
	@Insert("INSERT INTO TBL_Log(TBL_Log.LOGID,TBL_Log.USERID,TBL_Log.LOGMETHOD,TBL_Log.LOGTIME) VALUES (#{0},#{1},#{2},#{3})")
	public void log_info(String logId,String userId,String logMethod,String logTime);

	/*
	 * 前台添加日志_2017年9月14日22:21:59
	 */
	@Insert("INSERT INTO TBL_Log(TBL_Log.LOGID,TBL_Log.USERID,TBL_Log.LOGMETHOD,TBL_Log.LOGTIME) VALUES (#{0},#{1},#{2},#{3})")
	public List<LogPersistence> addLog(String logId, String userId, String faqPath, String logTime);
}
