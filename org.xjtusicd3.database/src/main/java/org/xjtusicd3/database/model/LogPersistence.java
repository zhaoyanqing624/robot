package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Log")
public class LogPersistence
{
	@TableKey(strategy = Strategy.NORMAL)
	@TableField(columnName="LOGID")
	private String LogId;
	@TableField(columnName="USERID")
	private String UserId;
	@TableField(columnName="LOGMETHOD")
	private String LogMethod;
	@TableField(columnName="LOGTIME")
	private String LogTime;
	@TableField(columnName="OPERATION")
	private String Operation;
	@TableField(columnName="URL")
	private String Url;
	@TableField(columnName="IP")
	private String Ip;	
	public String getLogId()
	{
		return LogId;
	}
	public void setLogId(String logId)
	{
		LogId = logId;
	}
	public String getUserId()
	{
		return UserId;
	}
	public void setUserId(String userId)
	{
		UserId = userId;
	}
	public String getLogMethod()
	{
		return LogMethod;
	}
	public void setLogMethod(String logMethod)
	{
		LogMethod = logMethod;
	}
	public String getLogTime()
	{
		return LogTime;
	}
	public void setLogTime(String logTime)
	{
		LogTime = logTime;
	}
	public String getOperation() {
		return Operation;
	}
	public void setOperation(String operation) {
		Operation = operation;
	}
	
	public String getUrl() {
		return Url;
	}
	public void setUrl(String urlPath) {
		Url = urlPath;
	}
	public String getIp() {
		return Ip;
	}
	public void setIp(String ip) {
		Ip = ip;
	}
	
	
}
