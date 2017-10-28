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
import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.ITHelper;
import org.xjtusicd3.database.helper.ShareHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.ITPersistence;
import org.xjtusicd3.database.model.SharePersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.annotation.SystemControllerLog;
import org.xjtusicd3.partner.service.CommunityService;
import org.xjtusicd3.partner.view.Question2_CommunityView;
import org.xjtusicd3.partner.view.Question_CommunityView;

import com.alibaba.fastjson.JSONObject;

@Controller
public class CommunityController {
	/*
	 * zyq_question_右侧类别
	 */
	@RequestMapping(value="question",method=RequestMethod.GET)
	@SystemControllerLog(description = "社区主页显示")
	public ModelAndView question(String c,String type,HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		System.out.println("c:"+c);
		System.out.println("type:"+type);
		String username = (String) session.getAttribute("UserName");
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.classifyName1();
		List<Question_CommunityView> question_CommunityViews = CommunityService.Question_CommunityView(username,0,type,c);
		System.out.println("CommunityController:" + question_CommunityViews.size());
		ModelAndView mv = new ModelAndView("question");
		mv.addObject("classifyList", classifyPersistences);
		mv.addObject("communityViews", question_CommunityViews);
		String typename = "";
		if (type.equals("all")) {
			typename="全部";
		}else if (type.equals("1")) {
			typename="已解决";
		}else if (type.equals("2")) {
			typename="待回答";
		}
		mv.addObject("typename", typename);
		mv.addObject("userName", username);
		String urlPath="";
		if (request.getQueryString()==null) {
			urlPath = request.getServletPath();
		}else {
			urlPath = request.getServletPath()+"?"+request.getQueryString().toString();
		}
		session.setAttribute("urlPath", urlPath);
		return mv;
	}
	/*
	 * zyq_question_ajax_获取更多问题
	 * 
	 * 
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreCommunity"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "社区主页显示更多")
	public String getMoreCommunity(HttpServletRequest request,HttpSession session)
	{	
		System.out.println("获取更多问题");
		/* 获得前台参数  */
		// 用户名用于判断是否登录
		String username = (String) session.getAttribute("UserName");
		// 已经加载的数目
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		// 全部 = all、 已解决 = 1、待解决 = 2
		String type = request.getParameter("type");
		// 具体问题分类
		String c = request.getParameter("c");
		
		
		JSONObject jsonObject = new JSONObject();
		if (username==null) 
		{
			// 未登录  返回登录界面
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
		else
		{
			// 获得新的 5 条
			List<Question_CommunityView> question_CommunityViews = CommunityService.Question_CommunityView(username,startnumber,type,c);
			// 返回对应的
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.question_ClassifyListByName(c, "0");
			List<CommunityQuestionPersistence> communityQuestionPersistences = null;
			
			if (type=="all")    //全部
			{
				communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity(classifyPersistences.get(0).getFAQCLASSIFYID());
				jsonObject.put("totalnumber", communityQuestionPersistences.size());
			}
			else if (type=="1") // 已解决
			{
				communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2(classifyPersistences.get(0).getFAQCLASSIFYID(), 1);
				jsonObject.put("totalnumber", communityQuestionPersistences.size());
			}
			else if (type=="0") // 待回答  !!! 没有 type = 0 的情况
			{
				communityQuestionPersistences = CommunityQuestionHelper.question_getCommunity2(classifyPersistences.get(0).getFAQCLASSIFYID(), 0);
				jsonObject.put("totalnumber", communityQuestionPersistences.size());
			}
			jsonObject.put("value", "1");
			jsonObject.put("endnumber", startnumber+question_CommunityViews.size());
			jsonObject.put("communityViews", question_CommunityViews);
			String result = JsonUtil.toJsonString(jsonObject); 
			System.out.println(result);
			return result;
		}
	}
	/*
	 * zyq_question2_问题内容展示
	 */
	@RequestMapping(value="question2",method=RequestMethod.GET)
	@SystemControllerLog(description = "社区具体问题显示")
	public ModelAndView question2(HttpServletRequest request,HttpServletResponse response,String q,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		ModelAndView mv = new ModelAndView("question2");
		List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
		//System.out.println("userPersistences:"+userPersistences.get(0).getUSERNAME());
		
		List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question2_getCommunity(q);
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistences.get(0).getCLASSIFYID());
		
		List<Question2_CommunityView> question2_CommunityViews = CommunityService.question2_CommunityViews_best(username,communityQuestionPersistences.get(0).getCOMMUNITYQUESTIONID());
		//System.out.println("有无最佳答案:"+question2_CommunityViews.size());
		
		int startNumber = 0;
		List<Question2_CommunityView> question2_CommunityViews2 = CommunityService.question2_CommunityViews_other(username,communityQuestionPersistences.get(0).getCOMMUNITYQUESTIONID(),startNumber);
		//System.out.println("非最佳答案个数:"+question2_CommunityViews2.size());
		
