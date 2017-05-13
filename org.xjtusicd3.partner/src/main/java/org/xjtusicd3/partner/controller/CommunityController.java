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
import org.xjtusicd3.database.helper.ClassifyHelper;
import org.xjtusicd3.database.helper.CommunityQuestionHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.ClassifyPersistence;
import org.xjtusicd3.database.model.CommunityQuestionPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.service.CommentService;
import org.xjtusicd3.partner.service.CommunityService;

@Controller
public class CommunityController {
	/*
	 * zyq_question_右侧类别
	 */
	@RequestMapping(value="question",method=RequestMethod.GET)
	public ModelAndView question(){
		List<ClassifyPersistence> classifyPersistences = ClassifyHelper.community_classify();
		ModelAndView mv = new ModelAndView("question");
		mv.addObject("classifyList", classifyPersistences);
		return mv;
	}
	/*
	 * zyq_ajax_question的增加
	 */
	@ResponseBody
	@RequestMapping(value={"/saveCommunityQuestion"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/plain;charset=UTF-8")
	public String saveCommunityQuestion(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail");
		if (useremail==null) {
			return "0";
		}else {
			String title = request.getParameter("title");
			String content = request.getParameter("description");
			String classifynumber = request.getParameter("check_val");
			CommunityService.savaCommunityQuestion(useremail, title, content, classifynumber);
			List<UserPersistence> userPersistences = UserHelper.getEmail(useremail);
			List<CommunityQuestionPersistence> communityQuestionPersistences = CommunityQuestionHelper.question_iscurrent(userPersistences.get(0).getUSERID(), title);
			if (communityQuestionPersistences.size()==0) {
				return "1";
			}else {
				return "2";
			}
		}
	}
}
