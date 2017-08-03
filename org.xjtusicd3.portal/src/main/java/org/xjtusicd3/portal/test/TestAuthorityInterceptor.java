package org.xjtusicd3.portal.test;

import org.aopalliance.intercept.MethodInterceptor;

import org.aopalliance.intercept.MethodInvocation;

//����Around����Ӧ��ʵ��MethodInterceptor�ӿ�

public class TestAuthorityInterceptor implements MethodInterceptor
{

	private User user;

	public User getUser()
	{

		return user;
	}

	public void setUser(User user)
	{

		this.user = user;
	}

	// invoke�������ص��õĽ��

	public Object invoke(MethodInvocation invocation) throws Throwable
	{
		String methodName = invocation.getMethod().getName();

		if (user.getUsername().equals("unRegistedUser"))
		{
			System.out.println("��������δע���û�,û��Ȩ�޻ظ�,ɾ������!");

			return null;
		}

		if ((user.getUsername().equals("user")) && (methodName.equals("deleteTopic")))
		{
			System.out.println("��������ע���û�,û��Ȩ��ɾ������");

			return null;
		}

		// proceed()���������ӵ��������������������,���������е�ÿ����������ִ�и÷���,���������ķ���ֵ

		return invocation.proceed();
	}
}