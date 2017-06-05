package org.xjtusicd3.portal.view;
//这个view是用来显示FAQ的
public class KnowledgeindexView {
	private String faqTitle;
	private String classifyName;
	private String faqKeyWord;
	public String getFaqKeyWord() {
		return faqKeyWord;
	}
	public void setFaqKeyWord(String faqKeyWord) {
		this.faqKeyWord = faqKeyWord;
	}
	public String getFaqTitle() {
		return faqTitle;
	}
	public void setFaqTitle(String faqTitle) {
		this.faqTitle = faqTitle;
	}
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	
}
