package org.xjtusicd3.partner.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.service.CommentService;

@Controller
public class CommentController {
	/*
	 * zyq_faq3_ajax_添加评论
	 */
	@ResponseBody
	@RequestMapping(value={"/saveComment"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	public String saveComment(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String faqtitle = request.getParameter("faqtitle");
		String comment = request.getParameter("comment");
		String useremail = (String) session.getAttribute("UserEmail");
		if (useremail==null) {
			return "0";
		}else {
			List<UserPersistence> uList = UserHelper.getEmail(useremail);
			List<QuestionPersistence> faqlist = QuestionHelper.faq3_faqcontent_title(faqtitle);
			CommentService.addComment(uList.get(0).getUSERID(),faqlist.get(0).getFAQQUESTIONID(),comment);
			return "1";
		}
	 }
	/*
	 * zyq_question_ajax_添加评论
	 */
	@ResponseBody
	@RequestMapping(value={"/addComment"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	public String addComment(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		String useremail = (String) session.getAttribute("UserEmail"); 
		String questionId = request.getParameter("questionId");
		String commentContent = request.getParameter("commentContent");
		//判断评论是否重复提交
		List<UserPersistence> userPersistences = UserHelper.getEmail(useremail); 
		List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_IsCommunityAnswer(userPersistences.get(0).getUSERID(), commentContent,questionId);
		if (communityAnswerPersistences.size()==0) {
			//CommunityAnswerHelper.
		}else{
			
		}
		System.out.println(questionId);
		System.out.println(commentContent);
		return questionId;
	}
}
