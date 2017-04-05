package org.xjtusicd3.partner.view;

import java.util.List;

import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.FaqPersistence;

public class Classify_faq1View {
	private int classifyId;
	private String classifyName;
	private List<FaqTitleView> content;
	private List<FaqTitleView> content2;
	
	public int getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(int classifyId) {
		this.classifyId = classifyId;
	}
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	public List<FaqTitleView> getContent() {
		return content;
	}
	public void setContent(List<FaqTitleView> content) {
		this.content = content;
	}
	public List<FaqTitleView> getContent2() {
		return content2;
	}
	public void setContent2(List<FaqTitleView> content2) {
		this.content2 = content2;
	}
	public Classify_faq1View(ClassifyPersistence classifyPersistence){
		this.classifyId = classifyPersistence.getClassifyId();
		this.classifyName = classifyPersistence.getClassifyName();
	}
	public Classify_faq1View(){
		
	}
}
