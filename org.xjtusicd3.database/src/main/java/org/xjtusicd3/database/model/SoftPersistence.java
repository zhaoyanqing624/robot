package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;
@Table(tablename="Soft")
public class SoftPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="ConfigureId")
	private String ConfigureId;
	@TableField(columnName="SoftId")
	private String SoftId;
	@TableField(columnName="SoftScore")
	private String SoftScore;
	@TableField(columnName="Logo")
	private String Logo;
	@TableField(columnName="Logo48")
	private String Logo48;
	@TableField(columnName="Logo96")
	private String Logo96;
	@TableField(columnName="Offical_website")
	private String Offical_website;
	@TableField(columnName="Soft_desc")
	private String Soft_desc;
	@TableField(columnName="Soft_desc_short")
	private String Soft_desc_short;
	@TableField(columnName="Nick_version")
	private String Nick_version;
	@TableField(columnName="Whats_new_desc")
	private String whats_new_desc;
	@TableField(columnName="Whats_new_desc_short")
	private String Whats_new_desc_short;
	@TableField(columnName="Version")
	private String Version;
	@TableField(columnName="ClassifyName")
	private String ClassifyName;
	@TableField(columnName="ReURL")
	private String ReURL;
	@TableField(columnName="OS_type")
	private String OS_type;
	public String getConfigureId() {
		return ConfigureId;
	}
	public void setConfigureId(String configureId) {
		ConfigureId = configureId;
	}
	public String getSoftId() {
		return SoftId;
	}
	public void setSoftId(String softId) {
		SoftId = softId;
	}
	public String getSoftScore() {
		return SoftScore;
	}
	public void setSoftScore(String softScore) {
		SoftScore = softScore;
	}
	public String getLogo() {
		return Logo;
	}
	public void setLogo(String logo) {
		Logo = logo;
	}
	public String getLogo48() {
		return Logo48;
	}
	public void setLogo48(String logo48) {
		Logo48 = logo48;
	}
	public String getLogo96() {
		return Logo96;
	}
	public void setLogo96(String logo96) {
		Logo96 = logo96;
	}
	public String getOffical_website() {
		return Offical_website;
	}
	public void setOffical_website(String offical_website) {
		Offical_website = offical_website;
	}
	public String getSoft_desc() {
		return Soft_desc;
	}
	public void setSoft_desc(String soft_desc) {
		Soft_desc = soft_desc;
	}
	public String getSoft_desc_short() {
		return Soft_desc_short;
	}
	public void setSoft_desc_short(String soft_desc_short) {
		Soft_desc_short = soft_desc_short;
	}
	public String getNick_version() {
		return Nick_version;
	}
	public void setNick_version(String nick_version) {
		Nick_version = nick_version;
	}
	public String getWhats_new_desc() {
		return whats_new_desc;
	}
	public void setWhats_new_desc(String whats_new_desc) {
		this.whats_new_desc = whats_new_desc;
	}
	
	public String getWhats_new_desc_short() {
		return Whats_new_desc_short;
	}
	public void setWhats_new_desc_short(String whats_new_desc_short) {
		Whats_new_desc_short = whats_new_desc_short;
	}
	public String getVersion() {
		return Version;
	}
	public void setVersion(String version) {
		Version = version;
	}
	public String getClassifyName() {
		return ClassifyName;
	}
	public void setClassifyName(String classifyName) {
		ClassifyName = classifyName;
	}
	public String getReURL() {
		return ReURL;
	}
	public void setReURL(String reURL) {
		ReURL = reURL;
	}
	public String getOS_type() {
		return OS_type;
	}
	public void setOS_type(String oS_type) {
		OS_type = oS_type;
	}
	
}	
