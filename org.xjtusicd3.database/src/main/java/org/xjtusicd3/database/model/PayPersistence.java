package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Pay")
public class PayPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="PAYID")
	private String PAYID;
	@TableField(columnName="PAYUSERID")
	private String PAYUSERID;
	@TableField(columnName="BEPAYUSERID")
	private String BEPAYUSERID;
	public String getPAYID() {
		return PAYID;
	}
	public void setPAYID(String pAYID) {
		PAYID = pAYID;
	}
	public String getPAYUSERID() {
		return PAYUSERID;
	}
	public void setPAYUSERID(String pAYUSERID) {
		PAYUSERID = pAYUSERID;
	}
	public String getBEPAYUSERID() {
		return BEPAYUSERID;
	}
	public void setBEPAYUSERID(String bEPAYUSERID) {
		BEPAYUSERID = bEPAYUSERID;
	}
	
}
