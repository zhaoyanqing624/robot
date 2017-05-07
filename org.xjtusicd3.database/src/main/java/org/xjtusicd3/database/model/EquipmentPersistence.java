package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Equipment")
public class EquipmentPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="EQUIPMENTID")
	private String EQUIPMENTID;
	@TableField(columnName="MACADDRESS")
	private String MACADDRESS;
	@TableField(columnName="EQUIPMENTMODEL")
	private String EQUIPMENTMODEL;
	@TableField(columnName="BUYTIME")
	private String BUYTIME;
	@TableField(columnName="CPU")
	private String CPU;
	@TableField(columnName="RAM")
	private String RAM;
	@TableField(columnName="STORAGE")
	private String STORAGE;
	@TableField(columnName="IP")
	private String IP;
	@TableField(columnName="EQUIPMENTIMAGE")
	private String EQUIPMENTIMAGE;
	@TableField(columnName="STATE")
	private int STATE;
	public String getEQUIPMENTID() {
		return EQUIPMENTID;
	}
	public void setEQUIPMENTID(String eQUIPMENTID) {
		EQUIPMENTID = eQUIPMENTID;
	}
	public String getEQUIPMENTMODEL() {
		return EQUIPMENTMODEL;
	}
	public void setEQUIPMENTMODEL(String eQUIPMENTMODEL) {
		EQUIPMENTMODEL = eQUIPMENTMODEL;
	}
	public String getBUYTIME() {
		return BUYTIME;
	}
	public void setBUYTIME(String bUYTIME) {
		BUYTIME = bUYTIME;
	}
	public String getCPU() {
		return CPU;
	}
	public void setCPU(String cPU) {
		CPU = cPU;
	}
	public String getRAM() {
		return RAM;
	}
	public void setRAM(String rAM) {
		RAM = rAM;
	}
	public String getSTORAGE() {
		return STORAGE;
	}
	public void setSTORAGE(String sTORAGE) {
		STORAGE = sTORAGE;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
	}
	public String getEQUIPMENTIMAGE() {
		return EQUIPMENTIMAGE;
	}
	public void setEQUIPMENTIMAGE(String eQUIPMENTIMAGE) {
		EQUIPMENTIMAGE = eQUIPMENTIMAGE;
	}
	public int getSTATE() {
		return STATE;
	}
	public void setSTATE(int sTATE) {
		STATE = sTATE;
	}
	public String getMACADDRESS() {
		return MACADDRESS;
	}
	public void setMACADDRESS(String mACADDRESS) {
		MACADDRESS = mACADDRESS;
	}
	
	
}
