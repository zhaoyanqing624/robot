package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Score")
public class ScorePersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="SCOREID")
	private String SCOREID;
	@TableField(columnName="USERID")
	private String USERID;
	@TableField(columnName="FAQQUESTIONID")
	private String FAQQUESTIONID;
	@TableField(columnName="SCORE")
	private float SCORE;
	@TableField(columnName="TIME")
	private String TIME;
	public String getSCOREID() {
		return SCOREID;
	}
	public void setSCOREID(String sCOREID) {
		SCOREID = sCOREID;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getFAQQUESTIONID() {
		return FAQQUESTIONID;
	}
	public void setFAQQUESTIONID(String fAQQUESTIONID) {
		FAQQUESTIONID = fAQQUESTIONID;
	}
	public float getSCORE() {
		return SCORE;
	}
	public void setSCORE(float sCORE) {
		SCORE = sCORE;
	}
	public String getTIME() {
		return TIME;
	}
	public void setTIME(String tIME) {
		TIME = tIME;
	}
}
