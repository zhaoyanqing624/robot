package org.xjtusicd3.partner.view;

import java.util.List;

import org.xjtusicd3.database.model.FaqPersistence;

public class Faq_faq3View {
	private int faqId;
	private String faqTitle;
	private String faqDescription;
	private int faqScan;
	private String faqModifytime;
	private int faqCollection;
	private int faqWritetime;
	private String faqContent;
	private float faqScore;
	
	private List<User_faq2View> uList;

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

	public int getFaqCollection() {
		return faqCollection;
	}

	public void setFaqCollection(int faqCollection) {
		this.faqCollection = faqCollection;
	}

	
	
	public List<User_faq2View> getuList() {
		return uList;
	}

	public void setuList(List<User_faq2View> uList) {
		this.uList = uList;
	}

	
	public int getFaqWritetime() {
		return faqWritetime;
	}

	public void setFaqWritetime(int faqWritetime) {
		this.faqWritetime = faqWritetime;
	}

	public String getFaqContent() {
		return faqContent;
	}

	public void setFaqContent(String faqContent) {
		this.faqContent = faqContent;
	}

	public float getFaqScore() {
		return faqScore;
	}

	public void setFaqScore(float faqScore) {
		this.faqScore = faqScore;
	}

	public Faq_faq3View(FaqPersistence faqPersistence){
		this.faqId = faqPersistence.getFaqId();
		this.faqTitle = faqPersistence.getFaqTitle();
		this.faqDescription = faqPersistence.getFaqDescription();
		this.faqModifytime = faqPersistence.getFaqModifytime();
		this.faqScan = faqPersistence.getFaqScan();
		this.faqCollection = faqPersistence.getFaqCollection();
		this.faqWritetime = faqPersistence.getFaqWritetime();
		this.faqContent = faqPersistence.getFaqContent();
		this.faqScore = faqPersistence.getFaqScore();
	}
}
