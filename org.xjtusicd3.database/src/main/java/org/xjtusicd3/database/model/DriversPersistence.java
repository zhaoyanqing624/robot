package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;
@Table(tablename="Driver")
public class DriversPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="ConfigureId")
	private String ConfigureId;
	@TableField(columnName="DriversOS")
	private String DriversOS;
	@TableField(columnName="DriversClassify")
	private String DriversClassify;
	@TableField(columnName="DriversFitness")
	private String DriversFitness;
	@TableField(columnName="DriversContent")
	private String DriversContent;
	public String getConfigureId() {
		return ConfigureId;
	}
	public void setConfigureId(String configureId) {
		ConfigureId = configureId;
	}
	public String getDriversOS() {
		return DriversOS;
	}
	public void setDriversOS(String driversOS) {
		DriversOS = driversOS;
	}
	public String getDriversClassify() {
		return DriversClassify;
	}
	public void setDriversClassify(String driversClassify) {
		DriversClassify = driversClassify;
	}
	public String getDriversFitness() {
		return DriversFitness;
	}
	public void setDriversFitness(String driversFitness) {
		DriversFitness = driversFitness;
	}
	public String getDriversContent() {
		return DriversContent;
	}
	public void setDriversContent(String driversContent) {
		DriversContent = driversContent;
	}
	
	
}
