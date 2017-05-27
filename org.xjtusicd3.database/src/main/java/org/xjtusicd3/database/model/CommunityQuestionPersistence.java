package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_CommunityQuestion")
public class CommunityQuestionPersistence{
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="COMMUNITYQUESTIONID")
	private String COMMUNITYQUESTIONID;
	@TableField(columnName="TIME")
	private String TIME;
	@TableField(columnName="TITLE")
	private String TITLE;
	@TableField(columnName="CONTENT")
	private String CONTENT;
	@TableField(columnName="CLASSIFYID")
	private String CLASSIFYID;
	@TableField(columnName="USERID")
	private String USERID;
	@TableField(columnName="SCAN")
	private String SCAN;
	@TableField(columnName="USERQUESTIONID")
	private String USERQUESTIONID;
	@TableField(columnName="ISANSWER")
	private String ISANSWER;
	public String getCOMMUNITYQUESTIONID() {
		return COMMUNITYQUESTIONID;
	}
	public void setCOMMUNITYQUESTIONID(String cOMMUNITYQUESTIONID) {
		COMMUNITYQUESTIONID = cOMMUNITYQUESTIONID;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getCLASSIFYID() {
		return CLASSIFYID;
	}
	public void setCLASSIFYID(String cLASSIFYID) {
		CLASSIFYID = cLASSIFYID;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getSCAN() {
		return SCAN;
	}
	public void setSCAN(String sCAN) {
		SCAN = sCAN;
	}
	public String getUSERQUESTIONID() {
		return USERQUESTIONID;
	}
	public void setUSERQUESTIONID(String uSERQUESTIONID) {
		USERQUESTIONID = uSERQUESTIONID;
	}
	public String getTITLE() {
		return TITLE;
	}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	public String getISANSWER() {
		return ISANSWER;
	}
	public void setISANSWER(String iSANSWER) {
		ISANSWER = iSANSWER;
	}
}
