package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.LogPersistenceMapper;
import org.xjtusicd3.database.mapper.RolePersistenceMapper;
import org.xjtusicd3.database.model.LogPersistence;
import org.xjtusicd3.database.model.RolePersistence;

public class RoleHelper
{
	/*
	 * zpz_get info of role
	 */
	public static List<RolePersistence> getRole()
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePersistenceMapper mapper = session.getMapper(RolePersistenceMapper.class);
		List<RolePersistence> role = mapper.getAllRoleInfo();
		session.close();
		return role;
	}
	/*
	 * zpz_get role Name
	 */
	public static String getRoleName(String Rid)
	{
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RolePersistenceMapper mapper = session.getMapper(RolePersistenceMapper.class);
		String role = mapper.getRoleNameByRoleId(Rid);
		session.close();
		return role;
	}
}
