package org.xjtusicd3.portal.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.xjtusicd3.portal.test.ILogin;
public class Test {
	public static void main(String[] args)  
    {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("ApplicationContext.XML");
        ILogin login = (ILogin)ctx.getBean("login");
        login.login("flash"); //用户为flash是正确用户，否则是错误的
    }
}