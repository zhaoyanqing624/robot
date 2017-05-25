package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_CommunityAnswer")
public class CommunityAnswerPersistence{
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="COMMUNITYANSWERID")
	private String COMMUNITYANSWERID;
	@TableField(columnName="TIME")
	private String TIME;
	@TableField(columnName="CONTENT")
	private String CONTENT;
	@TableField(columnName="ISBESTANSWER")
	private int ISBESTANSWER;
	@TableField(columnName="COMMUNITYQUESTIONID")
	private String COMMUNITYQUESTIONID;
	@TableField(columnName="USERID")
	private String USERID;
	@TableField(columnName="ISNOTICE")
	private int ISNOTICE;
	public String getCOMMUNITYANSWERID() {
		return COMMUNITYANSWERID;
	}
	public void setCOMMUNITYANSWERID(String cOMMUNITYANSWERID) {
		COMMUNITYANSWERID = cOMMUNITYANSWERID;
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
	public int getISBESTANSWER() {
		return ISBESTANSWER;
	}
	public void setISBESTANSWER(int iSBESTANSWER) {
		ISBESTANSWER = iSBESTANSWER;
	}
	public String getCOMMUNITYQUESTIONID() {
		return COMMUNITYQUESTIONID;
	}
	public void setCOMMUNITYQUESTIONID(String cOMMUNITYQUESTIONID) {
		COMMUNITYQUESTIONID = cOMMUNITYQUESTIONID;
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
	
	
}
