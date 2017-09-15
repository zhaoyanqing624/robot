package org.xjtusicd3.partner.view;


/**
 * author:zzl
 * abstract:已登录用户推荐View
 * data:2017年9月15日09:05:05
 */
public class Faq_CommendView {
	private	String userId;
	private String FAQQUESTIONID;
	private String faqParentId;
	private String FAQTITLE;
	private String MODIFYTIME;
	private String FAQDESCRIPTION;
	private int COLLECTION;
	private String SCAN;
	private String FAQCLASSIFYIMAGE;
	private int COMMENTSUM;
	
	
	
	

	public int getCOMMENTSUM() {
		return COMMENTSUM;
	}
	public void setCOMMENTSUM(int cOMMENTSUM) {
		COMMENTSUM = cOMMENTSUM;
	}
	public String getFAQCLASSIFYIMAGE() {
		return FAQCLASSIFYIMAGE;
	}
	public void setFAQCLASSIFYIMAGE(String fAQCLASSIFYIMAGE) {
		FAQCLASSIFYIMAGE = fAQCLASSIFYIMAGE;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public String getFAQQUESTIONID() {
		return FAQQUESTIONID;
	}
	public void setFAQQUESTIONID(String fAQQUESTIONID) {
		FAQQUESTIONID = fAQQUESTIONID;
	}
	public String getFaqParentId() {
		return faqParentId;
	}
	public void setFaqParentId(String faqParentId) {
		this.faqParentId = faqParentId;
	}
	public String getFAQTITLE() {
		return FAQTITLE;
	}
	public void setFAQTITLE(String fAQTITLE) {
		FAQTITLE = fAQTITLE;
	}
	public String getMODIFYTIME() {
		return MODIFYTIME;
	}
	public void setMODIFYTIME(String mODIFYTIME) {
		MODIFYTIME = mODIFYTIME;
	}
	public String getFAQDESCRIPTION() {
		return FAQDESCRIPTION;
	}
	public void setFAQDESCRIPTION(String fAQDESCRIPTION) {
		FAQDESCRIPTION = fAQDESCRIPTION;
	}
	
	public int getCOLLECTION() {
		return COLLECTION;
	}
	public void setCOLLECTION(int cOLLECTION) {
		COLLECTION = cOLLECTION;
	}
	public String getSCAN() {
		return SCAN;
	}
	public void setSCAN(String sCAN) {
		SCAN = sCAN;
	}
	
	
	
}
