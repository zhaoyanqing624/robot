package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Generaluser")
public class GeneraluserPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="USERID")
	private String USERID;
	@TableField(columnName="EMPLOYER")
	private String EMPLOYER;
	@TableField(columnName="DEPARTMENT")
	private String DEPARTMENT;
	@TableField(columnName="REALNAME")
	private String REALNAME;
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getEMPLOYER() {
		return EMPLOYER;
	}
	public void setEMPLOYER(String eMPLOYER) {
		EMPLOYER = eMPLOYER;
	}
	public String getDEPARTMENT() {
		return DEPARTMENT;
	}
	public void setDEPARTMENT(String dEPARTMENT) {
		DEPARTMENT = dEPARTMENT;
	}
	public String getREALNAME() {
		return REALNAME;
	}
	public void setREALNAME(String rEALNAME) {
		REALNAME = rEALNAME;
	}
	
}
