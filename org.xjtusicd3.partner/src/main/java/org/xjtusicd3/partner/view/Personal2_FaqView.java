package org.xjtusicd3.partner.view;

public class Personal2_FaqView {
	private String faqId;
	private String title;
	private String content;
	private String username;
	private String userId;
	private String time;
	private String scanNumber;
	private String collectionNumber;
	private String commentNumber;
	private String isMore;
	
	//评论
	private String replyId;
	private String reply;
	private String isreply;
	private String replytime;
	private String replyNumber;
	private String parentId;
	
	
	
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getIsreply() {
		return isreply;
	}
	public void setIsreply(String isreply) {
		this.isreply = isreply;
	}
	public String getReplytime() {
		return replytime;
	}
	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}
	public String getReplyNumber() {
		return replyNumber;
	}
	public void setReplyNumber(String replyNumber) {
		this.replyNumber = replyNumber;
	}
	public String getIsMore() {
		return isMore;
	}
	public void setIsMore(String isMore) {
		this.isMore = isMore;
	}
	public String getFaqId() {
		return faqId;
	}
	public void setFaqId(String faqId) {
		this.faqId = faqId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getScanNumber() {
		return scanNumber;
	}
	public void setScanNumber(String scanNumber) {
		this.scanNumber = scanNumber;
	}
	public String getCollectionNumber() {
		return collectionNumber;
	}
	public void setCollectionNumber(String collectionNumber) {
		this.collectionNumber = collectionNumber;
	}
	public String getCommentNumber() {
		return commentNumber;
	}
	public void setCommentNumber(String commentNumber) {
		this.commentNumber = commentNumber;
	}
}
