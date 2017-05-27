package org.xjtusicd3.partner.view;


import java.util.List;

import org.xjtusicd3.database.model.CommunityAnswerPersistence;

public class Question2_CommunityView {
	private String answerId;
	private String answer;
	private String userImage;
	private String userName;
	private String userId;
	private String signature;
	private String totalAnswer;
	private String totalLikes;
	
	private String likesNumber;
	private String communityNumber;
	private String time;
	
	private String isLike;//判断是否点赞
	private String isCollection;//判断是否收藏
	private String isBestAnswer;//判断是否最佳答案
	
	private List<Question2_CommunityReplayView> replay;
	
		
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsBestAnswer() {
		return isBestAnswer;
	}

	public void setIsBestAnswer(String isBestAnswer) {
		this.isBestAnswer = isBestAnswer;
	}

	public String getIsLike() {
		return isLike;
	}

	public void setIsLike(String isLike) {
		this.isLike = isLike;
	}

	public String getIsCollection() {
		return isCollection;
	}

	public void setIsCollection(String isCollection) {
		this.isCollection = isCollection;
	}

	public List<Question2_CommunityReplayView> getReplay() {
		return replay;
	}

	public void setReplay(List<Question2_CommunityReplayView> replay) {
		this.replay = replay;
	}

	public String getAnswerId() {
		return answerId;
	}

	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
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

	public String getTotalAnswer() {
		return totalAnswer;
	}

	public void setTotalAnswer(String totalAnswer) {
		this.totalAnswer = totalAnswer;
	}

	public String getTotalLikes() {
		return totalLikes;
	}

	public void setTotalLikes(String totalLikes) {
		this.totalLikes = totalLikes;
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

	public Question2_CommunityView(CommunityAnswerPersistence communityAnswerPersistence){
		this.answer= communityAnswerPersistence.getCONTENT();
	}
	public Question2_CommunityView(){
		
	}
}
