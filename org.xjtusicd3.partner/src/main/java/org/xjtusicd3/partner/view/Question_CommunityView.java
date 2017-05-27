package org.xjtusicd3.partner.view;


import org.xjtusicd3.database.model.CommunityQuestionPersistence;

public class Question_CommunityView {
	private String communityId;
	private String communityTitle;
	private String communityQuestion;
	private String time;
	private String classifyName;
	private String answer;
	private String likesNumber; //点赞数
	private String communityNumber; //评论数
	private String userImage;
	private String userName;
	private String userId;
	private String signature;
	private String totalCommunityNumber;
	private String totalLikesNumber;
	
	private String isLike;//判断是否点赞
	
	
	public String getIsLike() {
		return isLike;
	}
	public void setIsLike(String isLike) {
		this.isLike = isLike;
	}
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommunityId() {
		return communityId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	public String getCommunityTitle() {
		return communityTitle;
	}
	public void setCommunityTitle(String communityTitle) {
		this.communityTitle = communityTitle;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getLikesNumber() {
		return likesNumber;
	}
	public void setLikesNumber(String likesNumber) {
		this.likesNumber = likesNumber;
	}
	public String getCommunityNumber() {
		return communityNumber;
	}
	public void setCommunityNumber(String communityNumber) {
		this.communityNumber = communityNumber;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTotalCommunityNumber() {
		return totalCommunityNumber;
	}
	public void setTotalCommunityNumber(String totalCommunityNumber) {
		this.totalCommunityNumber = totalCommunityNumber;
	}
	public String getTotalLikesNumber() {
		return totalLikesNumber;
	}
	public void setTotalLikesNumber(String totalLikesNumber) {
		this.totalLikesNumber = totalLikesNumber;
	}
	public String getCommunityQuestion() {
		return communityQuestion;
	}
	public void setCommunityQuestion(String communityQuestion) {
		this.communityQuestion = communityQuestion;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}	
	public Question_CommunityView(CommunityQuestionPersistence communityQuestionPersistence){
		this.communityQuestion = communityQuestionPersistence.getTITLE();
		this.time = communityQuestionPersistence.getTIME();
		this.communityId = communityQuestionPersistence.getCOMMUNITYQUESTIONID();
	}
	public Question_CommunityView(){}
	
}
