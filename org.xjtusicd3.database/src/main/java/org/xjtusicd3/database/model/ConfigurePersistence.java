package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="Configure")
public class ConfigurePersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="ConfigureId")
	private String ConfigureId;
	@TableField(columnName="ConfigureName")
	private String ConfigureName;
	@TableField(columnName="ConfigureType")
	private String ConfigureType;
	@TableField(columnName="ConfigureProducer")
	private String ConfigureProducer;
	@TableField(columnName="ConfigureDate")
	private String ConfigureDate;
	@TableField(columnName="ConfigureURL")
	private String ConfigureURL;
	@TableField(columnName="ConfigureSize")
	private String ConfigureSize;
	public String getConfigureId() {
		return ConfigureId;
	}
	public void setConfigureId(String configureId) {
		ConfigureId = configureId;
	}
	public String getConfigureName() {
		return ConfigureName;
	}
	public void setConfigureName(String configureName) {
		ConfigureName = configureName;
	}
	public String getConfigureType() {
		return ConfigureType;
	}
	public void setConfigureType(String configureType) {
		ConfigureType = configureType;
	}
	public String getConfigureProducer() {
		return ConfigureProducer;
	}
	public void setConfigureProducer(String configureProducer) {
		ConfigureProducer = configureProducer;
	}
	public String getConfigureDate() {
		return ConfigureDate;
	}
	public void setConfigureDate(String configureDate) {
		ConfigureDate = configureDate;
	}
	public String getConfigureURL() {
		return ConfigureURL;
	}
	public void setConfigureURL(String configureURL) {
		ConfigureURL = configureURL;
	}
	public String getConfigureSize() {
		return ConfigureSize;
	}
	public void setConfigureSize(String configureSize) {
		ConfigureSize = configureSize;
	}
	
	
}
