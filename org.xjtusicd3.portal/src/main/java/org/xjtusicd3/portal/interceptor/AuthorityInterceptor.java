package org.xjtusicd3.portal.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;

public class AuthorityInterceptor extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String user = (String)request.getSession().getAttribute("user");
//		 MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();
//	     String    method = methodNameResolver.getHandlerMethodName(request);
		System.out.println("进入拦截器了o adf，当前用户是：" + request.getSession().getAttribute("user") + " ，当前uri是：" );
		 
		 
		 // 2. 没登录，登录去
//        if (user == null) {
//            request.setAttribute("message", "请先登录！！！");
//            request.getRequestDispatcher("/message.jsp").forward(request, response);
//            return false;
//        }

        // 3. 得到用户想访问的资源
        String uri = request.getRequestURI();
       
        System.out.println("当前uri是：" + request.getRequestURI());
        System.out.println("当前uri是：" + request.getContextPath());
        return super.preHandle(request, response, handler);
        // 4. 得到访问该资源需要的权限
//        SecurityService service = new SecurityService();
//        Resource r = service.findResource(uri);
        /*
         * 你要访问的资源，我在权限管理系统里面没有说访问这个资源需要权限，
         * 也即这个资源不需要被权限系统控制，只有被权限系统控制的资源，在数据库里面
         * 才有，如果为null，这个资源不受权限系统控制。
         */
//	        if (r == null) {
//	            chain.doFilter(request, response);
//	            return;
//	        }
//        Privilege required_Privilege = r.getPrivilege(); // 得到访问资源需要的权限
//
//        // 5. 判断用户是否有相应权限
//        List<Privilege> list = service.getUserAllPrivilege(user.getId()); // 得到用户所有权限
//        if (!list.contains(required_Privilege)) {
//            // 6. 没有权限，则提示用户权限不足，联系管理员
//            request.setAttribute("message", "对不起，您没有权限，请联系管理员！！！");
//            request.getRequestDispatcher("/message.jsp").forward(request, response);
//            return;
//        }
//
//        // 7. 如果有，则则放行
//        chain.doFilter(request, response);

    }

	 
	
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception
	{
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception
	{
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}
