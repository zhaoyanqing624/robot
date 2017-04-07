package org.xjtusicd3.database.model;

import org.xjtusicd3.database.ann.Table;
import org.xjtusicd3.database.ann.TableField;
import org.xjtusicd3.database.ann.TableKey;
import org.xjtusicd3.database.ann.TableKey.Strategy;

@Table(tablename="user")
public class UserPersistence {
	@TableKey(strategy=Strategy.NORMAL)
	@TableField(columnName="userId")
	private int userId;
	@TableField(columnName="userEmail")
	private String userEmail;
	@TableField(columnName="userPassword")
	private String userPassword;
	@TableField(columnName="userName")
	private String userName;
	@TableField(columnName="userSex")
	private String userSex;
	@TableField(columnName="userBirthday")
	private String userBirthday;
	@TableField(columnName="userAddress")
	private String userAddress;
	@TableField(columnName="userBrief")
	private String userBrief;
	@TableField(columnName="userImage")
	private String userImage;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserBirthday() {
		return userBirthday;
	}
	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserBrief() {
		return userBrief;
	}
	public void setUserBrief(String userBrief) {
		this.userBrief = userBrief;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
}	