		List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswer(q);
		//System.out.println("问题答案个数:"+communityAnswerPersistences.size());
		//判断是否有分享内容的权利
		List<ITPersistence> list = ITHelper.IT(userPersistences.get(0).getUSERID());
		//System.out.println("用户是否为IT运维人员:"+list.size());
		if (list.size()==0) {
			mv.addObject("IsIT", "0");
		}else{
			System.out.println("IT运维人员：有权分享内容");
			mv.addObject("IsIT", "1");
			List<SharePersistence> sharePersistences = ShareHelper.getShareList_ID2(userPersistences.get(0).getUSERID(),q);
			
			List<CommunityAnswerPersistence> communityAnswerPersistences2 = CommunityAnswerHelper.question_iscurrentAnswer(q, 1);
			System.out.println("判断问题是否有最佳答案："+communityAnswerPersistences2.size());
			if (communityAnswerPersistences2.size()!=0) {
				System.out.println("IT运维人员是否分享内容："+sharePersistences.size());
				if (sharePersistences.size()==0) {
					mv.addObject("IsShare", "0");
				}else {
					mv.addObject("IsShare", "1");
				}
			}
		}
		mv.addObject("answerList_best", question2_CommunityViews);
		mv.addObject("answerList_other", question2_CommunityViews2);
		mv.addObject("userList", userPersistences);
		mv.addObject("questionList", communityQuestionPersistences);
		mv.addObject("classifyName", classifyPersistences.get(0).getFAQCLASSIFYNAME());
		mv.addObject("communityNumber", communityAnswerPersistences.size());
		mv.addObject("userid", userPersistences.get(0).getUSERID());
		mv.addObject("userName", userPersistences.get(0).getUSERNAME());
		mv.addObject("_userid", communityQuestionPersistences.get(0).getUSERID());
		return mv;
	}
	
	/*
	 * zyq_ajax_question的增加
	 */
	@ResponseBody
	@RequestMapping(value={"/saveCommunityQuestion"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	@SystemControllerLog(description = "社区问题增加")
	public String saveCommunityQuestion(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String url = (String) session.getAttribute("urlPath");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			String title = request.getParameter("title");
			String content = request.getParameter("description");
			String classifyId = request.getParameter("check_val");
			
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_iscurrent(userPersistences.get(0).getUSERID(), title);
			if (communityQuestionPersistences.size()==0) {
				//CommunityService.savaCommunityQuestion(useremail, title, content, classifyId);
				if (classifyId == null) {
					//zzl_当用户添加问题的时候没有选类别
					jsonObject.put("value", "3");					
				}else {
					CommunityService.savaCommunityQuestion2(username, title, content, classifyId);
					jsonObject.put("value", "1");
					
				}								
				jsonObject.put("url",url);
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}else {
				jsonObject.put("value", "2");
				jsonObject.put("url",url);
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}
		}
	}
	/*
	 * zyq_ajax_question2回复的增加
	 */
	@ResponseBody
	@RequestMapping(value={"/saveReplyQuestion"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	@SystemControllerLog(description = "社区问题回复")
	public String saveReplyQuestion(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		JSONObject jsonObject = new JSONObject();
		String url = request.getParameter("url");
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}else {
			String content = request.getParameter("content");
			String questionId = request.getParameter("questionId");
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_IsCommunityAnswer(userPersistences.get(0).getUSERID(), content, questionId);
			if (communityAnswerPersistences.size()==0) {
				CommunityService.saveReplyQuestion(username, content, questionId);
				jsonObject.put("value", "1");
				jsonObject.put("url", url);
				String result = JsonUtil.toJsonString(jsonObject);
				return result;
			}else {
				jsonObject.put("value", "2");
				jsonObject.put("url", url);
				String result = JsonUtil.toJsonString(jsonObject);
				return result;
			}
			
		}
		
	}
	/*
	 * zyq_question2_ajax_获得更多评论
	 */
	@ResponseBody
	@RequestMapping(value={"/queryMoreComment2"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(description = "获得更多社区问题评论")
	public String queryMoreComment2(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		//String useremail = (String) session.getAttribute("UserEmail");
		String questionId = request.getParameter("questionId");
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(questionId);
			List<Question2_CommunityView> question2_CommunityViews = CommunityService.question2_CommunityViews_other(username, questionId, startnumber);
			jsonObject.put("value", "1");
			jsonObject.put("endnumber", startnumber+question2_CommunityViews.size());
			jsonObject.put("totalnumber", answerPersistences.size());
			jsonObject.put("commentList", question2_CommunityViews);
			String result = JsonUtil.toJsonString(jsonObject); 
			System.out.println(result);
			return result;
		}
	}
}
