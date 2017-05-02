package org.xjtusicd3.database.model;


import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_FAQclassify")
public class ClassifyPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="FAQCLASSIFYID")
	private String FAQCLASSIFYID;
	@TableField(columnName="FAQCLASSIFYNAME")
	private String FAQCLASSIFYNAME;
	@TableField(columnName="FAQPARENTID")
	private String FAQPARENTID;
	public String getFAQCLASSIFYID() {
		return FAQCLASSIFYID;
	}
	public void setFAQCLASSIFYID(String fAQCLASSIFYID) {
		FAQCLASSIFYID = fAQCLASSIFYID;
	}
	public String getFAQCLASSIFYNAME() {
		return FAQCLASSIFYNAME;
	}
	public void setFAQCLASSIFYNAME(String fAQCLASSIFYNAME) {
		FAQCLASSIFYNAME = fAQCLASSIFYNAME;
	}
	public String getFAQPARENTID() {
		return FAQPARENTID;
	}
	public void setFAQPARENTID(String fAQPARENTID) {
		FAQPARENTID = fAQPARENTID;
	}
	
}
