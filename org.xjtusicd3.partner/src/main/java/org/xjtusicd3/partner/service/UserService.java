package org.xjtusicd3.partner.service;

import org.xjtusicd3.database.helper.UserHelper;

public class UserService {
	//校验邮箱是否被注册
	public static String getEmail(String email){
		String a = UserHelper.getEmail(email);
		return a;
	}
}
