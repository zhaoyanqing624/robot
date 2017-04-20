package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="Robot")
public class RobotPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="RobotId")
	private String RobotId;
	@TableField(columnName="RobotName")
	private String RobotName;
	@TableField(columnName="RobotWelcome")
	private String RobotWelcome;
	@TableField(columnName="RobotDescription")
	private String RobotDescription;
	@TableField(columnName="RobotImage")
	private String RobotImage;
	@TableField(columnName="RobotNoknow")
	private String RobotNoknow;
	public String getRobotId() {
		return RobotId;
	}
	public void setRobotId(String robotId) {
		RobotId = robotId;
	}
	public String getRobotName() {
		return RobotName;
	}
	public void setRobotName(String robotName) {
		RobotName = robotName;
	}
	public String getRobotWelcome() {
		return RobotWelcome;
	}
	public void setRobotWelcome(String robotWelcome) {
		RobotWelcome = robotWelcome;
	}
	public String getRobotDescription() {
		return RobotDescription;
	}
	public void setRobotDescription(String robotDescription) {
		RobotDescription = robotDescription;
	}
	public String getRobotImage() {
		return RobotImage;
	}
	public void setRobotImage(String robotImage) {
		RobotImage = robotImage;
	}
	public String getRobotNoknow() {
		return RobotNoknow;
	}
	public void setRobotNoknow(String robotNoknow) {
		RobotNoknow = robotNoknow;
	}

	
	
}
