package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Configure")
public class ConfigurePersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="CONFIGUREID")
	private String CONFIGUREID;
	@TableField(columnName="CONFIGURENAME")
	private String CONFIGURENAME;
	@TableField(columnName="CONFIGURETYPE")
	private String CONFIGURETYPE;
	@TableField(columnName="PRODUCER")
	private String PRODUCER;
	@TableField(columnName="DOWNLOADTIMES")
	private String DOWNLOADTIMES;
	@TableField(columnName="URL")
	private String URL;
	@TableField(columnName="FILESIZE")
	private String FILESIZE;
	public String getCONFIGUREID() {
		return CONFIGUREID;
	}
	public void setCONFIGUREID(String cONFIGUREID) {
		CONFIGUREID = cONFIGUREID;
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
	public String getPRODUCER() {
		return PRODUCER;
	}
	public void setPRODUCER(String pRODUCER) {
		PRODUCER = pRODUCER;
	}
	public String getDOWNLOADTIMES() {
		return DOWNLOADTIMES;
	}
	public void setDOWNLOADTIMES(String dOWNLOADTIMES) {
		DOWNLOADTIMES = dOWNLOADTIMES;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getFILESIZE() {
		return FILESIZE;
	}
	public void setFILESIZE(String fILESIZE) {
		FILESIZE = fILESIZE;
	}
	
}
