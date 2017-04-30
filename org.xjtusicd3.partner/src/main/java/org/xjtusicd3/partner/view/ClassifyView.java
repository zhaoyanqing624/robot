package org.xjtusicd3.partner.view;

import java.util.List;

import org.xjtusicd3.database.model.ClassifyPersistence;

public class ClassifyView {
	/*
	 * robot-第一类
	 */
	private String title;
	private List<ClassifyView2> content;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public List<ClassifyView2> getContent() {
		return content;
	}

	public void setContent(List<ClassifyView2> content) {
		this.content = content;
	}

	public ClassifyView(ClassifyPersistence classifyPersistence){
		this.title = classifyPersistence.getClassifyName();
	}
	public ClassifyView(){
	}
}
