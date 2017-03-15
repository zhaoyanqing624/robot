package org.xjtusicd3.portal.service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class HttpSessionListenerImpl implements HttpSessionListener{
	public void sessionCreated(HttpSessionEvent event) { 
		  ServletContext application = event.getSession().getServletContext(); 
		  Integer num = (Integer) application.getAttribute("onLineNum"); 
		  if(num != null){ 
		   int count = num; 
		   count = count + 1; 
		   application.setAttribute("onLineNum", count); 
		  } 
		 }

		 public void sessionDestroyed(HttpSessionEvent event) { 
		  ServletContext application = event.getSession().getServletContext(); 
		  Integer num = (Integer) application.getAttribute("onLineNum"); 
		  int count = num; 
		  count = count - 1; 
		  application.setAttribute("onLineNum", count); 
		   
		 }
}
