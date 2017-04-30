package org.xjtusicd3.partner.view;


import java.util.List;

import org.xjtusicd3.database.model.ClassifyPersistence;

public class ClassifyView2 {
	/*
	 * robot-第二类
	 */
	private String title;
	private List<FaqTitleView> content;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public List<FaqTitleView> getContent() {
		return content;
	}
	public void setContent(List<FaqTitleView> content) {
		this.content = content;
	}
	public ClassifyView2(ClassifyPersistence classifyPersistence){
		this.title = classifyPersistence.getClassifyName();
	}
	public ClassifyView2(){
	}
}
