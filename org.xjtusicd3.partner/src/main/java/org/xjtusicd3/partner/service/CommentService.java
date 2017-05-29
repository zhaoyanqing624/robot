package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.AnswerHelper;
import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.CommunityAnswerHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.AnswerPersistence;
import org.xjtusicd3.database.model.CommentPersistence;
import org.xjtusicd3.database.model.CommunityAnswerPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.view.Faq2_faqUserView;
import org.xjtusicd3.partner.view.Faq3_CommentView;
import org.xjtusicd3.partner.view.Question2_CommunityReplayView;


public class CommentService {
	/*
	 * zyq_faq3_ajax_添加评论
	 */
	public static void addComment(String userid, String faqquestionid, String comment) {
		Date date=new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
	    //查看是否回答自己的FAQ
	    List<AnswerPersistence> answerPersistences = AnswerHelper.faq3_faqContent(faqquestionid);
	    int isnotice = 0;
	    if (userid.equals(answerPersistences.get(0).getUSERID())) {
			isnotice = 0;
		}else {
			isnotice = 1;
		}
		CommentHelper.saveComment(UUID.randomUUID().toString(),faqquestionid,null,userid,comment,time,"0",isnotice);
	}
	/*
	 * zyq_question2_ajax_添加评论的回复
	 */
	public static void saveCommunityComment(String userid,String communityquestionId,String comment,String answerId){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
	    //查看是否回复了自己的评论
	    List<CommunityAnswerPersistence> communityAnswerPersistences = CommunityAnswerHelper.question_CommunityAnswerId(answerId);
	    int isnotice = 0;
	    if (userid.equals(communityAnswerPersistences.get(0).getUSERID())) {
			isnotice = 0;
		}else {
			isnotice = 1;
		}
	    CommentHelper.saveComment(UUID.randomUUID().toString(), null, communityquestionId, userid, comment, time, answerId,isnotice);
	}
	/*
	 * zyq_faq3_获得评论列表
	 */
	public static List<Faq3_CommentView> faq3_comment(String questionId,int startnumber) {
		List<Faq3_CommentView> faq3_CommentViews = new ArrayList<Faq3_CommentView>();
		List<CommentPersistence> commentPersistences = CommentHelper.getCommentMore(questionId,startnumber);
		for(CommentPersistence commentPersistence : commentPersistences){
			List<Faq2_faqUserView> faq2_faqUserViews = new ArrayList<Faq2_faqUserView>();
			List<UserPersistence> userPersistences = UserHelper.getEmail_id(commentPersistence.getUSERID());
			for(UserPersistence userPersistence:userPersistences){
				Faq2_faqUserView userView = new Faq2_faqUserView(userPersistence);
				faq2_faqUserViews.add(userView);
			}
			Faq3_CommentView faq3_CommentView = new Faq3_CommentView(commentPersistence);
			faq3_CommentView.setUserViews(faq2_faqUserViews);
			faq3_CommentView.setCommentId(commentPersistence.getCOMMENTID());
			faq3_CommentViews.add(faq3_CommentView);
		}
		return faq3_CommentViews;
	}
	/*
	 * zyq_question2_获得更多的回复
	 */
	public static List<Question2_CommunityReplayView> question2_CommunityReplayViews(String questionId,String answerId,Integer startnumber){
		List<Question2_CommunityReplayView> question2_CommunityReplayViews = new ArrayList<Question2_CommunityReplayView>();
		List<CommentPersistence> commentPersistences = CommentHelper.question2_getMoreComment(questionId, answerId, startnumber);
		for(CommentPersistence commentPersistence:commentPersistences){
			Question2_CommunityReplayView question2_CommunityReplayView = new Question2_CommunityReplayView();
			question2_CommunityReplayView.setCommunity(commentPersistence.getCOMMENTCONTENT());
			question2_CommunityReplayView.setTime(commentPersistence.getCOMMENTTIME());
			List<UserPersistence> userPersistences = UserHelper.getEmail_id(commentPersistence.getUSERID());
			question2_CommunityReplayView.setUserImage(userPersistences.get(0).getAVATAR());
			question2_CommunityReplayView.setUserName(userPersistences.get(0).getUSERNAME());
			question2_CommunityReplayViews.add(question2_CommunityReplayView);
		}
		return question2_CommunityReplayViews;
	}
}
