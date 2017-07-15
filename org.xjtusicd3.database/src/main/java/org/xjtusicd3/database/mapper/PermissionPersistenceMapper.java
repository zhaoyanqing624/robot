package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.model.PermissionPersistence;

public interface PermissionPersistenceMapper
{
	@Select("SELECT * FROM TBL_Permission WHERE PERMISSIONID=#{0}")
	public PermissionPersistence getPermission(String PermissionId);
}
