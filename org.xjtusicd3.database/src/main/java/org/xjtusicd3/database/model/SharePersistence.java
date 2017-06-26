package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Share")
public class SharePersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="SHAREID")
	private String SHAREID;
	@TableField(columnName="USERID")
	private String USERID;
	@TableField(columnName="TIME")
	private String TIME;
	@TableField(columnName="FAQQUESTIONID")
	private String FAQQUESTIONID;
	@TableField(columnName="COMMUNITYQUESTIONID")
	private String COMMUNITYQUESTIONID;
	public String getSHAREID() {
		return SHAREID;
	}
	public void setSHAREID(String sHAREID) {
		SHAREID = sHAREID;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	public String getFAQQUESTIONID() {
		return FAQQUESTIONID;
	}
	public void setFAQQUESTIONID(String fAQQUESTIONID) {
		FAQQUESTIONID = fAQQUESTIONID;
	}
	public String getCOMMUNITYQUESTIONID() {
		return COMMUNITYQUESTIONID;
	}
	public void setCOMMUNITYQUESTIONID(String cOMMUNITYQUESTIONID) {
		COMMUNITYQUESTIONID = cOMMUNITYQUESTIONID;
	}
}
