package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_User_Equipment_History")
public class User_Equipment_HistoryPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="USEREQUIPMENTHISTORYID")
	private String USEREQUIPMENTHISTORYID;
	@TableField(columnName="USERID")
	private String USERID;
	@TableField(columnName="EQUIPMENTID")
	private String EQUIPMENTID;
	@TableField(columnName="ALLOCATIONSTARTTIME")
	private String ALLOCATIONSTARTTIME;
	@TableField(columnName="ALLOCATIONENDTIME")
	private String ALLOCATIONENDTIME;
	public String getUSEREQUIPMENTHISTORYID() {
		return USEREQUIPMENTHISTORYID;
	}
	public void setUSEREQUIPMENTHISTORYID(String uSEREQUIPMENTHISTORYID) {
		USEREQUIPMENTHISTORYID = uSEREQUIPMENTHISTORYID;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getEQUIPMENTID() {
		return EQUIPMENTID;
	}
	public void setEQUIPMENTID(String eQUIPMENTID) {
		EQUIPMENTID = eQUIPMENTID;
	}
	public String getALLOCATIONSTARTTIME() {
		return ALLOCATIONSTARTTIME;
	}
	public void setALLOCATIONSTARTTIME(String aLLOCATIONSTARTTIME) {
		ALLOCATIONSTARTTIME = aLLOCATIONSTARTTIME;
	}
	public String getALLOCATIONENDTIME() {
		return ALLOCATIONENDTIME;
	}
	public void setALLOCATIONENDTIME(String aLLOCATIONENDTIME) {
		ALLOCATIONENDTIME = aLLOCATIONENDTIME;
	}
}
