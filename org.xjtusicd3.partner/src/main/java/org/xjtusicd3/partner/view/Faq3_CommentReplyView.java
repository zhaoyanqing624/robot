package org.xjtusicd3.partner.view;

public class Faq3_CommentReplyView {
	private String commentId;
	private String parrentId;
	private String UserName;
	private String toUserName;
	private String time;
	private String comment;

	
	public String getParrentId() {
		return parrentId;
	}
	public void setParrentId(String parrentId) {
		this.parrentId = parrentId;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
