package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;
@Table(tablename="TBL_Driver")
public class DriverPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="CONFIGUREID")
	private String CONFIGUREID;
	@TableField(columnName="OS")
	private String OS;
	@TableField(columnName="DRIVERTYPE")
	private String DRIVERTYPE;
	@TableField(columnName="FITNESS")
	private String FITNESS;
	@TableField(columnName="DRIVERINTRODUCTION")
	private String DRIVERINTRODUCTION;
	public String getCONFIGUREID() {
		return CONFIGUREID;
	}
	public void setCONFIGUREID(String cONFIGUREID) {
		CONFIGUREID = cONFIGUREID;
	}
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}
	public String getDRIVERTYPE() {
		return DRIVERTYPE;
	}
	public void setDRIVERTYPE(String dRIVERTYPE) {
		DRIVERTYPE = dRIVERTYPE;
	}
	public String getFITNESS() {
		return FITNESS;
	}
	public void setFITNESS(String fITNESS) {
		FITNESS = fITNESS;
	}
	public String getDRIVERINTRODUCTION() {
		return DRIVERINTRODUCTION;
	}
	public void setDRIVERINTRODUCTION(String dRIVERINTRODUCTION) {
		DRIVERINTRODUCTION = dRIVERINTRODUCTION;
	}

	
}
