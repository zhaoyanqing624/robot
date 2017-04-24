package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="User")
public class UserPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="UserId")
	private String UserId;
	@TableField(columnName="UserName")
	private String UserName;
	@TableField(columnName="UserPassword")
	private String UserPassword;
	@TableField(columnName="UserEmail")
	private String UserEmail;
	@TableField(columnName="UserSex")
	private String UserSex;
	@TableField(columnName="UserBirthday")
	private String UserBirthday;
	@TableField(columnName="UserAddress")
	private String UserAddress;
	@TableField(columnName="UserBrief")
	private String UserBrief;
	@TableField(columnName="UserImage")
	private String UserImage;
	@TableField(columnName="RoleId")
	private String RoleId;
	@TableField(columnName="UserState")
	private int UserState;
	@TableField(columnName="UserRegister")
	private String UserRegister;
	@TableField(columnName="RegisterTime")
	private String RegisterTime;
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
	public String getRoleId() {
		return RoleId;
	}
	public void setRoleId(String roleId) {
		RoleId = roleId;
	}
	public int getUserState() {
		return UserState;
	}
	public void setUserState(int userState) {
		UserState = userState;
	}
	public String getUserRegister() {
		return UserRegister;
	}
	public void setUserRegister(String userRegister) {
		UserRegister = userRegister;
	}
	public String getRegisterTime() {
		return RegisterTime;
	}
	public void setRegisterTime(String registerTime) {
		RegisterTime = registerTime;
	}
	
	
}	
