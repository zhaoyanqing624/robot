package org.xjtusicd3.partner.view;

import java.util.List;

import org.xjtusicd3.database.model.ClassifyPersistence;

public class Faq1_ClassifyView {
	private String ClassifyId;
	private String ClassifyName;
	private List<Faq1_faqContentView> content;
	private List<Faq1_faqContentView> content2;
	public String getClassifyId() {
		return ClassifyId;
	}
	public void setClassifyId(String classifyId) {
		ClassifyId = classifyId;
	}
	public String getClassifyName() {
		return ClassifyName;
	}
	public void setClassifyName(String classifyName) {
		ClassifyName = classifyName;
	}
	public List<Faq1_faqContentView> getContent() {
		return content;
	}
	public void setContent(List<Faq1_faqContentView> content) {
		this.content = content;
	}
	public List<Faq1_faqContentView> getContent2() {
		return content2;
	}
	public void setContent2(List<Faq1_faqContentView> content2) {
		this.content2 = content2;
	}
	public Faq1_ClassifyView(ClassifyPersistence classifyPersistence){
		this.ClassifyId = classifyPersistence.getClassifyId();
		this.ClassifyName = classifyPersistence.getClassifyName();
	}
	public Faq1_ClassifyView(){
		
	}
}
