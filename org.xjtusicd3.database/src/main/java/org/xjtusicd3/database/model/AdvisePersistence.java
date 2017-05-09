package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;
@Table(tablename="TBL_Advise")
public class AdvisePersistence
{
	@TableKey(strategy = Strategy.NORMAL)
	@TableField(columnName="ADVISEID")
	private String ADVISEID;
	@TableField(columnName="EMAIL")
	private String EMAIL;
	@TableField(columnName="NAME")
	private String NAME;
	@TableField(columnName="PHONE")
	private String PHONE;
	@TableField(columnName="TEXT")
	private String TEXT;
	@TableField(columnName="ANNEX")
	private String ANNEX;
	@TableField(columnName="USERID")
	private String USERID;
	@TableField(columnName="STATES")
	private int STATES;
	@TableField(columnName="ADVISETIME")
	private String ADVISETIME;
	public String getADVISEID() {
		return ADVISEID;
	}
	public void setADVISEID(String aDVISEID) {
		ADVISEID = aDVISEID;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public String getTEXT() {
		return TEXT;
	}
	public void setTEXT(String tEXT) {
		TEXT = tEXT;
	}
	public String getANNEX() {
		return ANNEX;
	}
	public void setANNEX(String aNNEX) {
		ANNEX = aNNEX;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public int getSTATES() {
		return STATES;
	}
	public void setSTATES(int sTATES) {
		STATES = sTATES;
	}
	public String getADVISETIME() {
		return ADVISETIME;
	}
	public void setADVISETIME(String aDVISETIME) {
		ADVISETIME = aDVISETIME;
	}
	
	 
}
