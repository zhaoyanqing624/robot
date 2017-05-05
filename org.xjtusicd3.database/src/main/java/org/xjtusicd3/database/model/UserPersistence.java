package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_User")
public class UserPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="USERID")
	private String USERID;
	@TableField(columnName="USERNAME")
	private String USERNAME;
	@TableField(columnName="USERPASSWORD")
	private String USERPASSWORD;
	@TableField(columnName="USEREMAIL")
	private String USEREMAIL;
	@TableField(columnName="GENDER")
	private String GENDER;
	@TableField(columnName="USERBIRTHDAY")
	private String USERBIRTHDAY;
	@TableField(columnName="USERADDRESS")
	private String USERADDRESS;
	@TableField(columnName="USERSIGNATURE")
	private String USERSIGNATURE;
	@TableField(columnName="AVATAR")
	private String AVATAR;
	@TableField(columnName="ROLEID")
	private String ROLEID;
	@TableField(columnName="USERSTATE")
	private int USERSTATE;
	@TableField(columnName="VERIFICATIONCODE")
	private String VERIFICATIONCODE;
	@TableField(columnName="TIMEREMARKS")
	private String TIMEREMARKS;
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getUSERNAME() {
		return USERNAME;
	}
	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}
	public String getUSERPASSWORD() {
		return USERPASSWORD;
	}
	public void setUSERPASSWORD(String uSERPASSWORD) {
		USERPASSWORD = uSERPASSWORD;
	}
	public String getUSEREMAIL() {
		return USEREMAIL;
	}
	public void setUSEREMAIL(String uSEREMAIL) {
		USEREMAIL = uSEREMAIL;
	}
	public String getGENDER() {
		return GENDER;
	}
	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}
	public String getUSERBIRTHDAY() {
		return USERBIRTHDAY;
	}
	public void setUSERBIRTHDAY(String uSERBIRTHDAY) {
		USERBIRTHDAY = uSERBIRTHDAY;
	}
	public String getUSERADDRESS() {
		return USERADDRESS;
	}
	public void setUSERADDRESS(String uSERADDRESS) {
		USERADDRESS = uSERADDRESS;
	}
	public String getUSERSIGNATURE() {
		return USERSIGNATURE;
	}
	public void setUSERSIGNATURE(String uSERSIGNATURE) {
		USERSIGNATURE = uSERSIGNATURE;
	}
	public String getAVATAR() {
		return AVATAR;
	}
	public void setAVATAR(String aVATAR) {
		AVATAR = aVATAR;
	}
	public String getROLEID() {
		return ROLEID;
	}
	public void setROLEID(String rOLEID) {
		ROLEID = rOLEID;
	}
	public int getUSERSTATE() {
		return USERSTATE;
	}
	public void setUSERSTATE(int uSERSTATE) {
		USERSTATE = uSERSTATE;
	}
	public String getVERIFICATIONCODE() {
		return VERIFICATIONCODE;
	}
	public void setVERIFICATIONCODE(String vERIFICATIONCODE) {
		VERIFICATIONCODE = vERIFICATIONCODE;
	}
	public String getTIMEREMARKS() {
		return TIMEREMARKS;
	}
	public void setTIMEREMARKS(String tIMEREMARKS) {
		TIMEREMARKS = tIMEREMARKS;
	}
	
	
	
}	
