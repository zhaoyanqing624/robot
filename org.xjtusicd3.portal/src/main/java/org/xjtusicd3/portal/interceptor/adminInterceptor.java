package org.xjtusicd3.portal.interceptor;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.xjtusicd3.database.model.UserPersistence;

public class adminInterceptor extends HandlerInterceptorAdapter{
	
	private final Logger log = LoggerFactory.getLogger(adminInterceptor.class);

	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object Handler) throws ServletException, IOException{
				
		if("GET".equalsIgnoreCase(req.getMethod())){
			
			System.out.println("GET ......");
			
		}
		log.info("***************执行1：adminpreHandler**************");
		
		UserPersistence user = (UserPersistence) req.getSession().getAttribute("user");
		if(user == null){
			log.info("interceptor...跳转到adminlogin页面...");
			req.getRequestDispatcher("../adminlogin.jsp").forward(req, resp);
			return false;
		}
		if(user != null){
//			User_userType user_usertype = user.getUser_userType();
//			if(user_usertype.getU_ut_id() == 1){
//				return true;
//			}else{
//				System.out.println("admin 中的user_usertype ......");
//				req.getRequestDispatcher("../adminlogin.jsp").forward(req, resp);
//				return false;
//			}
		}
		else{
			return true;			
		}
		return false;		
	}
	
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, 
			ModelAndView modelAndView){
		log.info("***********执行2：adminpostHandle*************");
		if(modelAndView != null){
			modelAndView.addObject("var", "测试postHandle");
		}
	} 
	
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp){
		log.info("************执行3：afterCompletion**************");
	}
}
