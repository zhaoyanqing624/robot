package org.xjtusicd3.partner.view;

import java.util.List;

import org.xjtusicd3.database.model.CommunityAnswerPersistence;

public class Question_CommunityAnswerView {
	private String answer;
	private String likesNumber;
	private String communityNumber;
	private String userImage;
	private String userName;
	private String signature;
	private String totalCommunityNumber;
	private String totalLikesNumber;
	
	
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
	
	public Question_CommunityAnswerView(CommunityAnswerPersistence answerPersistence){
		this.answer = answerPersistence.getCONTENT();
	}
	public Question_CommunityAnswerView(){
		
	}
}
