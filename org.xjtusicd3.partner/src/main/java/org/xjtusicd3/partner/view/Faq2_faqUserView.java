package org.xjtusicd3.partner.view;

import org.xjtusicd3.database.model.UserPersistence;

public class Faq2_faqUserView {
	private String userId;
	private String userName;
	private String userImage;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public Faq2_faqUserView(UserPersistence userPersistence){
		this.userId = userPersistence.getUserId();
		this.userName = userPersistence.getUserName();
		this.userImage = userPersistence.getUserImage();
	}
	public Faq2_faqUserView(){
		
	}
}
