package org.xjtusicd3.database.model;


import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;
@Table(tablename="Question")
public class QuestionPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="QuestionId")
	private String QuestionId;
	@TableField(columnName="FaqTitle")
	private String FaqTitle;
	@TableField(columnName="FaqDescription")
	private String FaqDescription;
	@TableField(columnName="ClassifyId")
	private String ClassifyId;
	@TableField(columnName="FaqKeyword")
	private String FaqKeywords;
	@TableField(columnName="FaqCollection")
	private int FaqCollection;
	@TableField(columnName="FaqScan")
	private int FaqScan;
	@TableField(columnName="FaqModifytime")
	private String FaqModifytime;
	@TableField(columnName="FaqWritetime")
	private int FaqWritetime;
	@TableField(columnName="FaqScore")
	private float FaqScore;

	
	public String getQuestionId() {
		return QuestionId;
	}
	public void setQuestionId(String questionId) {
		QuestionId = questionId;
	}
	public String getClassify() {
		return ClassifyId;
	}
	public void setClassify(String classify) {
		ClassifyId = classify;
	}
	public String getFaqTitle() {
		return FaqTitle;
	}
	public void setFaqTitle(String faqTitle) {
		FaqTitle = faqTitle;
	}
	public String getFaqDescription() {
		return FaqDescription;
	}
	public void setFaqDescription(String faqDescription) {
		FaqDescription = faqDescription;
	}
	public String getFaqKeywords() {
		return FaqKeywords;
	}
	public void setFaqKeywords(String faqKeywords) {
		FaqKeywords = faqKeywords;
	}
	public String getClassifyId() {
		return ClassifyId;
	}
	public void setClassifyId(String classifyId) {
		ClassifyId = classifyId;
	}
	public int getFaqCollection() {
		return FaqCollection;
	}
	public void setFaqCollection(int faqCollection) {
		FaqCollection = faqCollection;
	}
	public int getFaqScan() {
		return FaqScan;
	}
	public void setFaqScan(int faqScan) {
		FaqScan = faqScan;
	}
	public String getFaqModifytime() {
		return FaqModifytime;
	}
	public void setFaqModifytime(String faqModifytime) {
		FaqModifytime = faqModifytime;
	}
	public int getFaqWritetime() {
		return FaqWritetime;
	}
	public void setFaqWritetime(int faqWritetime) {
		FaqWritetime = faqWritetime;
	}
	public float getFaqScore() {
		return FaqScore;
	}
	public void setFaqScore(float faqScore) {
		FaqScore = faqScore;
	}
	
}
