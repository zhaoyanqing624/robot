package org.xjtusicd3.portal.test;

import java.io.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.xjtusicd3.portal.test.ILogin;
public class Test {
	public static void main(String[] args)  
    {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("ApplicationContext.XML");
        ILogin login = (ILogin)ctx.getBean("login");
        login.login("flash"); //�û�Ϊflash����ȷ�û��������Ǵ����
    }
}