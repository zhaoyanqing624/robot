package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="Patch")
public class PatchPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="ConfigureId")
	private String ConfigureId;
	@TableField(columnName="PatchOS")
	private String PatchOS;
	@TableField(columnName="PatchKeyword")
	private String PatchKeyword;
	@TableField(columnName="PatchLanguage")
	private String 	PatchLanguage;
	@TableField(columnName="PatchContent")
	private String PatchContent;
	public String getConfigureId() {
		return ConfigureId;
	}
	public void setConfigureId(String configureId) {
		ConfigureId = configureId;
	}
	public String getPatchOS() {
		return PatchOS;
	}
	public void setPatchOS(String patchOS) {
		PatchOS = patchOS;
	}
	public String getPatchKeyword() {
		return PatchKeyword;
	}
	public void setPatchKeyword(String patchKeyword) {
		PatchKeyword = patchKeyword;
	}
	public String getPatchLanguage() {
		return PatchLanguage;
	}
	public void setPatchLanguage(String patchLanguage) {
		PatchLanguage = patchLanguage;
	}
	public String getPatchContent() {
		return PatchContent;
	}
	public void setPatchContent(String patchContent) {
		PatchContent = patchContent;
	}

}
