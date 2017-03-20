package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;
@Table(tablename="soft")
public class SoftSpiderPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="soft_name")
	private String soft_name;
	@TableField(columnName="point")
	private int point;
	@TableField(columnName="logo")
	private String logo;
	@TableField(columnName="logo48")
	private String logo48;
	@TableField(columnName="logo96")
	private String logo96;
	@TableField(columnName="official_web")
	private String official_web;
	@TableField(columnName="soft_desc")
	private String soft_desc;
	@TableField(columnName="soft_desc_short")
	private String soft_desc_short;
	@TableField(columnName="nick_version")
	private String nick_version;
	@TableField(columnName="whats_new_desc")
	private String whats_new_desc;
	@TableField(columnName="whats_new_short")
	private String whats_new_short;
	@TableField(columnName="version")
	private String version;
	@TableField(columnName="file_size")
	private String file_size;
	@TableField(columnName="file_name")
	private String file_name;
	@TableField(columnName="update_time")
	private String update_time;
	@TableField(columnName="url")
	private String url;
	@TableField(columnName="pic_path")
	private String pic_path;
	@TableField(columnName="class_name")
	private String class_name;
	@TableField(columnName="reurl")
	private String reurl;
	@TableField(columnName="developer_name")
	private String developer_name;
	@TableField(columnName="download_num")
	private String download_num;
	@TableField(columnName="os_type")
	private String os_type;
	public String getSoft_name() {
		return soft_name;
	}
	public void setSoft_name(String soft_name) {
		this.soft_name = soft_name;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getLogo48() {
		return logo48;
	}
	public void setLogo48(String logo48) {
		this.logo48 = logo48;
	}
	public String getLogo96() {
		return logo96;
	}
	public void setLogo96(String logo96) {
		this.logo96 = logo96;
	}
	public String getOfficial_web() {
		return official_web;
	}
	public void setOfficial_web(String official_web) {
		this.official_web = official_web;
	}
	public String getSoft_desc() {
		return soft_desc;
	}
	public void setSoft_desc(String soft_desc) {
		this.soft_desc = soft_desc;
	}
	public String getSoft_desc_short() {
		return soft_desc_short;
	}
	public void setSoft_desc_short(String soft_desc_short) {
		this.soft_desc_short = soft_desc_short;
	}
	public String getNick_version() {
		return nick_version;
	}
	public void setNick_version(String nick_version) {
		this.nick_version = nick_version;
	}
	public String getWhats_new_desc() {
		return whats_new_desc;
	}
	public void setWhats_new_desc(String whats_new_desc) {
		this.whats_new_desc = whats_new_desc;
	}
	public String getWhats_new_short() {
		return whats_new_short;
	}
	public void setWhats_new_short(String whats_new_short) {
		this.whats_new_short = whats_new_short;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getFile_size() {
		return file_size;
	}
	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPic_path() {
		return pic_path;
	}
	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	public String getReurl() {
		return reurl;
	}
	public void setReurl(String reurl) {
		this.reurl = reurl;
	}
	public String getDeveloper_name() {
		return developer_name;
	}
	public void setDeveloper_name(String developer_name) {
		this.developer_name = developer_name;
	}
	public String getDownload_num() {
		return download_num;
	}
	public void setDownload_num(String download_num) {
		this.download_num = download_num;
	}
	public String getOs_type() {
		return os_type;
	}
	public void setOs_type(String os_type) {
		this.os_type = os_type;
	}
	
	
	
}	
