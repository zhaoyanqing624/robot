package org.xjtusicd3.partner.service;

import java.util.List;

import org.xjtusicd3.database.helper.RobotHelper;
import org.xjtusicd3.database.model.RobotPersistence;

public class RobotService {
	/*
	 * robot_ajax获取机器人信息
	 */
	public static List<RobotPersistence> robotinfo(){
		List<RobotPersistence> list = RobotHelper.robotinfo();
		return list;
	}
}
