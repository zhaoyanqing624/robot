package org.xjtusicd3.database.helper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.xjtusicd3.database.logic.SqlSessionManager;
import org.xjtusicd3.database.mapper.RobotPersistenceMapper;
import org.xjtusicd3.database.model.RobotPersistence;

public class RobotHelper {
	/*
	 * robot_ajax获取机器人信息
	 */
	public static List<RobotPersistence> robotinfo(){
		SqlSession session = SqlSessionManager.getSqlSessionFactory().openSession(true);
		RobotPersistenceMapper mapper = session.getMapper(RobotPersistenceMapper.class);
		List<RobotPersistence> list = mapper.robotinfo();
		session.close();
		return list;
	}
}
