package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Collection")
public class CollectionPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="COLLECTIONID")
	private String COLLECTIONID;
	@TableField(columnName="COMMUNITYANSWERID")
	private String COMMUNITYANSWERID;
	@TableField(columnName="FAQQUESTIONID")
	private String FAQQUESTIONID;
	@TableField(columnName="USERID")
	private String USERID;
	@TableField(columnName="TIME")
	private String TIME;
	@TableField(columnName="ISNOTICE")
	private int ISNOTICE;
	
	public int getISNOTICE() {
		return ISNOTICE;
	}
	public void setISNOTICE(int iSNOTICE) {
		ISNOTICE = iSNOTICE;
	}
	public String getCOLLECTIONID() {
		return COLLECTIONID;
	}
	public void setCOLLECTIONID(String cOLLECTIONID) {
		COLLECTIONID = cOLLECTIONID;
	}
	public String getCOMMUNITYANSWERID() {
		return COMMUNITYANSWERID;
	}
	public void setCOMMUNITYANSWERID(String cOMMUNITYANSWERID) {
		COMMUNITYANSWERID = cOMMUNITYANSWERID;
	}
	public String getFAQQUESTIONID() {
		return FAQQUESTIONID;
	}
	public void setFAQQUESTIONID(String fAQQUESTIONID) {
		FAQQUESTIONID = fAQQUESTIONID;
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
	
}
