package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.RobotPersistence;

public interface RobotPersistenceMapper extends IBaseDao<RobotPersistence, String>{
	/*
	 * robot_ajax获取机器人信息
	 */
	@Select("SELECT * FROM robot")
	public List<RobotPersistence> robotinfo();
}
