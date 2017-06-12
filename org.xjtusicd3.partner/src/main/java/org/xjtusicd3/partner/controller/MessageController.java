package org.xjtusicd3.partner.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.service.CommunityService;
import org.xjtusicd3.partner.view.Question_CommunityView;

import com.alibaba.fastjson.JSONObject;

@Controller
public class MessageController {
	@RequestMapping(value="main",method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String messageIndexPage() {
		return "liuyan/index";
	}
	
	/*
	 * zyq_message_ajax_获得用户名
	 */
	@ResponseBody
	@RequestMapping(value={"/getUserName"},method={org.springframework.web.bind.annotation.RequestMethod.GET},produces="text/plain;charset=UTF-8")
	public String getUserName(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		List<UserPersistence> list = UserHelper.getEmail(useremail);
		String result = JsonUtil.toJsonString(list);
		return result;
	}
	/*
	 * zyq_message_ajax_获得用户基本信息
	 */
	@ResponseBody
	@RequestMapping(value={"/getUserInfo"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String getUserInfo(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String username = request.getParameter("username");
		List<UserPersistence> list = UserHelper.getUserInfo(username);
		String result = JsonUtil.toJsonString(list);
		return result;
	}
	/*
	 * zyq_notice_消息通知
	 */
	@RequestMapping(value="notice",method=RequestMethod.GET)
	public ModelAndView notice(HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String userid = (String) session.getAttribute("UserId");
		if (useremail==null) {
			return new ModelAndView("login");
		}else {
			ModelAndView mv = new ModelAndView("notice");
			mv.addObject("uid", userid);
			return mv;
		}
	}
	/*
	 * zyq_ajax_更改消息通知的被查阅后的状态
	 */
	@ResponseBody
	@RequestMapping(value={"/updateNotice"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String updateNotice(HttpServletRequest request,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		System.out.println(type + id);
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
		return null;
	}
}
