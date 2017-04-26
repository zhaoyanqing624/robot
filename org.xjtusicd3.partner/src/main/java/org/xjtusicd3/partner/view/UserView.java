package org.xjtusicd3.partner.view;

import org.xjtusicd3.database.model.UserPersistence;

public class UserView {
	private String UserId;
	private String UserName;
	private String UserPassword;
	private String UserEmail;
	private String UserSex;
	private String UserBirthday;
	private String UserAddress;
	private String UserBrief;
	private String UserImage;
	private String Province;
	private String City;
	private String District;
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getUserPassword() {
		return UserPassword;
	}
	public void setUserPassword(String userPassword) {
		UserPassword = userPassword;
	}
	public String getUserEmail() {
		return UserEmail;
	}
	public void setUserEmail(String userEmail) {
		UserEmail = userEmail;
	}
	public String getUserSex() {
		return UserSex;
	}
	public void setUserSex(String userSex) {
		UserSex = userSex;
	}
	public String getUserBirthday() {
		return UserBirthday;
	}
	public void setUserBirthday(String userBirthday) {
		UserBirthday = userBirthday;
	}
	public String getUserAddress() {
		return UserAddress;
	}
	public void setUserAddress(String userAddress) {
		UserAddress = userAddress;
	}
	public String getUserBrief() {
		return UserBrief;
	}
	public void setUserBrief(String userBrief) {
		UserBrief = userBrief;
	}
	public String getUserImage() {
		return UserImage;
	}
	public void setUserImage(String userImage) {
		UserImage = userImage;
	}
	
	public String getProvince() {
		return Province;
	}
	public void setProvince(String province) {
		Province = province;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public UserView(UserPersistence userPersistence){
		this.UserId = userPersistence.getUserId();
		this.UserName = userPersistence.getUserName();
		this.UserPassword = userPersistence.getUserPassword();
		this.UserEmail = userPersistence.getUserEmail();
		this.UserBirthday = userPersistence.getUserBirthday();
		this.UserAddress = userPersistence.getUserAddress();
		this.UserBrief = userPersistence.getUserBrief();
		this.UserImage = userPersistence.getUserImage();
		this.UserSex = userPersistence.getUserSex();
		this.Province = getProvince();
		this.City = getCity();
		this.District = getDistrict();
	}
	
	public UserView(){
		
	}
}
