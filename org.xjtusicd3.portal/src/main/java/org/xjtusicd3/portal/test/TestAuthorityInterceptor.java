package org.xjtusicd3.portal.test;

import org.aopalliance.intercept.MethodInterceptor;

import org.aopalliance.intercept.MethodInvocation;

//创建Around处理应该实现MethodInterceptor接口

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

	// invoke方法返回调用的结果

	public Object invoke(MethodInvocation invocation) throws Throwable
	{
		String methodName = invocation.getMethod().getName();

		if (user.getUsername().equals("unRegistedUser"))
		{
			System.out.println("你的身份是未注册用户,没有权限回复,删除帖子!");

			return null;
		}

		if ((user.getUsername().equals("user")) && (methodName.equals("deleteTopic")))
		{
			System.out.println("你的身份是注册用户,没有权限删除帖子");

			return null;
		}

		// proceed()方法对连接点的整个拦截器链起作用,拦截器链中的每个拦截器都执行该方法,并返回它的返回值

		return invocation.proceed();
	}
}