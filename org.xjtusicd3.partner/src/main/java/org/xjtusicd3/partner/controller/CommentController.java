package org.xjtusicd3.partner.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xjtusicd3.common.util.JsonUtil;
import org.xjtusicd3.database.helper.AgreeHelper;
import org.xjtusicd3.database.helper.CollectionHelper;
import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.QuestionHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AgreePersistence;
import org.xjtusicd3.database.model.CollectionPersistence;
import org.xjtusicd3.database.model.CommentPersistence;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.QuestionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.service.CommentService;
import org.xjtusicd3.partner.service.CommunityService;
import org.xjtusicd3.partner.view.Faq3_CommentReplyView;
import org.xjtusicd3.partner.view.Faq3_CommentView;
import org.xjtusicd3.partner.view.Question2_CommunityReplayView;

import com.alibaba.fastjson.JSONObject;

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
		String faqusername = request.getParameter("faqusername");
		String username = (String) session.getAttribute("UserName");
		if (username==null) {
			return "0";
		}else {
			//查看评论是否重复提交
			List<UserPersistence> uList = UserHelper.getUserInfo(username);
			List<QuestionPersistence> faqlist = QuestionHelper.faq3_faqcontent_title(faqtitle);
			List<UserPersistence> userPersistences = UserHelper.getEmail_name(faqusername);
			CommentService.addComment(uList.get(0).getUSERID(),faqlist.get(0).getFAQQUESTIONID(),comment,userPersistences.get(0).getUSERID());
			return "1";
		}
	 }
	/*
	 * zyq_question_ajax_添加评论
	 */
	@ResponseBody
	@RequestMapping(value={"/addComment"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	public String addComment(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail"); 
		String username = (String) session.getAttribute("UserName"); 
		String url = (String) session.getAttribute("urlPath");
		String questionId = request.getParameter("questionId");
		String commentContent = request.getParameter("commentContent");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			//判断评论是否重复提交
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username); 
			List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_IsCommunityAnswer(userPersistences.get(0).getUSERID(), commentContent,questionId);
			if (communityAnswerPersistences.size()==0) {
				CommunityService.addComment(userPersistences.get(0).getUSERID(), commentContent, questionId);
				jsonObject.put("value", "1");
				jsonObject.put("url",url);
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
				
			}else{
				jsonObject.put("value", "2");
				jsonObject.put("url",url);
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}
		}
	}
	/*
	 * zyq_question2_ajax_添加评论的回复
	 */
	@ResponseBody
	@RequestMapping(value={"/saveCommunityComment"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	public String saveCommunityComment(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail"); 
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");
		String answerId = request.getParameter("answerId");
		String content = request.getParameter("content");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			//判断回复是否重复提交
			List<CommentPersistence> commentPersistences = CommentHelper.question2_getComment2(answerId, userPersistences.get(0).getUSERID(), content,questionId);
			if (commentPersistences.size()==0) {
				CommentService.saveCommunityComment(userPersistences.get(0).getUSERID(), questionId, content, answerId);
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
				
			}else{
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}
		}
	}
	/*
	 * zyq_question2_ajax_添加评论的回复的回复
	 */
	@ResponseBody
	@RequestMapping(value={"/saveCommunityReply"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	public String saveCommunityReply(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail"); 
		String username = (String) session.getAttribute("UserName"); 		
		String questionId = request.getParameter("questionId");
		String answerId = request.getParameter("answerId");
		String content = request.getParameter("content");
		String toUserName = request.getParameter("tousername");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			//判断回复的回复是否重复提交
			List<UserPersistence> userPersistences2 = UserHelper.getEmail_name(toUserName);
			List<CommentPersistence> commentPersistences = CommentHelper.question2_getComment3(answerId, userPersistences.get(0).getUSERID(), content,questionId,userPersistences2.get(0).getUSERID());
			if (commentPersistences.size()==0) {
				CommentService.saveCommunityReply(userPersistences.get(0).getUSERID(), questionId, content, answerId,userPersistences2.get(0).getUSERID());
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
				
			}else{
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}
		}
	}
	/*
	 * zyq_faq3_ajax_添加知识库评论
	 */
	@ResponseBody
	@RequestMapping(value={"/saveFaqComment"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String saveFaqComment(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");  
		String questionId = request.getParameter("questionId");
		String commentId = request.getParameter("commentId");
		String content = request.getParameter("comment");
		String duo = request.getParameter("duo");//判断是回复评论还是回复回复
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			List<UserPersistence> userPersistences = UserHelper.getUserInfo(username);
			//判断回复是否重复提交
			List<CommentPersistence> commentPersistences = CommentHelper.faq3_getComment(commentId,userPersistences.get(0).getUSERID(), content,questionId);
			if (commentPersistences.size()==0) {
				CommentService.saveFaqComment(userPersistences.get(0).getUSERID(), questionId, content, commentId,duo);
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
				
			}else{
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}
		}
	}
	/*
	 * zyq_question2_ajax_查看更多回复
	 */
	@ResponseBody
	@RequestMapping(value={"/getMoreComment"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String getMoreComment(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");
		String answerId = request.getParameter("answerId");
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<Question2_CommunityReplayView> list = CommentService.question2_CommunityReplayViews(questionId, answerId, startnumber);
			List<CommentPersistence> commentPersistences = CommentHelper.question2_getComment(questionId, answerId);
			jsonObject.put("value", "1");
			jsonObject.put("endnumber", startnumber+list.size());
			jsonObject.put("commentList", list);
			jsonObject.put("totalnumber", commentPersistences.size());
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_faq3_ajax_获得更多评论
	 */
	@ResponseBody
	@RequestMapping(value={"/queryMoreComment"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String queryMoreComment(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<CommentPersistence> commentPersistences = CommentHelper.getComment2(questionId,"0");
			List<Faq3_CommentView> faq3_CommentViews = CommentService.faq3_comment(questionId,startnumber);
			jsonObject.put("value", "1");
			jsonObject.put("endnumber", startnumber+faq3_CommentViews.size());
			jsonObject.put("totalnumber", commentPersistences.size());
			jsonObject.put("commentList", faq3_CommentViews);
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}

	/*
	 * zyq_faq3_ajax_获得更多回复
	 */
	@ResponseBody
	@RequestMapping(value={"/queryMoreReply"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String queryMoreReply(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String commentId = request.getParameter("commentid");
		int startnumber = Integer.parseInt(request.getParameter("startnumber"));
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else{
			List<CommentPersistence> commentPersistences = CommentHelper.faq3_getCommentReply(commentId);
			List<Faq3_CommentReplyView> faq3_CommentReplyViews = CommentService.faq3_CommentReplyViews(commentId,startnumber);
			jsonObject.put("value", "1");
			jsonObject.put("endnumber", startnumber+faq3_CommentReplyViews.size());
			jsonObject.put("totalnumber", commentPersistences.size());
			jsonObject.put("commentList", faq3_CommentReplyViews);
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_question2_ajax_点赞
	 */
	@ResponseBody
	@RequestMapping(value={"/saveAgreeAnswer"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String saveAgreeAnswer(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String answerId = request.getParameter("answerId");
		System.out.println("进入点赞模块");
		List<AgreePersistence> agreePersistences = AgreeHelper.getAgree(username, answerId);
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			if (agreePersistences.size()==0) {
				System.out.println("点赞数量"+agreePersistences.size());
				AgreeHelper.saveAgree(username, answerId);
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}else {
				AgreeHelper.deleteAgree(agreePersistences.get(0).getAGREEID());
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}
		}
	}
	/*
	 * zyq_question_ajax_点赞
	 */
	@ResponseBody
	@RequestMapping(value={"/saveAgreeAnswer2"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String saveAgreeAnswer2(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");
		List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_iscurrentAnswer(questionId, 1);
		String answerId = communityAnswerPersistences.get(0).getCOMMUNITYANSWERID();
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			List<AgreePersistence> agreePersistences = AgreeHelper.getAgree(username, answerId);
			if (agreePersistences.size()==0) {
				AgreeHelper.saveAgree(username, answerId);
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}else {
				AgreeHelper.deleteAgree(agreePersistences.get(0).getAGREEID());
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}
		}
	}
	/*
	 * zyq_question2_ajax_收藏
	 */
	@ResponseBody
	@RequestMapping(value={"/saveCollectionAnswer"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String saveCollectionAnswer(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String answerId = request.getParameter("answerId");
		List<CollectionPersistence> collectionPersistences = CollectionHelper.getCollection(username, answerId);
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			if (collectionPersistences.size()==0) {
				CollectionHelper.saveCollection(username, answerId);
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}else {
				CollectionHelper.deleteCollection(collectionPersistences.get(0).getCOLLECTIONID());
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}
		}
	}
	/*
	 * zyq_question2_ajax_收藏
	 * faq3_收藏
	 */
	@ResponseBody
	@RequestMapping(value={"/saveCollectionFAQ"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String saveCollectionFAQ(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String questionId = request.getParameter("questionId");
		List<CollectionPersistence> collectionPersistences = CollectionHelper.getCollection2(username, questionId);
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			if (collectionPersistences.size()==0) {
				CollectionHelper.saveCollection2(username, questionId);
				jsonObject.put("value", "1");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}else {
				CollectionHelper.deleteCollection(collectionPersistences.get(0).getCOLLECTIONID());
				jsonObject.put("value", "2");
				String result = JsonUtil.toJsonString(jsonObject); 
				return result;
			}
		}
	}
	/*
	 * zyq_question2_ajax_设为最佳答案
	 */
	@ResponseBody
	@RequestMapping(value={"/saveBestAnswer"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String saveBestAnswer(HttpServletRequest request,HttpSession session){
		String username = (String) session.getAttribute("UserName");
		//String useremail = (String) session.getAttribute("UserEmail");
		System.out.println("设置最佳答案用户："+username);
		String answerId = request.getParameter("answerId");
		String quesitonId = request.getParameter("quesitonId");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			System.out.println("设置最佳答案");
			CommentService.saveBestAnswer(quesitonId,answerId);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}
	}
	/*
	 * zyq_faq3_ajax_删除自己的回复
	 */
	@ResponseBody
	@RequestMapping(value={"/deleteReply"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String deleteReply(HttpServletRequest request,HttpSession session){
		//String useremail = (String) session.getAttribute("UserEmail");
		String username = (String) session.getAttribute("UserName");
		String commentId = request.getParameter("commentId");
		JSONObject jsonObject = new JSONObject();
		if (username==null) {
			jsonObject.put("value", "0");
			String result = JsonUtil.toJsonString(jsonObject); 
			return result;
		}else {
			CommentHelper.deleteReply(commentId);
			jsonObject.put("value", "1");
			String result = JsonUtil.toJsonString(jsonObject);
			return result;
		}
	}
}
