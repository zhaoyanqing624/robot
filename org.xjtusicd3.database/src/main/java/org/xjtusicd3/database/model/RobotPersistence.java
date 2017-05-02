package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Robot")
public class RobotPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="ROBOTID")
	private String ROBOTID;
	@TableField(columnName="ROBOTNAME")
	private String ROBOTNAME;
	@TableField(columnName="ROBOTWELCOME")
	private String ROBOTWELCOME;
	@TableField(columnName="ROBOTINTRODUCTION")
	private String ROBOTINTRODUCTION;
	@TableField(columnName="ROBOTIMAGE")
	private String ROBOTIMAGE;
	@TableField(columnName="ROBOTNOKNOW")
	private String ROBOTNOKNOW;
	public String getROBOTID() {
		return ROBOTID;
	}
	public void setROBOTID(String rOBOTID) {
		ROBOTID = rOBOTID;
	}
	public String getROBOTNAME() {
		return ROBOTNAME;
	}
	public void setROBOTNAME(String rOBOTNAME) {
		ROBOTNAME = rOBOTNAME;
	}
	public String getROBOTWELCOME() {
		return ROBOTWELCOME;
	}
	public void setROBOTWELCOME(String rOBOTWELCOME) {
		ROBOTWELCOME = rOBOTWELCOME;
	}
	public String getROBOTINTRODUCTION() {
		return ROBOTINTRODUCTION;
	}
	public void setROBOTINTRODUCTION(String rOBOTINTRODUCTION) {
		ROBOTINTRODUCTION = rOBOTINTRODUCTION;
	}
	public String getROBOTIMAGE() {
		return ROBOTIMAGE;
	}
	public void setROBOTIMAGE(String rOBOTIMAGE) {
		ROBOTIMAGE = rOBOTIMAGE;
	}
	public String getROBOTNOKNOW() {
		return ROBOTNOKNOW;
	}
	public void setROBOTNOKNOW(String rOBOTNOKNOW) {
		ROBOTNOKNOW = rOBOTNOKNOW;
	}
	
	
}
