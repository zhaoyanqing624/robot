package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Computer")
public class ComputerPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="EQUIPMENTID")
	private String EQUIPMENTID;
	@TableField(columnName="GRAPHICCARD")
	private String GRAPHICCARD;
	@TableField(columnName="AUDIOCARD")
	private String AUDIOCARD;
	@TableField(columnName="NETWORKCARD")
	private String NETWORKCARD;
	@TableField(columnName="MOTHERBOARD")
	private String MOTHERBOARD;
	@TableField(columnName="OSNAME")
	private String OSNAME;
	@TableField(columnName="OSID")
	private String OSID;
	public String getEQUIPMENTID() {
		return EQUIPMENTID;
	}
	public void setEQUIPMENTID(String eQUIPMENTID) {
		EQUIPMENTID = eQUIPMENTID;
	}
	public String getGRAPHICCARD() {
		return GRAPHICCARD;
	}
	public void setGRAPHICCARD(String gRAPHICCARD) {
		GRAPHICCARD = gRAPHICCARD;
	}
	public String getAUDIOCARD() {
		return AUDIOCARD;
	}
	public void setAUDIOCARD(String aUDIOCARD) {
		AUDIOCARD = aUDIOCARD;
	}
	public String getNETWORKCARD() {
		return NETWORKCARD;
	}
	public void setNETWORKCARD(String nETWORKCARD) {
		NETWORKCARD = nETWORKCARD;
	}
	public String getMOTHERBOARD() {
		return MOTHERBOARD;
	}
	public void setMOTHERBOARD(String mOTHERBOARD) {
		MOTHERBOARD = mOTHERBOARD;
	}
	public String getOSNAME() {
		return OSNAME;
	}
	public void setOSNAME(String oSNAME) {
		OSNAME = oSNAME;
	}
	public String getOSID() {
		return OSID;
	}
	public void setOSID(String oSID) {
		OSID = oSID;
	}
	
}
