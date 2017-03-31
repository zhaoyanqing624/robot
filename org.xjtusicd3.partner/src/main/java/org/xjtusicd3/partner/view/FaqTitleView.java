package org.xjtusicd3.partner.view;

import org.xjtusicd3.database.model.FaqPersistence;

public class FaqTitleView {
	/*
	 * robot-第三类
	 */
	private String faqTitle;
	
	public String getFaqTitle() {
		return faqTitle;
	}

	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}

	public FaqTitleView(FaqPersistence faqPersistence){
		this.faqTitle = faqPersistence.getFaqTitle();
	}
	
	public FaqTitleView(){
		
	}
}
