package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;
@Table(tablename="drivers")
public class DriversSpiderPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="driverId")
	private int driverId;
	@TableField(columnName="driverName")
	private String driverName;
	@TableField(columnName="driverOs")
	private String driverOs;
	@TableField(columnName="driverProducer")
	private String driverProducer;
	@TableField(columnName="driverDate")
	private String driverDate;
	@TableField(columnName="driverSize")
	private String driverSize;
	@TableField(columnName="driverClassify")
	private String driverClassify;
	@TableField(columnName="driverFitness")
	private String driverFitness;
	@TableField(columnName="driverContent")
	private String driverContent;
	@TableField(columnName="driverUrl")
	private String driverUrl;
	public int getDriverId() {
		return driverId;
	}
	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverOs() {
		return driverOs;
	}
	public void setDriverOs(String driverOs) {
		this.driverOs = driverOs;
	}
	public String getDriverProducer() {
		return driverProducer;
	}
	public void setDriverProducer(String driverProducer) {
		this.driverProducer = driverProducer;
	}
	public String getDriverDate() {
		return driverDate;
	}
	public void setDriverDate(String driverDate) {
		this.driverDate = driverDate;
	}
	public String getDriverSize() {
		return driverSize;
	}
	public void setDriverSize(String driverSize) {
		this.driverSize = driverSize;
	}
	public String getDriverClassify() {
		return driverClassify;
	}
	public void setDriverClassify(String driverClassify) {
		this.driverClassify = driverClassify;
	}
	public String getDriverFitness() {
		return driverFitness;
	}
	public void setDriverFitness(String driverFitness) {
		this.driverFitness = driverFitness;
	}
	public String getDriverContent() {
		return driverContent;
	}
	public void setDriverContent(String driverContent) {
		this.driverContent = driverContent;
	}
	public String getDriverUrl() {
		return driverUrl;
	}
	public void setDriverUrl(String driverUrl) {
		this.driverUrl = driverUrl;
	}
	
	
}
