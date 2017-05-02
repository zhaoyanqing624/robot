package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_ConfigureHistory")
public class ConfigureHistoryPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="CONFIGUREHISTORYID")
	private String CONFIGUREHISTORYID;
	@TableField(columnName="UPDATETIME")
	private String UPDATETIME;
	@TableField(columnName="VERSION")
	private String VERSION;
	@TableField(columnName="CONFIGUREID")
	private String CONFIGUREID;
	@TableField(columnName="REMARKS")
	private String REMARKS;
	@TableField(columnName="URL")
	private String URL;
	public String getCONFIGUREHISTORYID() {
		return CONFIGUREHISTORYID;
	}
	public void setCONFIGUREHISTORYID(String cONFIGUREHISTORYID) {
		CONFIGUREHISTORYID = cONFIGUREHISTORYID;
	}
	public String getUPDATETIME() {
		return UPDATETIME;
	}
	public void setUPDATETIME(String uPDATETIME) {
		UPDATETIME = uPDATETIME;
	}
	public String getVERSION() {
		return VERSION;
	}
	public void setVERSION(String vERSION) {
		VERSION = vERSION;
	}
	public String getCONFIGUREID() {
		return CONFIGUREID;
	}
	public void setCONFIGUREID(String cONFIGUREID) {
		CONFIGUREID = cONFIGUREID;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
}
