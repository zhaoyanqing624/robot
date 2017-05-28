package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Agree")
public class AgreePersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="AGREEID")
	private String AGREEID;
	@TableField(columnName="COMMUNITYANSWERID")
	private String COMMUNITYANSWERID;
	@TableField(columnName="USERID")
	private String USERID ;
	@TableField(columnName="TOUSERID")
	private String TOUSERID ;
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
	public String getTOUSERID() {
		return TOUSERID;
	}
	public void setTOUSERID(String tOUSERID) {
		TOUSERID = tOUSERID;
	}
	public String getAGREEID() {
		return AGREEID;
	}
	public void setAGREEID(String aGREEID) {
		AGREEID = aGREEID;
	}
	public String getCOMMUNITYANSWERID() {
		return COMMUNITYANSWERID;
	}
	public void setCOMMUNITYANSWERID(String cOMMUNITYANSWERID) {
		COMMUNITYANSWERID = cOMMUNITYANSWERID;
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

