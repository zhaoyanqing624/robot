package org.xjtusicd3.partner.view;

import org.xjtusicd3.database.model.FaqPersistence;

public class Faq_faq1View {
	private int faqId;
	private String faqTitle;
	private String faqDescription;
	public int getFaqId() {
		return faqId;
	}
	public void setFaqId(int faqId) {
		this.faqId = faqId;
	}
	public String getFaqTitle() {
		return faqTitle;
	}
	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}
	public String getFaqDescription() {
		return faqDescription;
	}
	public void setFaqDescription(String faqDescription) {
		this.faqDescription = faqDescription;
	}
	
	public Faq_faq1View(FaqPersistence faqPersistence){
		this.faqId = faqPersistence.getFaqId();
		this.faqTitle = faqPersistence.getFaqTitle();
		this.faqDescription = faqPersistence.getFaqDescription();
	}
	public Faq_faq1View(){
		
	}
}
