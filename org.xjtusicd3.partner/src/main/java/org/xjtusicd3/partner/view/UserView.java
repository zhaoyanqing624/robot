package org.xjtusicd3.partner.view;

import org.xjtusicd3.database.model.UserPersistence;

public class UserView {
	private String UserId;
	private String UserName;
	private String UserPassword;
	private String UserPassword2;
	private String UserEmail;
	private String UserSex;
	private String UserSex2;
	private String UserBirthday;
	private String UserAddress;
	private String UserBrief;
	private String UserImage;
	private String Province;
	private String City;
	private String District;
	
	//zzl_用户名或密码
	private String nameOrEmail;
		
	public String getNameOrEmail() {
		return nameOrEmail;
	}
	public void setNameOrEmail(String nameOrEmail) {
		this.nameOrEmail = nameOrEmail;
	}
	public String getUserSex2() {
		return UserSex2;
	}
	public void setUserSex2(String userSex2) {
		UserSex2 = userSex2;
	}
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
	
	public String getUserPassword2() {
		return UserPassword2;
	}
	public void setUserPassword2(String userPassword2) {
		UserPassword2 = userPassword2;
	}
	public UserView(UserPersistence userPersistence){
		this.UserId = userPersistence.getUSERID();
		this.UserName = userPersistence.getUSERNAME();
		this.UserPassword = userPersistence.getUSERPASSWORD();
		this.UserEmail = userPersistence.getUSEREMAIL();
		this.UserBirthday = userPersistence.getUSERBIRTHDAY();
		this.UserAddress = userPersistence.getUSERADDRESS();
		this.UserBrief = userPersistence.getUSERSIGNATURE();
		this.UserImage = userPersistence.getAVATAR();
		this.UserSex = userPersistence.getGENDER();
		this.Province = getProvince();
		this.City = getCity();
		this.District = getDistrict();
		this.UserPassword2 = getUserPassword2();
		this.UserSex2 = getUserSex2();
	}
	
	public UserView(){
		
	}
}
