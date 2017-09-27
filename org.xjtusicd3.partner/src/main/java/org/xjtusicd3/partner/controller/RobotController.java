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
import org.xjtusicd3.partner.service.RobotService;
import org.xjtusicd3.partner.view.robot_Chat;

import com.alibaba.fastjson.JSONObject;

@Controller
public class RobotController {
	/*
	 * robot_ajax获取机器人信息
	 */
	@ResponseBody
	@RequestMapping(value={"/getRobotInfo"},method={org.springframework.web.bind.annotation.RequestMethod.GET},produces="text/plain;charset=UTF-8")
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
	public String ChatWithRobot(HttpServletRequest request, HttpSession session) throws Exception{
		String comment = request.getParameter("comment");
		String useremail = (String) session.getAttribute("UserEmail");
		JSONObject jsonObject = new JSONObject();
		List<robot_Chat> robot_Chats = RobotService.getRobotAnswer(comment);
		List<RobotPersistence> robotPersistences = RobotHelper.robotinfo();
		jsonObject.put("value", "1");
		jsonObject.put("robotChat", robot_Chats);
		jsonObject.put("robotInfo", robotPersistences);
		if (useremail!=null) {
			List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
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
	public String questionSkill(HttpSession session,HttpServletResponse response){
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		List<RobotPersistence> robotPersistences = RobotHelper.robotinfo();
		jsonObject.put("robotInfo", robotPersistences);
		if (username==null) {
			jsonObject.put("value", "0");
		}else {
			jsonObject.put("value", "1");
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			jsonObject.put("robotUser", userPersistences);
		}
		String result = JsonUtil.toJsonString(jsonObject);
		return result;
	 }
}
