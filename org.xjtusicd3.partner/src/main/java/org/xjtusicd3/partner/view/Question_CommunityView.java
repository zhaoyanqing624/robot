package org.xjtusicd3.partner.view;


import org.xjtusicd3.database.model.CommunityQuestionPersistence;

public class Question_CommunityView {
	private String communityTitle;
	private String communityQuestion;
	private String time;
	
	private String answer;
	private String likesNumber; //点赞数
	private String communityNumber; //评论数
	private String userImage;
	private String userName;
	private String signature;
	private String totalCommunityNumber;
	private String totalLikesNumber;
	
	
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
	}
	public Question_CommunityView(){}
	
}
