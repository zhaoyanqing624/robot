package org.xjtusicd3.partner.view;

import org.xjtusicd3.database.model.CurrentConfigurePersistence;

public class Personal3_EquipmentConfigureView {
	private String CONFIGURENAME;
	private String CONFIGUREVERSION;
	
	
	
	public String getCONFIGURENAME() {
		return CONFIGURENAME;
	}



	public void setCONFIGURENAME(String cONFIGURENAME) {
		CONFIGURENAME = cONFIGURENAME;
	}



	public String getCONFIGUREVERSION() {
		return CONFIGUREVERSION;
	}



	public void setCONFIGUREVERSION(String cONFIGUREVERSION) {
		CONFIGUREVERSION = cONFIGUREVERSION;
	}



	public Personal3_EquipmentConfigureView(CurrentConfigurePersistence configurePersistence){
		this.CONFIGURENAME = configurePersistence.getCONFIGURENAME();
		this.CONFIGUREVERSION = configurePersistence.getCONFIGUREVERSION();
	}
	public Personal3_EquipmentConfigureView(){
		
	}
}
