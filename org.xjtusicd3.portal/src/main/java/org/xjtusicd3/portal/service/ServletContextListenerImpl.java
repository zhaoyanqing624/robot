package org.xjtusicd3.portal.service;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerImpl implements ServletContextListener{
	public void contextDestroyed(ServletContextEvent event) { 
		  ServletContext application = event.getServletContext(); 
		  application.removeAttribute("onLineNum"); 
		}

		public void contextInitialized(ServletContextEvent event) { 
		  int num = 0; 
		  ServletContext application = event.getServletContext(); 
		  application.setAttribute("onLineNum", num); 
		}
}
