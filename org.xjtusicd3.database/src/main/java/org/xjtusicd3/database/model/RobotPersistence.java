package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="robot")
public class RobotPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="robotId")
	private int robotId;
	@TableField(columnName="robotName")
	private String robotName;
	@TableField(columnName="robotWelcome")
	private String robotWelcome;
	@TableField(columnName="robotDescription")
	private String robotDescription;
	@TableField(columnName="robotImage")
	private String robotImage;
	@TableField(columnName="robotNoknow")
	private String robotNoknow;
	public int getRobotId() {
		return robotId;
	}
	public void setRobotId(int robotId) {
		this.robotId = robotId;
	}
	public String getRobotName() {
		return robotName;
	}
	public void setRobotName(String robotName) {
		this.robotName = robotName;
	}
	public String getRobotWelcome() {
		return robotWelcome;
	}
	public void setRobotWelcome(String robotWelcome) {
		this.robotWelcome = robotWelcome;
	}
	public String getRobotDescription() {
		return robotDescription;
	}
	public void setRobotDescription(String robotDescription) {
		this.robotDescription = robotDescription;
	}
	public String getRobotImage() {
		return robotImage;
	}
	public void setRobotImage(String robotImage) {
		this.robotImage = robotImage;
	}
	public String getRobotNoknow() {
		return robotNoknow;
	}
	public void setRobotNoknow(String robotNoknow) {
		this.robotNoknow = robotNoknow;
	}
	
	
}
