package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.RolePersistence;

public interface RolePersistenceMapper	extends IBaseDao<RolePersistence, String>
{
	//zpz_获取所有角色
		@Select("SELECT * FROM TBL_Role")
		List<RolePersistence> getAllRoleInfo();
		
		//zpz_get role name by RoleId
		@Select("SELECT ROLENAME FROM TBL_Role WHERE ROLEID = #{0}")
		String getRoleNameByRoleId(String Rid);
}
