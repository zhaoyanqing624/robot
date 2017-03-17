package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;
@Table(tablename="faq")
public class FAQSpiderPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="faqTitle")
	private String faqTitle;
	@TableField(columnName="faqDescription")
	private String faqDescription;
	@TableField(columnName="faqClassify")
	private String faqClassify;
	@TableField(columnName="faqKeywords")
	private String faqKeywords;
	@TableField(columnName="faqContent")
	private String faqContent;

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
	public String getFaqClassify() {
		return faqClassify;
	}
	public void setFaqClassify(String faqClassify) {
		this.faqClassify = faqClassify;
	}
	public String getFaqKeywords() {
		return faqKeywords;
	}
	public void setFaqKeywords(String faqKeywords) {
		this.faqKeywords = faqKeywords;
	}
	public String getFaqContent() {
		return faqContent;
	}
	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}
	
}
