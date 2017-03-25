package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="patch")
public class PatchSpiderPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="patchId")
	private int patchId;
	@TableField(columnName="patchName")
	private String patchName;
	@TableField(columnName="patchOs")
	private String patchOs;
	@TableField(columnName="patchProducer")
	private String patchProducer;
	@TableField(columnName="patchDate")
	private String patchDate;
	@TableField(columnName="patchSize")
	private String patchSize;
	@TableField(columnName="patchKeyword")
	private String patchKeyword;
	@TableField(columnName="patchLanguage")
	private String patchLanguage;
	@TableField(columnName="patchContent")
	private String patchContent;
	@TableField(columnName="patchURL")
	private String patchURL;
	public int getPatchId() {
		return patchId;
	}
	public void setPatchId(int patchId) {
		this.patchId = patchId;
	}
	public String getPatchName() {
		return patchName;
	}
	public void setPatchName(String patchName) {
		this.patchName = patchName;
	}
	public String getPatchOs() {
		return patchOs;
	}
	public void setPatchOs(String patchOs) {
		this.patchOs = patchOs;
	}
	public String getPatchProducer() {
		return patchProducer;
	}
	public void setPatchProducer(String patchProducer) {
		this.patchProducer = patchProducer;
	}
	public String getPatchDate() {
		return patchDate;
	}
	public void setPatchDate(String patchDate) {
		this.patchDate = patchDate;
	}
	public String getPatchSize() {
		return patchSize;
	}
	public void setPatchSize(String patchSize) {
		this.patchSize = patchSize;
	}
	public String getPatchKeyword() {
		return patchKeyword;
	}
	public void setPatchKeyword(String patchKeyword) {
		this.patchKeyword = patchKeyword;
	}
	public String getPatchLanguage() {
		return patchLanguage;
	}
	public void setPatchLanguage(String patchLanguage) {
		this.patchLanguage = patchLanguage;
	}
	public String getPatchContent() {
		return patchContent;
	}
	public void setPatchContent(String patchContent) {
		this.patchContent = patchContent;
	}
	public String getPatchURL() {
		return patchURL;
	}
	public void setPatchURL(String patchURL) {
		this.patchURL = patchURL;
	}
	
	
}
