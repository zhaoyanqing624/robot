package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Message")
public class MessagePersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="MESSAGEID")
	private String MESSAGEID;
	@TableField(columnName="MESSAGECONTENT")
	private String MESSAGECONTENT;
	@TableField(columnName="MESSAGETIME")
	private String MESSAGETIME;
	@TableField(columnName="MESSAGESTATE")
	private int MESSAGESTATE;
	@TableField(columnName="POSTUSERID")
	private String POSTUSERID;
	@TableField(columnName="GETUSERID")
	private String GETUSERID;
	public String getMESSAGEID() {
		return MESSAGEID;
	}
	public void setMESSAGEID(String mESSAGEID) {
		MESSAGEID = mESSAGEID;
	}
	public String getMESSAGECONTENT() {
		return MESSAGECONTENT;
	}
	public void setMESSAGECONTENT(String mESSAGECONTENT) {
		MESSAGECONTENT = mESSAGECONTENT;
	}
	public String getMESSAGETIME() {
		return MESSAGETIME;
	}
	public void setMESSAGETIME(String mESSAGETIME) {
		MESSAGETIME = mESSAGETIME;
	}
	public int getMESSAGESTATE() {
		return MESSAGESTATE;
	}
	public void setMESSAGESTATE(int mESSAGESTATE) {
		MESSAGESTATE = mESSAGESTATE;
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
	
	
}
