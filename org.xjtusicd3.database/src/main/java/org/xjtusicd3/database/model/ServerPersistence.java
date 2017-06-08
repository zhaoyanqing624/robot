package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Server")
public class ServerPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="EQUIPMENTID")
	private String EQUIPMENTID;
	@TableField(columnName="OSVERSION")
	private String OSVERSION;
	@TableField(columnName="COMPUTERNAME")
	private String COMPUTERNAME;
	@TableField(columnName="PCI")
	private String PCI;
	@TableField(columnName="USB")
	private String USB;
	@TableField(columnName="PATH")
	private String PATH;
	@TableField(columnName="RAM_EXCHANGEAREAUS")
	private String RAM_EXCHANGEAREAUS;
	@TableField(columnName="PARTATIONUSE")
	private String PARTATIONUSE;
	@TableField(columnName="IDLERAM")
	private String IDLERAM;
	@TableField(columnName="OS_TIME_USERNUM_LOAD")
	private String OS_TIME_USERNUM_LOAD;
	@TableField(columnName="OSLOAD")
	private String OSLOAD;
	public String getEQUIPMENTID() {
		return EQUIPMENTID;
	}
	public void setEQUIPMENTID(String eQUIPMENTID) {
		EQUIPMENTID = eQUIPMENTID;
	}
	public String getOSVERSION() {
		return OSVERSION;
	}
	public void setOSVERSION(String oSVERSION) {
		OSVERSION = oSVERSION;
	}
	public String getCOMPUTERNAME() {
		return COMPUTERNAME;
	}
	public void setCOMPUTERNAME(String cOMPUTERNAME) {
		COMPUTERNAME = cOMPUTERNAME;
	}
	public String getPCI() {
		return PCI;
	}
	public void setPCI(String pCI) {
		PCI = pCI;
	}
	public String getUSB() {
		return USB;
	}
	public void setUSB(String uSB) {
		USB = uSB;
	}
	public String getPATH() {
		return PATH;
	}
	public void setPATH(String pATH) {
		PATH = pATH;
	}
	public String getRAM_EXCHANGEAREAUS() {
		return RAM_EXCHANGEAREAUS;
	}
	public void setRAM_EXCHANGEAREAUS(String rAM_EXCHANGEAREAUS) {
		RAM_EXCHANGEAREAUS = rAM_EXCHANGEAREAUS;
	}
	public String getPARTATIONUSE() {
		return PARTATIONUSE;
	}
	public void setPARTATIONUSE(String pARTATIONUSE) {
		PARTATIONUSE = pARTATIONUSE;
	}
	public String getIDLERAM() {
		return IDLERAM;
	}
	public void setIDLERAM(String iDLERAM) {
		IDLERAM = iDLERAM;
	}
	public String getOS_TIME_USERNUM_LOAD() {
		return OS_TIME_USERNUM_LOAD;
	}
	public void setOS_TIME_USERNUM_LOAD(String oS_TIME_USERNUM_LOAD) {
		OS_TIME_USERNUM_LOAD = oS_TIME_USERNUM_LOAD;
	}
	public String getOSLOAD() {
		return OSLOAD;
	}
	public void setOSLOAD(String oSLOAD) {
		OSLOAD = oSLOAD;
	}
	public String getFIREWALL() {
		return FIREWALL;
	}
	public void setFIREWALL(String fIREWALL) {
		FIREWALL = fIREWALL;
	}
	public String getROUTINGTABLE() {
		return ROUTINGTABLE;
	}
	public void setROUTINGTABLE(String rOUTINGTABLE) {
		ROUTINGTABLE = rOUTINGTABLE;
	}
	public String getHASCONTACT() {
		return HASCONTACT;
	}
	public void setHASCONTACT(String hASCONTACT) {
		HASCONTACT = hASCONTACT;
	}
	public String getNETWORK() {
		return NETWORK;
	}
	public void setNETWORK(String nETWORK) {
		NETWORK = nETWORK;
	}
	public String getPROCESS() {
		return PROCESS;
	}
	public void setPROCESS(String pROCESS) {
		PROCESS = pROCESS;
	}
	public String getREALTIMEPROCESS() {
		return REALTIMEPROCESS;
	}
	public void setREALTIMEPROCESS(String rEALTIMEPROCESS) {
		REALTIMEPROCESS = rEALTIMEPROCESS;
	}
	public String getACTIVEUSER() {
		return ACTIVEUSER;
	}
	public void setACTIVEUSER(String aCTIVEUSER) {
		ACTIVEUSER = aCTIVEUSER;
	}
	public String getBIOS() {
		return BIOS;
	}
	public void setBIOS(String bIOS) {
		BIOS = bIOS;
	}
	public String getNETWORKCARD() {
		return NETWORKCARD;
	}
	public void setNETWORKCARD(String nETWORKCARD) {
		NETWORKCARD = nETWORKCARD;
	}
	@TableField(columnName="FIREWALL")
	private String FIREWALL;
	@TableField(columnName="ROUTINGTABLE")
	private String ROUTINGTABLE;
	@TableField(columnName="HASCONTACT")
	private String HASCONTACT;
	@TableField(columnName="NETWORK")
	private String NETWORK;
	@TableField(columnName="PROCESS")
	private String PROCESS;
	@TableField(columnName="REALTIMEPROCESS")
	private String REALTIMEPROCESS;
	@TableField(columnName="ACTIVEUSER")
	private String ACTIVEUSER;
	@TableField(columnName="BIOS")
	private String BIOS;
	@TableField(columnName="NETWORKCARD")
	private String NETWORKCARD; 
	
	
}
