package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="TBL_User")
public class UserPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="USERID")
	private String UserId;
	@TableField(columnName="USERNAME")
	private String UserName;
	@TableField(columnName="USERPASSWORD")
	private String UserPassword;
	@TableField(columnName="USEREMAIL")
	private String UserEmail;
	@TableField(columnName="GENDER")
	private String UserSex;
	@TableField(columnName="USERBIRTHDAY")
	private String UserBirthday;
	@TableField(columnName="USERADDRESS")
	private String UserAddress;
	@TableField(columnName="USERSIGNATURE")
	private String UserBrief;
	@TableField(columnName="AVATAR")
	private String UserImage;
	@TableField(columnName="ROLEID")
	private String RoleId;
	@TableField(columnName="USERSTATE")
	private int UserState;
	@TableField(columnName="VERIFICATIONCODE")
	private String IdentificationNumber;
	@TableField(columnName="TIMESTAMPS")
	private String UserTimeStamp;
	
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
	public String getIdentificationNumber() {
		return IdentificationNumber;
	}
	public void setIdentificationNumber(String identificationNumber) {
		IdentificationNumber = identificationNumber;
	}
	public String getUserTimeStamp() {
		return UserTimeStamp;
	}
	public void setUserTimeStamp(String userTimeStamp) {
		UserTimeStamp = userTimeStamp;
	}
	
}	
