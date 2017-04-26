package org.xjtusicd3.database.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.xjtusicd3.database.logic.IBaseDao;
import org.xjtusicd3.database.model.UserPersistence;

public interface UserPersistenceMapper extends IBaseDao<UserPersistence, String>{
	/*
	 * login_ajax_注册
	 */
	@Insert("INSERT INTO `User`(`User`.UserId,`User`.UserEmail,`User`.UserPassword,`User`.UserName,`User`.UserState,`User`.IdentificationNumber,`User`.UserTimeStamp) VALUES (#{0},#{1},#{2},#{3},#{4},#{5},#{6})")
	public void login_register(String userid,String email,String password,String username,int userstate,String identification_number,String time_stamp);
	//校验邮箱是否被注册
	@Select("SELECT * FROM User WHERE UserEmail=#{0}")
	List<UserPersistence> getEmail(String useremail);
	@Select("SELECT * FROM User WHERE UserEmail=#{0} AND UserPassword=#{1}")
	List<UserPersistence> getEmail2(String param1,String param2);
	@Select("SELECT * FROM User WHERE UserEmail=#{0} AND IdentificationNumber=#{1}")
	List<UserPersistence> getEmail3(String param1,String param2);
	//验证码通过
	@Update("UPDATE `User` SET `User`.UserState='1' WHERE UserEmail=#{0}")
	public void updateUserState(String useremail);
	//验证码没有通过 删除
	@Delete("DELETE FROM `User` WHERE `User`.UserEmail=#{0}")
	public void deleteUser(String useremail);
	 
	//zpz_获取用户信息
	@Select("SELECT UserName,UserPassword,UserEmail FROM User")
	List<UserPersistence> getUser();

	//上传图片
	@Update("UPDATE `User` SET `User`.UserImage=#{1} WHERE UserEmail=#{0}")
	public void updateUserImage(String param2, String param1);
	//用户个人信息完善
	@Update("UPDATE `User` SET UserName=#{1},UserSex=#{2},UserBirthday=#{3},UserAddress=#{4},UserBrief=#{5} WHERE UserEmail=#{0}")
	public void updateUserInfo(String email, String username, String usersex, String userbirthday, String address,String userbrief);
}
