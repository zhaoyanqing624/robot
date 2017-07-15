package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Permission")
public class PermissionPersistence
{
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="PERMISSIONID")
	private String PERMISSIONID;
	@TableField(columnName="PERMISSIONPHYSICALNAME")
	private String PERMISSIONPHYSICALNAME;
	@TableField(columnName="PERMISSIONLOGICNAME")
	private String PERMISSIONLOGICNAME;
	public String getPERMISSIONID()
	{
		return PERMISSIONID;
	}
	public void setPERMISSIONID(String pERMISSIONID)
	{
		PERMISSIONID = pERMISSIONID;
	}
	public String getPERMISSIONPHYSICALNAME()
	{
		return PERMISSIONPHYSICALNAME;
	}
	public void setPERMISSIONPHYSICALNAME(String pERMISSIONPHYSICALNAME)
	{
		PERMISSIONPHYSICALNAME = pERMISSIONPHYSICALNAME;
	}
	public String getPERMISSIONLOGICNAME()
	{
		return PERMISSIONLOGICNAME;
	}
	public void setPERMISSIONLOGICNAME(String pERMISSIONLOGICNAME)
	{
		PERMISSIONLOGICNAME = pERMISSIONLOGICNAME;
	}
	
}
