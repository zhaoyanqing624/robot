package org.xjtusicd3.partner.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.Authentication.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.service.UserService;
import org.xjtusicd3.partner.view.UserView;

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
	/*
	 * 邮箱跳转验证码是否超时
	 */
	@RequestMapping(value="/valiadateEmail",method=RequestMethod.GET)
	public String validateEmail(HttpSession session,String e,String p){
		List<UserPersistence> list = UserHelper.getEmail3(e, p);
		if (list.size()==0) {
			return "redirect:404.html";
		}else {
			if (UserService.validateUserState(e)==true) {
				session.setAttribute("UserEmail", e);
				return "redirect:robot.html";
			}else {
				if (UserService.validateEmail(e)==true) {
					UserHelper.updateUserState(e);
					session.setAttribute("UserEmail", e);
					return "redirect:robot.html";
				}else {
					return "redirect:404.html";
				}
			}
		}
	}
	/*
	 * login_登录
	 */
	@RequestMapping(value="/saveLogin",method=RequestMethod.POST)
	public String loginlist(UserView userView,HttpSession session){
		String email = userView.getUserEmail();
		String password = userView.getUserPassword();
		List<UserPersistence> list = UserHelper.getEmail2(email, password);
		if (list.size()==0) {
			return "redirect:login.html";
		}else {
			session.setAttribute("UserEmail", email);
			return "redirect:robot.html";
		}
	}
	/*
	 * 用户退出
	 */
	@RequestMapping(value="/loginout",method=RequestMethod.GET)
	public String loginout(UserView userView,HttpSession session){
		ModelAndView modelAndView = null;
		session.invalidate();
		return "redirect:/robot.html";
	}
}
