package org.xjtusicd3.partner.view;

import org.xjtusicd3.database.model.AdvisePersistence;

public class AdviseView {
	private String EMAIL;
	private String NAME;
	private String PHONE;
	private String TEXT;
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public String getTEXT() {
		return TEXT;
	}
	public void setTEXT(String tEXT) {
		TEXT = tEXT;
	}
	
	public AdviseView(AdvisePersistence advisePersistence){
		this.EMAIL = advisePersistence.getEMAIL();
		this.NAME = advisePersistence.getNAME();
		this.PHONE = advisePersistence.getPHONE();
		this.TEXT = advisePersistence.getTEXT();
	}
	public AdviseView(){
		
	}
}
