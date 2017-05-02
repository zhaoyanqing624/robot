package org.xjtusicd3.database.model;


import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;
@Table(tablename="TBL_FAQquestion")
public class QuestionPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="FAQQUESTIONID")
	private String FAQQUESTIONID;
	@TableField(columnName="FAQTITLE")
	private String FAQTITLE;
	@TableField(columnName="FAQDESCRIPTION")
	private String FAQDESCRIPTION;
	@TableField(columnName="FAQCLASSIFYID")
	private String FAQCLASSIFYID;
	@TableField(columnName="FAQKEYWORDS")
	private String FAQKEYWORDS;
	@TableField(columnName="COLLECTION")
	private String COLLECTION;
	@TableField(columnName="SCAN")
	private String SCAN;
	@TableField(columnName="MODIFYTIME")
	private String MODIFYTIME;
	@TableField(columnName="MODIFYNUMBER")
	private String MODIFYNUMBER;
	@TableField(columnName="SCORE")
	private float SCORE;
	public String getFAQQUESTIONID() {
		return FAQQUESTIONID;
	}
	public void setFAQQUESTIONID(String fAQQUESTIONID) {
		FAQQUESTIONID = fAQQUESTIONID;
	}
	public String getFAQTITLE() {
		return FAQTITLE;
	}
	public void setFAQTITLE(String fAQTITLE) {
		FAQTITLE = fAQTITLE;
	}
	public String getFAQDESCRIPTION() {
		return FAQDESCRIPTION;
	}
	public void setFAQDESCRIPTION(String fAQDESCRIPTION) {
		FAQDESCRIPTION = fAQDESCRIPTION;
	}
	public String getFAQCLASSIFYID() {
		return FAQCLASSIFYID;
	}
	public void setFAQCLASSIFYID(String fAQCLASSIFYID) {
		FAQCLASSIFYID = fAQCLASSIFYID;
	}
	public String getFAQKEYWORDS() {
		return FAQKEYWORDS;
	}
	public void setFAQKEYWORDS(String fAQKEYWORDS) {
		FAQKEYWORDS = fAQKEYWORDS;
	}
	public String getCOLLECTION() {
		return COLLECTION;
	}
	public void setCOLLECTION(String cOLLECTION) {
		COLLECTION = cOLLECTION;
	}
	public String getSCAN() {
		return SCAN;
	}
	public void setSCAN(String sCAN) {
		SCAN = sCAN;
	}
	public String getMODIFYTIME() {
		return MODIFYTIME;
	}
	public void setMODIFYTIME(String mODIFYTIME) {
		MODIFYTIME = mODIFYTIME;
	}
	public String getMODIFYNUMBER() {
		return MODIFYNUMBER;
	}
	public void setMODIFYNUMBER(String mODIFYNUMBER) {
		MODIFYNUMBER = mODIFYNUMBER;
	}
	public float getSCORE() {
		return SCORE;
	}
	public void setSCORE(float sCORE) {
		SCORE = sCORE;
	}
	
	
	
	
}
