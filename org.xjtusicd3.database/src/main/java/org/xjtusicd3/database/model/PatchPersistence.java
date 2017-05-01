package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Patch")
public class PatchPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="CONFIGUREID")
	private String CONFIGUREID;
	@TableField(columnName="OS")
	private String OS;
	@TableField(columnName="PATCHNUMBER")
	private String PATCHNUMBER;
	@TableField(columnName="LANGUAGE")
	private String 	LANGUAGE;
	@TableField(columnName="PATCHINTRODUCTION")
	private String PATCHINTRODUCTION;
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
	public String getPATCHNUMBER() {
		return PATCHNUMBER;
	}
	public void setPATCHNUMBER(String pATCHNUMBER) {
		PATCHNUMBER = pATCHNUMBER;
	}
	public String getLANGUAGE() {
		return LANGUAGE;
	}
	public void setLANGUAGE(String lANGUAGE) {
		LANGUAGE = lANGUAGE;
	}
	public String getPATCHINTRODUCTION() {
		return PATCHINTRODUCTION;
	}
	public void setPATCHINTRODUCTION(String pATCHINTRODUCTION) {
		PATCHINTRODUCTION = pATCHINTRODUCTION;
	}
	
	

}
