package org.xjtusicd3.partner.view;

import org.xjtusicd3.database.model.AnswerPersistence;

public class Faq3_faqAnswer {
	private String FaqContent;

	public String getFaqContent() {
		return FaqContent;
	}

	public void setFaqContent(String faqContent) {
		FaqContent = faqContent;
	}
	public Faq3_faqAnswer(AnswerPersistence answerPersistence){
		this.FaqContent = answerPersistence.getFAQCONTENT();
	}
	public Faq3_faqAnswer(){
		
	}
}
