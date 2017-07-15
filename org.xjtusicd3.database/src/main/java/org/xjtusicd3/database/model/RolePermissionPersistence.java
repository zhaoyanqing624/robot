package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Role_Permission")
public class RolePermissionPersistence
{
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="ROLEPERMISSIONID")
	private String ROLEPERMISSIONID;
	@TableField(columnName="ROLEID")
	private String ROLEID;
	@TableField(columnName="PERMISSIONID")
	private String PERMISSIONID;
	public String getROLEPERMISSIONID()
	{
		return ROLEPERMISSIONID;
	}
	public void setROLEPERMISSIONID(String rOLEPERMISSIONID)
	{
		ROLEPERMISSIONID = rOLEPERMISSIONID;
	}
	public String getROLEID()
	{
		return ROLEID;
	}
	public void setROLEID(String rOLEID)
	{
		ROLEID = rOLEID;
	}
	public String getPERMISSIONID()
	{
		return PERMISSIONID;
	}
	public void setPERMISSIONID(String pERMISSIONID)
	{
		PERMISSIONID = pERMISSIONID;
	}
	
}
