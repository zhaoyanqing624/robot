package org.xjtusicd3.partner.view;

import org.xjtusicd3.database.model.QuestionPersistence;

public class Faq1_faqContentView {
	private String QuestionId;
	private String FaqTitle;
	private String FaqDescription;
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
	public Faq1_faqContentView(QuestionPersistence questionPersistence ){
		this.QuestionId = questionPersistence.getFAQQUESTIONID();
		this.FaqTitle = questionPersistence.getFAQTITLE();
		this.FaqDescription = questionPersistence.getFAQDESCRIPTION();
	}
	public Faq1_faqContentView(){
		
	}

}
