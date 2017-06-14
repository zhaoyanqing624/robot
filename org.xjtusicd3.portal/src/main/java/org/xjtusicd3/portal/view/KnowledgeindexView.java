package org.xjtusicd3.portal.view;
//这个view是用来显示FAQ的
public class KnowledgeindexView {
	private String faqId;
	private String faqTitle;
	private String classifyName;
	private String faqKeyWord;
	private String faqContent;
	private String faqDescription;
	public String getFaqDescription()
	{
		return faqDescription;
	}
	public void setFaqDescription(String faqDescription)
	{
		this.faqDescription = faqDescription;
	}
	public String getFaqContent()
	{
		return faqContent;
	}
	public void setFaqContent(String faqContent)
	{
		this.faqContent = faqContent;
	}
	public String getFaqId()
	{
		return faqId;
	}
	public void setFaqId(String faqId)
	{
		this.faqId = faqId;
	}
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
