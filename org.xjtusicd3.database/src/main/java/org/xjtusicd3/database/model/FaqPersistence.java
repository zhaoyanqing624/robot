package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;
@Table(tablename="faq")
public class FaqPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="faqId")
	private int faqId;
	@TableField(columnName="faqTitle")
	private String faqTitle;
	@TableField(columnName="faqDescription")
	private String faqDescription;
	@TableField(columnName="faqClassify")
	private int faqClassify;
	@TableField(columnName="faqKeywords")
	private String faqKeywords;
	@TableField(columnName="faqContent")
	private String faqContent;
	@TableField(columnName="faqCollection")
	private int faqCollection;
	@TableField(columnName="faqScan")
	private int faqScan;
	@TableField(columnName="faqModifytime")
	private String faqModifytime;
	@TableField(columnName="faqScore")
	private float faqScore;
	
	public int getFaqId() {
		return faqId;
	}
	public void setFaqId(int faqId) {
		this.faqId = faqId;
	}
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
	public int getFaqClassify() {
		return faqClassify;
	}
	public void setFaqClassify(int faqClassify) {
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
	public int getFaqCollection() {
		return faqCollection;
	}
	public void setFaqCollection(int faqCollection) {
		this.faqCollection = faqCollection;
	}
	public int getFaqScan() {
		return faqScan;
	}
	public void setFaqScan(int faqScan) {
		this.faqScan = faqScan;
	}
	public String getFaqModifytime() {
		return faqModifytime;
	}
	public void setFaqModifytime(String faqModifytime) {
		this.faqModifytime = faqModifytime;
	}
	public float getFaqScore() {
		return faqScore;
	}
	public void setFaqScore(float faqScore) {
		this.faqScore = faqScore;
	}
	
	
}
