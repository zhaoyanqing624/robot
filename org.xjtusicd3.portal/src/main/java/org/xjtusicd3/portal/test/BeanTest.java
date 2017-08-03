package org.xjtusicd3.portal.test;

import org.springframework.context.ApplicationContext;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BeanTest
{

	public static void main(String[] args) throws Exception
	{
		ApplicationContext ctx = new FileSystemXmlApplicationContext("bean.xml");
		TestResultImpl test = (TestResultImpl) ctx.getBean("testResult");
		test.answerTopic();
		test.deleteTopic();
	}
}