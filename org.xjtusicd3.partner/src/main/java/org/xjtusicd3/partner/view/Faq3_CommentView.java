package org.xjtusicd3.partner.view;

import java.util.List;

import org.xjtusicd3.database.model.CommentPersistence;

public class Faq3_CommentView {
	private String CommentId;
	private String CommentTime;
	private String CommentContent;
	private String CommentNumber;
	private List<Faq2_faqUserView> userViews;
	private List<Faq3_CommentReplyView> replyViews;
	
	public String getCommentNumber() {
		return CommentNumber;
	}
	public void setCommentNumber(String commentNumber) {
		CommentNumber = commentNumber;
	}
	public List<Faq3_CommentReplyView> getReplyViews() {
		return replyViews;
	}
	public void setReplyViews(List<Faq3_CommentReplyView> replyViews) {
		this.replyViews = replyViews;
	}
	public String getCommentId() {
		return CommentId;
	}
	public void setCommentId(String commentId) {
		CommentId = commentId;
	}
	public String getCommentTime() {
		return CommentTime;
	}
	public void setCommentTime(String commentTime) {
		CommentTime = commentTime;
	}
	public String getCommentContent() {
		return CommentContent;
	}
	public void setCommentContent(String commentContent) {
		CommentContent = commentContent;
	}
	public List<Faq2_faqUserView> getUserViews() {
		return userViews;
	}
	public void setUserViews(List<Faq2_faqUserView> userViews) {
		this.userViews = userViews;
	}
	
	public Faq3_CommentView(CommentPersistence commentPersistence){
		this.CommentTime = commentPersistence.getCOMMENTTIME();
		this.CommentContent = commentPersistence.getCOMMENTCONTENT();
	}
	public Faq3_CommentView(){
		
	}
}
