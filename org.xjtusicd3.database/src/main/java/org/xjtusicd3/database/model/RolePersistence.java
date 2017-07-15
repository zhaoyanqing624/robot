package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_Role")
public class RolePersistence
{
	@TableKey(strategy = Strategy.NORMAL)
	@TableField(columnName="ROLEID")
	private String RoleId;
	@TableField(columnName="ROLENAME")
	private String RoleName;
	public String getRoleId()
	{
		return RoleId;
	}
	public void setRoleId(String roleId)
	{
		RoleId = roleId;
	}
	public String getRoleName()
	{
		return RoleName;
	}
	public void setRoleName(String roleName)
	{
		RoleName = roleName;
	}
}
