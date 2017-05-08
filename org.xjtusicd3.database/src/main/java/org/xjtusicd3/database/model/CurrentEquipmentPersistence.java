package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_CurrentEquipment")
public class CurrentEquipmentPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="EQUIPMENTID")
	private String EQUIPMENTID;
	@TableField(columnName="MACADDRESS")
	private String MACADDRESS;
	@TableField(columnName="EQUIPMENTMODEL")
	private String EQUIPMENTMODEL;
	@TableField(columnName="EQUIPMENTTIME")
	private String EQUIPMENTTIME;
	@TableField(columnName="IP")
	private String IP;
	@TableField(columnName="CPU")
	private String CPU;
	@TableField(columnName="RAM")
	private String RAM;
	@TableField(columnName="HARDDRIVER")
	private String HARDDRIVER;
	@TableField(columnName="STORAGE")
	private String STORAGE;
	@TableField(columnName="NETWORKCARD")
	private String NETWORKCARD;
	@TableField(columnName="NETWORKCARD2")
	private String NETWORKCARD2;
	@TableField(columnName="MOTHERBOARD")
	private String MOTHERBOARD;
	@TableField(columnName="OSNAME")
	private String OSNAME;
	@TableField(columnName="OSTYPE")
	private String OSTYPE;
	@TableField(columnName="OSVERSION")
	private String OSVERSION;
	@TableField(columnName="OSID")
	private String OSID;
	@TableField(columnName="USERID")
	private String USERID;
	@TableField(columnName="ISNOTICE")
	private int ISNOTICE;
	@TableField(columnName="GRAPHICCARD")
	private String GRAPHICCARD;
	@TableField(columnName="AUDIOCARD")
	private String AUDIOCARD;
	@TableField(columnName="TIMEREMARKS")
	private String TIMEREMARKS;
	public String getEQUIPMENTID() {
		return EQUIPMENTID;
	}
	public void setEQUIPMENTID(String eQUIPMENTID) {
		EQUIPMENTID = eQUIPMENTID;
	}
	public String getMACADDRESS() {
		return MACADDRESS;
	}
	public void setMACADDRESS(String mACADDRESS) {
		MACADDRESS = mACADDRESS;
	}
	public String getEQUIPMENTMODEL() {
		return EQUIPMENTMODEL;
	}
	public void setEQUIPMENTMODEL(String eQUIPMENTMODEL) {
		EQUIPMENTMODEL = eQUIPMENTMODEL;
	}
	public String getIP() {
		return IP;
	}
	public void setIP(String iP) {
		IP = iP;
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
	public String getNETWORKCARD() {
		return NETWORKCARD;
	}
	public void setNETWORKCARD(String nETWORKCARD) {
		NETWORKCARD = nETWORKCARD;
	}
	public String getNETWORKCARD2() {
		return NETWORKCARD2;
	}
	public void setNETWORKCARD2(String nETWORKCARD2) {
		NETWORKCARD2 = nETWORKCARD2;
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
	public String getOSVERSION() {
		return OSVERSION;
	}
	public void setOSVERSION(String oSVERSION) {
		OSVERSION = oSVERSION;
	}
	public String getOSID() {
		return OSID;
	}
	public void setOSID(String oSID) {
		OSID = oSID;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public int getISNOTICE() {
		return ISNOTICE;
	}
	public void setISNOTICE(int iSNOTICE) {
		ISNOTICE = iSNOTICE;
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
	public String getOSTYPE() {
		return OSTYPE;
	}
	public void setOSTYPE(String oSTYPE) {
		OSTYPE = oSTYPE;
	}
	public String getHARDDRIVER() {
		return HARDDRIVER;
	}
	public void setHARDDRIVER(String hARDDRIVER) {
		HARDDRIVER = hARDDRIVER;
	}
	public String getTIMEREMARKS() {
		return TIMEREMARKS;
	}
	public void setTIMEREMARKS(String tIMEREMARKS) {
		TIMEREMARKS = tIMEREMARKS;
	}
	public String getEQUIPMENTTIME() {
		return EQUIPMENTTIME;
	}
	public void setEQUIPMENTTIME(String eQUIPMENTTIME) {
		EQUIPMENTTIME = eQUIPMENTTIME;
	}
	
}
