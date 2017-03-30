package org.xjtusicd3.partner.view;

import org.xjtusicd3.database.model.FaqPersistence;

public class FaqTitleView {
	/*
	 * robot-第三类
	 */
	private String content;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
	public FaqTitleView(FaqPersistence faqPersistence){
		this.content = faqPersistence.getFaqTitle();
	}
	
	public FaqTitleView(){
		
	}
}
