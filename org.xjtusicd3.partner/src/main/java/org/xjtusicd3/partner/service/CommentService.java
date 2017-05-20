package org.xjtusicd3.partner.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.xjtusicd3.database.helper.CommentHelper;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.CommentPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.view.Faq2_faqUserView;
import org.xjtusicd3.partner.view.Faq3_CommentView;

public class CommentService {
	/*
	 * zyq_faq3_ajax_添加评论
	 */
	public static void addComment(String userid, String faqquestionid, String comment) {
		Date date=new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
		CommentHelper.saveComment(UUID.randomUUID().toString(),faqquestionid,null,userid,comment,time,"0");
	}
	/*
	 * zyq_question2_ajax_添加评论的回复
	 */
	public static void saveCommunityComment(String userid,String communityquestionId,String comment,String answerId){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String time = format.format(date);
	    CommentHelper.saveComment(UUID.randomUUID().toString(), null, communityquestionId, userid, comment, time, answerId);
	}
	/*
	 * zyq_faq3_获得评论列表
	 */
	public static List<Faq3_CommentView> faq3_comment(String questionId) {
		List<Faq3_CommentView> faq3_CommentViews = new ArrayList<Faq3_CommentView>();
		List<CommentPersistence> commentPersistences = CommentHelper.getComment(questionId);
		for(CommentPersistence commentPersistence : commentPersistences){
			List<Faq2_faqUserView> faq2_faqUserViews = new ArrayList<Faq2_faqUserView>();
			List<UserPersistence> userPersistences = UserHelper.getEmail_id(commentPersistence.getUSERID());
			for(UserPersistence userPersistence:userPersistences){
				Faq2_faqUserView userView = new Faq2_faqUserView(userPersistence);
				faq2_faqUserViews.add(userView);
			}
			Faq3_CommentView faq3_CommentView = new Faq3_CommentView(commentPersistence);
			faq3_CommentView.setUserViews(faq2_faqUserViews);
			faq3_CommentViews.add(faq3_CommentView);
		}
		return faq3_CommentViews;
	}

}
