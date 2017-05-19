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
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.service.CommunityService;
import org.xjtusicd3.partner.view.Question_CommunityView;

import com.alibaba.fastjson.JSONObject;

@Controller
public class CommunityController {
	/*
	 * zyq_question_右侧类别
	 */
	@RequestMapping(value="question",method=RequestMethod.GET)
	public ModelAndView question(String c,String type,HttpServletRequest request){
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.community_classify();
		List<Question_CommunityView> question_CommunityViews = CommunityService.Question_CommunityView(type,c);
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
		return mv;
	}
	/*
	 * zyq_question2_问题内容展示
	 */
	@RequestMapping(value="question2",method=RequestMethod.GET)
	public ModelAndView question2(String q,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		ModelAndView mv = new ModelAndView("question2");
		if (useremail==null) {
			new ModelAndView("login.html");
		}else {
			List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
			List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question2_getCommunity(q);
			List<ClassifyPersistence> classifyPersistences = ClassifyHelper.faq2_classify(communityQuestionPersistences.get(0).getCLASSIFYID());
			mv.addObject("userList", userPersistences);
			mv.addObject("questionList", communityQuestionPersistences);
			mv.addObject("classifyName", classifyPersistences.get(0).getFAQCLASSIFYNAME());
		}
		return mv;
	}
	/*
	 * zyq_ajax_question的增加
	 */
	@ResponseBody
	@RequestMapping(value={"/saveCommunityQuestion"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String saveCommunityQuestion(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		String url = (String) session.getAttribute("urlPath");
		JSONObject jsonObject = new JSONObject();
		if (useremail==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			String title = request.getParameter("title");
			String content = request.getParameter("description");
			String classifynumber = request.getParameter("check_val");
			
			List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
			List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_iscurrent(userPersistences.get(0).getUSERID(), title);

			if (communityQuestionPersistences.size()==0) {
				CommunityService.savaCommunityQuestion(useremail, title, content, classifynumber);
				jsonObject.put("value", "1");
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
}
