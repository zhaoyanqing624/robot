package org.xjtusicd3.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.portal.service.UserService;

@Controller
public class UserController 
{
	/*
	 * zpz_得到user
	 */
	@RequestMapping(value="userindex",method=RequestMethod.GET)
	public ModelAndView user()
	{
		ModelAndView mv = new ModelAndView("userindex");
		String user = UserService.getUser();
		mv.addObject("user",user);
		return mv;
		
	}
	//增加用户
	@RequestMapping(value="adminAddUser",method=RequestMethod.GET)
	public String adminAddUser(HttpServletRequest request,HttpServletResponse response){

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		//判断邮箱是否被注册
		List<UserPersistence> list = UserHelper.getEmail(email);
		if (list.size()==0) {
			UserService.login_register(email, password);
			return "0";
		}else {
			if (UserHelper.getEmail2(email, password).size()==0) {
				if (UserService.validateUserState(email)==false) {
					if (UserService.validateEmail(email)==true) {
						return "1";
					}else {
						UserHelper.deleteUser(email);
						UserService.login_register(email, password);
						return "0";
					}
				}else {
					return "1";
				}
			}else {
				if (UserService.validateUserState(email)==false) {
					if (UserService.validateEmail(email)==true) {
						return "2";
					}else {
						UserHelper.deleteUser(email);
						UserService.login_register(email, password);
						return "0";
					}
				}else {
					return "1";
				}
			}
		}
	
	}
}
