package org.xjtusicd3.partner.view;

import java.util.List;

import org.xjtusicd3.database.model.QuestionPersistence;


public class Faq2_faqContentView {
	private String QuestionId;
	private String FaqTitle;
	private String FaqDescription;
	private String FaqScan;
	private String FaqModifytime;
	private String FaqCollection;
	private List<Faq2_faqUserView> uList;
	
	
	public String getQuestionId() {
		return QuestionId;
	}
	public void setQuestionId(String questionId) {
		QuestionId = questionId;
	}
	public String getFaqTitle() {
		return FaqTitle;
	}
	public void setFaqTitle(String faqTitle) {
		FaqTitle = faqTitle;
	}
	public String getFaqDescription() {
		return FaqDescription;
	}
	public void setFaqDescription(String faqDescription) {
		FaqDescription = faqDescription;
	}
	public String getFaqScan() {
		return FaqScan;
	}
	public void setFaqScan(String faqScan) {
		FaqScan = faqScan;
	}
	public String getFaqModifytime() {
		return FaqModifytime;
	}
	public void setFaqModifytime(String faqModifytime) {
		FaqModifytime = faqModifytime;
	}
	public String getFaqCollection() {
		return FaqCollection;
	}
	public void setFaqCollection(String faqCollection) {
		FaqCollection = faqCollection;
	}
	public List<Faq2_faqUserView> getuList() {
		return uList;
	}
	public void setuList(List<Faq2_faqUserView> uList) {
		this.uList = uList;
	}
	public Faq2_faqContentView(QuestionPersistence questionPersistence){
		this.QuestionId = questionPersistence.getFAQQUESTIONID();
		this.FaqTitle = questionPersistence.getFAQTITLE();
		this.FaqDescription = questionPersistence.getFAQDESCRIPTION();
		this.FaqModifytime = questionPersistence.getMODIFYTIME();
		this.FaqScan = questionPersistence.getSCAN();
		this.FaqCollection = questionPersistence.getCOLLECTION();
	}
	public Faq2_faqContentView(){
		
	}
}
