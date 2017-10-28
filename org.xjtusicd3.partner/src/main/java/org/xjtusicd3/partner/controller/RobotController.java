package org.xjtusicd3.partner.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.RobotHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.RobotPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.database.model.UserQuestionPersistence;
import org.xjtusicd3.partner.annotation.SystemControllerLog;
import org.xjtusicd3.partner.service.RobotService;
import org.xjtusicd3.partner.service.UserQuestionService;
import org.xjtusicd3.partner.view.robot_Chat;

import com.alibaba.fastjson.JSONObject;

@Controller
public class RobotController {
	/*
	 * robot_ajax_获取机器人信息
	 */
	@ResponseBody
	@RequestMapping(value={"/getRobotInfo"},method={org.springframework.web.bind.annotation.RequestMethod.GET},produces="text/plain;charset=UTF-8")
	@SystemControllerLog(description = "获取机器人信息")
	public String RobotInfo(HttpServletResponse response){
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		List<RobotPersistence> list = RobotService.robotinfo();
		String result = JsonUtil.toJsonString(list);
		return result;
	 }
	/*
	 * robot_ajax_和机器人聊天
	 */
	@ResponseBody
	@RequestMapping(value={"/chatWithRobot"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "与机器人聊天")
	public String ChatWithRobot(HttpServletRequest request, HttpSession session) throws Exception{
		//记录前台用户提问
		String comment = request.getParameter("comment");
		String username = (String) session.getAttribute("UserName");
		//zzl_记录用户提问记录_2017年10月22日11:23:00
		UserQuestionService.addUserQuestion(username,comment);
	
		JSONObject jsonObject = new JSONObject();
		//获取问题的答案
		List<robot_Chat> robot_Chats = RobotService.getRobotAnswer(comment);
		System.out.println("aaaaa机器人聊天回复："+robot_Chats.size());
		
		List<RobotPersistence> robotPersistences = RobotHelper.robotinfo();
		jsonObject.put("value", "1");
		jsonObject.put("robotChat", robot_Chats);
		jsonObject.put("robotInfo", robotPersistences);
		if (username!=null) {
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			jsonObject.put("robotUser", userPersistences);
		}else {
			List<UserPersistence> userPersistences = new ArrayList<UserPersistence>();
			jsonObject.put("robotUser", userPersistences);
		}
		String result = JsonUtil.toJsonString(jsonObject);
		return result;
	}
	/**
	 * author:zhaoyanqing
	 * abstract:robot页面 点击“提问技巧”
	 * data:2017年9月17日 23:09:59
	 */
	@ResponseBody
	@RequestMapping(value={"/questionSkill"},method={org.springframework.web.bind.annotation.RequestMethod.GET},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "提问技巧")
	public String questionSkill(HttpSession session,HttpServletResponse response){
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		
		//获取机器人信息
		List<RobotPersistence> robotPersistences = RobotHelper.robotinfo();
		jsonObject.put("robotInfo", robotPersistences);
		//用户名为空返回 0，不空返回 1
		if (username==null) {
			jsonObject.put("value", "0");
		}else {
			jsonObject.put("value", "1");
			//获取用户信息
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			jsonObject.put("robotUser", userPersistences);
		}
		String result = JsonUtil.toJsonString(jsonObject);
		return result;
	 }
}
