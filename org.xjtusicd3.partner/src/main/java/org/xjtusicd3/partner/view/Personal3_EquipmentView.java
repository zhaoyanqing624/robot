package org.xjtusicd3.partner.view;

import java.util.List;

import org.xjtusicd3.database.model.CurrentEquipmentPersistence;

public class Personal3_EquipmentView {
	private String EQUIPMENTMODEL;
	private String EQUIPMENTTIME;
	private String CPU;
	private String RAM;
	private String HARDDRIVER;
	private String NETWORKCARD;
	private String NETWORKCARD2;
	private String MOTHERBOARD;
	private String OS;
	private List<Personal3_EquipmentConfigureView> patchViews;
	private List<Personal3_EquipmentConfigureView> softViews;
	public String getEQUIPMENTMODEL() {
		return EQUIPMENTMODEL;
	}
	public void setEQUIPMENTMODEL(String eQUIPMENTMODEL) {
		EQUIPMENTMODEL = eQUIPMENTMODEL;
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
	public String getHARDDRIVER() {
		return HARDDRIVER;
	}
	public void setHARDDRIVER(String hARDDRIVER) {
		HARDDRIVER = hARDDRIVER;
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
	public String getOS() {
		return OS;
	}
	public void setOS(String oS) {
		OS = oS;
	}
	
	
	public String getEQUIPMENTTIME() {
		return EQUIPMENTTIME;
	}
	public void setEQUIPMENTTIME(String eQUIPMENTTIME) {
		EQUIPMENTTIME = eQUIPMENTTIME;
	}
	public List<Personal3_EquipmentConfigureView> getPatchViews() {
		return patchViews;
	}
	public void setPatchViews(List<Personal3_EquipmentConfigureView> patchViews) {
		this.patchViews = patchViews;
	}
	public List<Personal3_EquipmentConfigureView> getSoftViews() {
		return softViews;
	}
	public void setSoftViews(List<Personal3_EquipmentConfigureView> softViews) {
		this.softViews = softViews;
	}
	public Personal3_EquipmentView(CurrentEquipmentPersistence currentEquipmentPersistence){
		this.EQUIPMENTMODEL = currentEquipmentPersistence.getEQUIPMENTMODEL();
		this.CPU = currentEquipmentPersistence.getCPU();
		this.RAM = currentEquipmentPersistence.getRAM();
		this.HARDDRIVER = currentEquipmentPersistence.getHARDDRIVER();
		this.NETWORKCARD = currentEquipmentPersistence.getNETWORKCARD();
		this.MOTHERBOARD = currentEquipmentPersistence.getMOTHERBOARD();
		this.OS = currentEquipmentPersistence.getOSNAME()+" "+currentEquipmentPersistence.getOSTYPE();
		this.EQUIPMENTTIME = currentEquipmentPersistence.getEQUIPMENTTIME();
	}
	public Personal3_EquipmentView(){
		
	}
}
