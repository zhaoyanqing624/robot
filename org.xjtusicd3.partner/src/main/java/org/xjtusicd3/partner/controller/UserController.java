package org.xjtusicd3.partner.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.service.UserService;

@Controller
public class UserController {
	/*
	 * login_ajax_注册
	 */
	@ResponseBody
	@RequestMapping(value={"/saveRegister"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="text/html;charset=UTF-8")
	public String registerlist(HttpServletRequest request,HttpServletResponse response){
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		//判断邮箱是否被注册
		List<UserPersistence> list = UserHelper.getEmail(email);
		if (list==null) {
			UserService.login_register(email, password);
			return "0";
		}else {
			return "1";
		}
	}
	/*
	 * 邮箱跳转验证码是否超时
	 */
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String validateEmail(String e,String p){
		
		return p;
	}

}
