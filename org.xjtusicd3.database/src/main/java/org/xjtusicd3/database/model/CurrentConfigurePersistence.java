package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_CurrentConfigure")
public class CurrentConfigurePersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="CURRENTCONFIGUREID")
	private String CURRENTCONFIGUREID;
	@TableField(columnName="EQUIPMENTID")
	private String EQUIPMENTID;
	@TableField(columnName="CONFIGUREVERSION")
	private String CONFIGUREVERSION;
	@TableField(columnName="CONFIGURENAME")
	private String CONFIGURENAME;
	@TableField(columnName="CONFIGURETYPE")
	private String CONFIGURETYPE;
	public String getCURRENTCONFIGUREID() {
		return CURRENTCONFIGUREID;
	}
	public void setCURRENTCONFIGUREID(String cURRENTCONFIGUREID) {
		CURRENTCONFIGUREID = cURRENTCONFIGUREID;
	}
	public String getEQUIPMENTID() {
		return EQUIPMENTID;
	}
	public void setEQUIPMENTID(String eQUIPMENTID) {
		EQUIPMENTID = eQUIPMENTID;
	}
	public String getCONFIGUREVERSION() {
		return CONFIGUREVERSION;
	}
	public void setCONFIGUREVERSION(String cONFIGUREVERSION) {
		CONFIGUREVERSION = cONFIGUREVERSION;
	}
	public String getCONFIGURENAME() {
		return CONFIGURENAME;
	}
	public void setCONFIGURENAME(String cONFIGURENAME) {
		CONFIGURENAME = cONFIGURENAME;
	}
	public String getCONFIGURETYPE() {
		return CONFIGURETYPE;
	}
	public void setCONFIGURETYPE(String cONFIGURETYPE) {
		CONFIGURETYPE = cONFIGURETYPE;
	}
	
	
}
