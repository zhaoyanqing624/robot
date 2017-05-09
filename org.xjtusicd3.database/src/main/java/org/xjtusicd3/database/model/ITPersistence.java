package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_IT")
public class ITPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="USERID")
	private String USERID;
	@TableField(columnName="GOODWORK")
	private String GOODWORK;
	@TableField(columnName="WORKAGE")
	private String WORKAGE;
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getGOODWORK() {
		return GOODWORK;
	}
	public void setGOODWORK(String gOODWORK) {
		GOODWORK = gOODWORK;
	}
	public String getWORKAGE() {
		return WORKAGE;
	}
	public void setWORKAGE(String wORKAGE) {
		WORKAGE = wORKAGE;
	}
	
}
