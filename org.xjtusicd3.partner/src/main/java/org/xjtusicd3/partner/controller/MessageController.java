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
import org.xjtusicd3.database.helper.MessageHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.MessageHistoryPersistence;
import org.xjtusicd3.database.model.MessagePersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.service.MessageService;
import org.xjtusicd3.partner.service.NoticeService;
import org.xjtusicd3.partner.view.Message_MessageView;
import org.xjtusicd3.partner.view.Notice_NoticeCommunityView;

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
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		List<UserPersistence> list = UserHelper.getUserInfo(username);
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
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String userid = (String) session.getAttribute("UserId");
		if (username==null) {
			return new ModelAndView("login");
		}else {
			ModelAndView mv = new ModelAndView("notice");
			List<Notice_NoticeCommunityView> notice_NoticeCommunityViews = NoticeService.notice_NoticeViews(userid,1);
			List<Notice_NoticeCommunityView> notice_NoticeCommunityViews2 = NoticeService.notice_NoticeViews(userid,2);
			mv.addObject("uid", userid);
			mv.addObject("secondList", notice_NoticeCommunityViews);
			mv.addObject("thirdList", notice_NoticeCommunityViews2);
			System.out.println(JsonUtil.toJsonString(notice_NoticeCommunityViews2));
			System.out.println(JsonUtil.toJsonString(notice_NoticeCommunityViews));
			return mv;
		}
	}
	@RequestMapping(value="notice2",method=RequestMethod.GET)
	public ModelAndView notice2(HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String userid = (String) session.getAttribute("UserId");
		if (username==null) {
			return new ModelAndView("login");
		}else {
			ModelAndView mv = new ModelAndView("notice2");
			List<Notice_NoticeCommunityView> notice_NoticeCommunityViews = NoticeService.notice_NoticeViews(userid,1);
			List<Notice_NoticeCommunityView> notice_NoticeCommunityViews2 = NoticeService.notice_NoticeViews(userid,2);
			mv.addObject("uid", userid);
			mv.addObject("secondList", notice_NoticeCommunityViews);
			mv.addObject("thirdList", notice_NoticeCommunityViews2);
			return mv;
		}
	}
	/*
	 * zyq_ajax_更改消息通知的被查阅后的状态
	 */
	@ResponseBody
	@RequestMapping(value={"/updateNotice"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String updateNotice(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String type = request.getParameter("type");
		String type2 = request.getParameter("type2");
		String id = request.getParameter("id");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			NoticeService.updateNotice(id,type,type2);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_ajax_把列表的消息通知删除
	 */
	@ResponseBody
	@RequestMapping(value={"/deleteNotice"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String deleteNotice(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String type = request.getParameter("type");
		String type2 = request.getParameter("type2");
		String id = request.getParameter("id");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			NoticeService.deleteNotice(id,type,type2);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_message_消息通知
	 */
	@RequestMapping(value="message",method=RequestMethod.GET)
	public ModelAndView message(String u,HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String userid = (String) session.getAttribute("UserId");
		if (username==null) {
			return new ModelAndView("login");
		}else {
			ModelAndView mv = new ModelAndView("message");
			if (u==null) {
				List<Message_MessageView> message_MessageViews = MessageService.message_userList(userid);
				mv.addObject("messageList", message_MessageViews);
			}else{
				List<UserPersistence> userPersistences = UserHelper.getEmail_id(u);
				List<Message_MessageView> message_MessageViews = MessageService.message_userList(userid);
				if (message_MessageViews.size()==0) {
					mv.addObject("touserList", userPersistences);
				}else{
					for(Message_MessageView message_MessageView:message_MessageViews){
						if (message_MessageView.getUserId().contains(u)) {
							mv.addObject("messageList", message_MessageViews);
						}else {
							mv.addObject("touserList", userPersistences);
							mv.addObject("messageList", message_MessageViews);
						}
					}
				}

			}
			mv.addObject("uid", userid);
			return mv;
		}
	}
	/*
	 * zyq_message_ajax_发送私信
	 */
	@ResponseBody
	@RequestMapping(value={"/saveMessage"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String saveMessage(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String content = request.getParameter("content");
		String touserId = request.getParameter("touserId");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			Message_MessageView message_MessageViews = MessageService.message_MessageView(username, touserId, content);
			jsonObject.put("value", "1");
			jsonObject.put("messageList", message_MessageViews);
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	/*
	 * zyq_message_ajax_查询私信内容
	 */
	@ResponseBody
	@RequestMapping(value={"/getMessage"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getMessage(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String postuserId = request.getParameter("touserId");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			//查看是否之前关闭过聊天
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			List<MessageHistoryPersistence> historyPersistences = MessageHelper.getMessageHistoryList(postuserId, userPersistences.get(0).getUSERID());
			if (historyPersistences.size()==0) {
				List<Message_MessageView> message_MessageViews = MessageService.message_getMessage(postuserId,username);
				List<MessagePersistence> mList = MessageHelper.getMessageContent1(postuserId, userPersistences.get(0).getUSERID(), 2);
				if (mList.size()!=0) {
					List<Message_MessageView> message_MessageViews2 = MessageService.message_getMessageHistory(postuserId, userPersistences.get(0).getUSERID(), 2, 0);
					jsonObject.put("messageHistory", message_MessageViews2);
					String string = JsonUtil.toJsonString(message_MessageViews2);
					System.out.println(string);
				}
				if (mList.size()<5) {
					jsonObject.put("isMore", "0");
				}else {
					jsonObject.put("isMore", "1");
				}
				jsonObject.put("value", "1");
				jsonObject.put("messageContentList", message_MessageViews);
				String result = JsonUtil.toJsonString(jsonObject);
				return result;
			}else {
				List<Message_MessageView> message_MessageViews = MessageService.message_getMessage(postuserId,username);
				List<MessagePersistence> mList = MessageHelper.getMessageContent1_time(postuserId, userPersistences.get(0).getUSERID(), 2,historyPersistences.get(0).getTIMEMARK());
				if (mList.size()!=0) {
					List<Message_MessageView> message_MessageViews2 = MessageService.message_getMessageHistory_time(postuserId, userPersistences.get(0).getUSERID(), 2, 0,historyPersistences.get(0).getTIMEMARK());
					jsonObject.put("messageHistory", message_MessageViews2);
					String string = JsonUtil.toJsonString(message_MessageViews2);
					System.out.println(string);
				}
				if (mList.size()<5) {
					jsonObject.put("isMore", "0");
				}else {
					jsonObject.put("isMore", "1");
				}
				jsonObject.put("value", "1");
				jsonObject.put("messageContentList", message_MessageViews);
				String result = JsonUtil.toJsonString(jsonObject);
				return result;
			}

		}
	}
	/*
	 * zyq_message_ajax_获取更多私信的历史记录
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreMessageHistory"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getMoreMessageHistory(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String date = request.getParameter("date");
		String postuserId = request.getParameter("touserId");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			List<MessagePersistence> mList = MessageHelper.getMessageContent11(postuserId, userPersistences.get(0).getUSERID(), 2,date);
			if (mList.size()!=0) {
				List<Message_MessageView> message_MessageViews2 = MessageService.message_getMessageHistory2(postuserId, userPersistences.get(0).getUSERID(), 2, date);
				jsonObject.put("messageHistory", message_MessageViews2);
				
			}
			if (mList.size()<5) {
				jsonObject.put("isMore", "0");
			}else {
				jsonObject.put("isMore", "1");
			}
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
	
	/*
	 * zyq_message_ajax_删除私信列表
	 */
	@ResponseBody
	@RequestMapping(value={"/deleteMessageList"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String deleteMessageList(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String postuserId = request.getParameter("id");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			MessageService.deleteMessageList(postuserId,userPersistences.get(0).getUSERID());
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
}
