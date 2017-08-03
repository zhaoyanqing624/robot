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
		System.out.println("������������o adf����ǰ�û��ǣ�" + request.getSession().getAttribute("user") + " ����ǰuri�ǣ�" );
		 
		 
		 // 2. û��¼����¼ȥ
//	        if (user == null) {
//	            request.setAttribute("message", "���ȵ�¼������");
//	            request.getRequestDispatcher("/message.jsp").forward(request, response);
//	            return false;
//	        }

	        // 3. �õ��û�����ʵ���Դ
	        String uri = request.getRequestURI();
	       
	        System.out.println("��ǰuri�ǣ�" + request.getRequestURI());
	        System.out.println("��ǰuri�ǣ�" + request.getContextPath());
	        return super.preHandle(request, response, handler);
	        // 4. �õ����ʸ���Դ��Ҫ��Ȩ��
//	        SecurityService service = new SecurityService();
//	        Resource r = service.findResource(uri);
	        /*
	         * ��Ҫ���ʵ���Դ������Ȩ�޹���ϵͳ����û��˵���������Դ��ҪȨ�ޣ�
	         * Ҳ�������Դ����Ҫ��Ȩ��ϵͳ���ƣ�ֻ�б�Ȩ��ϵͳ���Ƶ���Դ�������ݿ�����
	         * ���У����Ϊnull�������Դ����Ȩ��ϵͳ���ơ�
	         */
//	        if (r == null) {
//	            chain.doFilter(request, response);
//	            return;
//	        }
//	        Privilege required_Privilege = r.getPrivilege(); // �õ�������Դ��Ҫ��Ȩ��
//
//	        // 5. �ж��û��Ƿ�����ӦȨ��
//	        List<Privilege> list = service.getUserAllPrivilege(user.getId()); // �õ��û�����Ȩ��
//	        if (!list.contains(required_Privilege)) {
//	            // 6. û��Ȩ�ޣ�����ʾ�û�Ȩ�޲��㣬��ϵ����Ա
//	            request.setAttribute("message", "�Բ�����û��Ȩ�ޣ�����ϵ����Ա������");
//	            request.getRequestDispatcher("/message.jsp").forward(request, response);
//	            return;
//	        }
//
//	        // 7. ����У��������
//	        chain.doFilter(request, response);

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
