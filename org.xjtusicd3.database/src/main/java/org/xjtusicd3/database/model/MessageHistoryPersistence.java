package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_MessageHistory")
public class MessageHistoryPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="MESSAGEHISTORYID")
	private String MESSAGEHISTORYID;
	@TableField(columnName="POSTUSERID")
	private String POSTUSERID;
	@TableField(columnName="GETUSERID")
	private String GETUSERID;
	@TableField(columnName="TIMEMARK")
	private String TIMEMARK;
	public String getMESSAGEHISTORYID() {
		return MESSAGEHISTORYID;
	}
	public void setMESSAGEHISTORYID(String mESSAGEHISTORYID) {
		MESSAGEHISTORYID = mESSAGEHISTORYID;
	}
	public String getPOSTUSERID() {
		return POSTUSERID;
	}
	public void setPOSTUSERID(String pOSTUSERID) {
		POSTUSERID = pOSTUSERID;
	}
	public String getGETUSERID() {
		return GETUSERID;
	}
	public void setGETUSERID(String gETUSERID) {
		GETUSERID = gETUSERID;
	}
	public String getTIMEMARK() {
		return TIMEMARK;
	}
	public void setTIMEMARK(String tIMEMARK) {
		TIMEMARK = tIMEMARK;
	}
}
