package org.xjtusicd3.partner.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xjtusicd3.partner.service.UserService;

@Controller
public class UserController {
	/*
	 * login_ajax_注册
	 */
	@ResponseBody
	@RequestMapping(value={"/register"},method={org.springframework.web.bind.annotation.RequestMethod.POST},produces="application/json;charset=UTF-8")
	public String registerlist(HttpServletRequest request,HttpServletResponse response){
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(email);
		System.out.println(username);
		System.out.println(password);
		return null;
	}
	/*
	 * 检验邮箱是否被注册
	 */
	@RequestMapping(value={"getEmail"},method={org.springframework.web.bind.annotation.RequestMethod.GET},produces="text/html;charset=UTF-8")
	public void getEmail(HttpServletResponse response,String e) throws IOException{
		String email = UserService.getEmail(e);
		boolean flag=false;
		if(email!=null&&email.length()!=0){
			 flag = true;
		}else{
			 flag = false;
		}
		response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(flag);//返回登录信息
        out.flush();
        out.close();
	}
	
}
