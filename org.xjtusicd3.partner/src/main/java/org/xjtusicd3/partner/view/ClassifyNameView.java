package org.xjtusicd3.partner.view;

import org.xjtusicd3.database.model.ClassifyPersistence;

public class ClassifyNameView {
	private String classifyName;

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	
	public ClassifyNameView(ClassifyPersistence classifyPersistence){
		this.classifyName = classifyPersistence.getClassifyName();
	}
	public ClassifyNameView(){
		
	}
}
