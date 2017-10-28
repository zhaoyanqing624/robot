package org.xjtusicd3.portal.interceptor;


import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.xjtusicd3.database.helper.RolePermissionHelper;
import org.xjtusicd3.database.model.PermissionPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.portal.service.LogService;

/**
 * 
 * http://blog.csdn.net/tonytfjing/article/details/39207551
 *
 */
public class CommonInterceptor extends HandlerInterceptorAdapter{
	
	//private final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);

	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object Handler) throws ServletException, IOException{
		//1
//	 	   List<UserPersistence> users = UserService.getAllUserList();
//	 	   UserPersistence user = users.get(0);
//	 	   
//	 	   for (int i = 0; i < users.size(); i++){
//	 		   
//	 		   	if(users.get(i).getUSERNAME().equals("royals")){
//	 		   		user = users.get(i);
//	 		   	}
//	 		   	
//	 		   	if (users.get(i).getUSERNAME().equals("会员20170509000900zs58ii")){
//	 		   		user = users.get(i);
//				}
//	 	   }
		UserPersistence user = (UserPersistence)req.getSession().getAttribute("user");
		
//		用来测试get请求，post请求		
//		if("GET".equalsIgnoreCase(req.getMethod())){
//			
//			System.out.println("GET ......");
//			
//		}
		//log.info("***************执行1：preHandler**************");
		
		//此处用来测试是否得到用户
		//System.out.println(user == null);
		
		//如果用户为空
		if(user == null){
			//log.info("interceptor...跳转到login页面...");
			req.getRequestDispatcher("login.html").forward(req, resp);
			return false;
		}
		
		else if(user != null){
			//判断角色下royals该用户的权限：获取到request的路径和get，post
			//System.out.println("asda:   "+req.getMethod() + " : "+ req.getServletPath());
		
			String path = req.getServletPath();
			List<PermissionPersistence> rolePermissions = RolePermissionHelper.getRolePermission(user.getUSERID());
			//System.out.println(rolePermissions.size());
			
			for(PermissionPersistence rp : rolePermissions){
				 
				if (rp.getPERMISSIONLOGICNAME().equals(path)){
					System.out.println(rp.getPERMISSIONLOGICNAME());
//					System.out.println(nowTime); 
//					System.out.println(user.getUSERID());
					String userId = user.getUSERID();
					String logPermission = rp.getPERMISSIONLOGICNAME();
					
					//注释_2017年10月22日14:52:12
					//LogService.saveLog(userId, logPermission);
					return true;
				}
			}			
			return false;
		}
		else {
			 
			return false;
		}
	}
	
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, 
			ModelAndView modelAndView){
		//log.info("***********执行2：postHandle*************");
		if(modelAndView != null){
			modelAndView.addObject("var", "测试postHandle");
		}
	} 
	
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp){
		//log.info("************执行3：afterCompletion**************");
	}
	public static final String GenerateGUID(){
		  UUID uuid = UUID.randomUUID();
		  return uuid.toString();  
		 }
}
