package org.xjtusicd3.partner.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;

@Controller
public class RobotController {
	/*
	 * robot_ajax获取机器人信息
	 */
	@ResponseBody
	@RequestMapping(value={"/getRobotInfo"},method={org.springframework.web.bind.annotation.RequestMethod.GET},produces="text/plain;charset=UTF-8")
	public  String RobotInfo(HttpServletResponse response){
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName1();
		if (classifyPersistences == null || classifyPersistences.size()==0) {
			return null;
		}			
		String result = JsonUtil.toJsonString(classifyPersistences);
		return result;
	 }
}
