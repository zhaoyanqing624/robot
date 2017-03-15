package org.xjtusicd3.partner.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xjtusicd3.partner.service.UserService;

@Controller
public class UserController {
	//校验邮箱是否被注册
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
