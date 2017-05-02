package org.xjtusicd3.database.model;


import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;
@Table(tablename="TBL_FAQanswer")
public class AnswerPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="FAQANSWERID")
	private String FAQANSWERID;
	@TableField(columnName="FAQQUESTIONID")
	private String FAQQUESTIONID;
	@TableField(columnName="FAQCONTENT")
	private String FAQCONTENT;
	@TableField(columnName="USERID")
	private String USERID;
	public String getFAQANSWERID() {
		return FAQANSWERID;
	}
	public void setFAQANSWERID(String fAQANSWERID) {
		FAQANSWERID = fAQANSWERID;
	}
	public String getFAQQUESTIONID() {
		return FAQQUESTIONID;
	}
	public void setFAQQUESTIONID(String fAQQUESTIONID) {
		FAQQUESTIONID = fAQQUESTIONID;
	}
	public String getFAQCONTENT() {
		return FAQCONTENT;
	}
	public void setFAQCONTENT(String fAQCONTENT) {
		FAQCONTENT = fAQCONTENT;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
}
