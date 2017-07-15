package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.AdvisePersistenceMapper;
import org.xjtusicd3.database.mapper.RolePermissionPersistenceMapper;
import org.xjtusicd3.database.model.AdvisePersistence;
import org.xjtusicd3.database.model.PermissionPersistence;
import org.xjtusicd3.database.model.RolePermissionPersistence;

public class RolePermissionHelper
{
	/*
	 * zpz_get role permission By UserId
	 */
	public static List<PermissionPersistence> getRolePermission(String uId)
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePermissionPersistenceMapper mapper = session.getMapper(RolePermissionPersistenceMapper.class);
		List<PermissionPersistence> rolePermission = mapper.getRolePermissionByUId(uId);
		session.close();
		return rolePermission;
	}
	
	/*
	 * zpz_get role permission By UserId
	 */
	public static List<PermissionPersistence> getRolePermissionByRId(String rId)
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePermissionPersistenceMapper mapper = session.getMapper(RolePermissionPersistenceMapper.class);
		List<PermissionPersistence> rolePermission = mapper.getRolePermissionByRId(rId);
		session.close();
		return rolePermission;
	}
	
	/*
	 * zpz_get all role Id
	 */
	public static List<String> getAllRoleId()
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePermissionPersistenceMapper mapper = session.getMapper(RolePermissionPersistenceMapper.class);
		List<String> roleId = mapper.getAllRoleId();
		session.close();
		return roleId;
	}
}
