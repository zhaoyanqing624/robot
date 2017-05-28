package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Comment")
public class CommentPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="COMMENTID")
	private String COMMENTID;
	@TableField(columnName="FAQQUESTIONID")
	private String FAQQUESTIONID;
	@TableField(columnName="COMMUNITYQUESTIONID")
	private String COMMUNITYQUESTIONID;
	@TableField(columnName="USERID")
	private String USERID;
	@TableField(columnName="COMMENTCONTENT")
	private String COMMENTCONTENT;
	@TableField(columnName="COMMENTTIME")
	private String COMMENTTIME;
	@TableField(columnName="COMMENTPARENTID")
	private String COMMENTPARENTID;
	@TableField(columnName="ISNOTICE")
	private int ISNOTICE;
	
	public int getISNOTICE() {
		return ISNOTICE;
	}
	public void setISNOTICE(int iSNOTICE) {
		ISNOTICE = iSNOTICE;
	}
	public String getCOMMENTID() {
		return COMMENTID;
	}
	public void setCOMMENTID(String cOMMENTID) {
		COMMENTID = cOMMENTID;
	}
	public String getFAQQUESTIONID() {
		return FAQQUESTIONID;
	}
	public void setFAQQUESTIONID(String fAQQUESTIONID) {
		FAQQUESTIONID = fAQQUESTIONID;
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
	public String getCOMMENTCONTENT() {
		return COMMENTCONTENT;
	}
	public void setCOMMENTCONTENT(String cOMMENTCONTENT) {
		COMMENTCONTENT = cOMMENTCONTENT;
	}
	public String getCOMMENTTIME() {
		return COMMENTTIME;
	}
	public void setCOMMENTTIME(String cOMMENTTIME) {
		COMMENTTIME = cOMMENTTIME;
	}
	public String getCOMMENTPARENTID() {
		return COMMENTPARENTID;
	}
	public void setCOMMENTPARENTID(String cOMMENTPARENTID) {
		COMMENTPARENTID = cOMMENTPARENTID;
	}
}
