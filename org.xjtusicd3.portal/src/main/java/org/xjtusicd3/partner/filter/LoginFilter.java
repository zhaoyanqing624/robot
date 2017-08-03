package org.xjtusicd3.partner.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	/*public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String url = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
		if (url.endsWith("/login.html") || url.endsWith("/loginAction.html") || url.contains("/apiMenu")) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}

		if (request.getSession().getAttribute("lgcount") != null) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		} else {
			if (url.endsWith("ftl") || url.endsWith("html")) {
				response.sendRedirect(request.getContextPath() + "/");
				// chain.doFilter(servletRequest, servletResponse);
				return;
			} else {
				response.getWriter().print("logout");
				return;
			}
		}
	}*/
	
	/*public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
			servletRequest.setCharacterEncoding("utf-8");
			servletResponse.setCharacterEncoding("utf-8");
			chain.doFilter(servletRequest, servletResponse);
			
	}*/
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {			
			
			HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			String url = request.getServletPath() + (request.getPathInfo() == null ? "" : request.getPathInfo());
			HttpSession session = request.getSession();
			Object object = session.getAttribute("session_username");
			
			//没有登录就不允许访问后台链接
			if (object==null&&(url.contains("incidentindex")||url.contains("problemindex")||url.contains("knowledgeindex")||url.contains("forum")||url.contains("forum")||url.contains("forum")||url.contains("forum")||url.contains("forum")||url.contains("forum"))) {
				response.sendRedirect(request.getContextPath() + "/user/login.html");
				return;
			} 
			chain.doFilter(servletRequest, servletResponse);
			
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	

}
