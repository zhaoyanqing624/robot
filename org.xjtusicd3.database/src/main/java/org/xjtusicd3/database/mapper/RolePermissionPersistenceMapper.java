package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.PermissionPersistence;
import org.xjtusicd3.database.model.RolePermissionPersistence;

public interface RolePermissionPersistenceMapper extends IBaseDao<RolePermissionPersistence, String>
{
	/*
	 * 多表联合查询获取到角色权限信息
	 */
//	@Select("SELECT TBL_Permission.PERMISSIONPHYSICALNAME,TBL_Permission.PERMISSIONLOGICNAME FROM TBL_User, TBL_Role_Permission INNER JOIN TBL_Permission ON TBL_Role_Permission.PERMISSIONID = TBL_Permission.PERMISSIONID WHERE TBL_User.USERID = #{0} AND TBL_User.ROLEID = TBL_Role_Permission.ROLEID")
//	public List<RolePermissionPersistence> getRolePermission(String Uid);
	/*
	 * 多表联合查询获取到角色权限信息
	 */
	@Select("SELECT TBL_Permission.* FROM TBL_User, TBL_Role_Permission INNER JOIN TBL_Permission ON TBL_Role_Permission.PERMISSIONID = TBL_Permission.PERMISSIONID WHERE TBL_User.USERID = #{0} AND TBL_User.ROLEID = TBL_Role_Permission.ROLEID")
	public List<PermissionPersistence> getRolePermissionByUId(String Uid);
	
	@Select("SELECT * FROM TBL_Role_Permission")
	public List<RolePermissionPersistence> getAllRolePermission();
	//search one kind of data and group them
	@Select("SELECT ROLEID FROM TBL_Role_Permission GROUP BY ROLEID")
	public List<String> getAllRoleId();
	//get permission by roleId
	@Select("SELECT p.PERMISSIONPHYSICALNAME FROM TBL_Permission p LEFT JOIN TBL_Role_Permission r ON p.PERMISSIONID = r.PERMISSIONID WHERE r.ROLEID = #{0}")
	public List<PermissionPersistence> getRolePermissionByRId(String Rid);
	//another kind of sql language 
	@Select("SELECT  *  FROM TBL_Permission P WHERE P.PERMISSIONID IN (SELECT r.PERMISSIONID 	FROM TBL_Role_Permission r 	WHERE r.ROLEID = 'ad36e57d-fb18-4fb9-8ce0-a9e72bf3fa4e'")
	public List<RolePermissionPersistence> getRolePermissionById2(String RId);

}
