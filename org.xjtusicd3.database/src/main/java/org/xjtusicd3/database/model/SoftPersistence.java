package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;
@Table(tablename="TBL_Soft")
public class SoftPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="CONFIGUREID")
	private String CONFIGUREID;
	@TableField(columnName="SOFTID")
	private String SOFTID;
	@TableField(columnName="SCORE")
	private String SCORE;
	@TableField(columnName="LOGO")
	private String LOGO;
	@TableField(columnName="WEBSITE")
	private String WEBSITE;
	@TableField(columnName="INTRODUCTION")
	private String INTRODUCTION;
	@TableField(columnName="DESCRIPTION")
	private String DESCRIPTION;
	@TableField(columnName="VERSIONTYPE")
	private String VERSIONTYPE;
	@TableField(columnName="NEWVERSIONINTRODUCTION")
	private String NEWVERSIONINTRODUCTION;
	@TableField(columnName="NEWVERSIONDESCRIPTION")
	private String NEWVERSIONDESCRIPTION;
	@TableField(columnName="VERSION")
	private String VERSION;
	@TableField(columnName="SOFTTYPE")
	private String SOFTTYPE;
	@TableField(columnName="SPAREURL")
	private String SPAREURL;
	@TableField(columnName="OS")
	private String OS;
	public String getCONFIGUREID() {
		return CONFIGUREID;
	}
	public void setCONFIGUREID(String cONFIGUREID) {
		CONFIGUREID = cONFIGUREID;
	}
	public String getSOFTID() {
		return SOFTID;
	}
	public void setSOFTID(String sOFTID) {
		SOFTID = sOFTID;
	}
	public String getSCORE() {
		return SCORE;
	}
	public void setSCORE(String sCORE) {
		SCORE = sCORE;
	}
	public String getLOGO() {
		return LOGO;
	}
	public void setLOGO(String lOGO) {
		LOGO = lOGO;
	}
	public String getWEBSITE() {
		return WEBSITE;
	}
	public void setWEBSITE(String wEBSITE) {
		WEBSITE = wEBSITE;
	}
	public String getINTRODUCTION() {
		return INTRODUCTION;
	}
	public void setINTRODUCTION(String iNTRODUCTION) {
		INTRODUCTION = iNTRODUCTION;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public String getVERSIONTYPE() {
		return VERSIONTYPE;
	}
	public void setVERSIONTYPE(String vERSIONTYPE) {
		VERSIONTYPE = vERSIONTYPE;
	}
	public String getNEWVERSIONINTRODUCTION() {
		return NEWVERSIONINTRODUCTION;
	}
	public void setNEWVERSIONINTRODUCTION(String nEWVERSIONINTRODUCTION) {
		NEWVERSIONINTRODUCTION = nEWVERSIONINTRODUCTION;
	}
	public String getNEWVERSIONDESCRIPTION() {
		return NEWVERSIONDESCRIPTION;
	}
	public void setNEWVERSIONDESCRIPTION(String nEWVERSIONDESCRIPTION) {
		NEWVERSIONDESCRIPTION = nEWVERSIONDESCRIPTION;
	}
	public String getVERSION() {
		return VERSION;
	}
	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}
	public String getSOFTTYPE() {
		return SOFTTYPE;
	}
	public void setSOFTTYPE(String sOFTTYPE) {
		SOFTTYPE = sOFTTYPE;
	}
	public String getSPAREURL() {
		return SPAREURL;
	}
	public void setSPAREURL(String sPAREURL) {
		SPAREURL = sPAREURL;
	}
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}
	
}	
