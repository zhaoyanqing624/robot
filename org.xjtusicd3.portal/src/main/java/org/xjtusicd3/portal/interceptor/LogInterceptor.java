package org.xjtusicd3.portal.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

//声明这是一个组件
@Component
//声明这是一个切面Bean
@Aspect
public class LogInterceptor
{

	private final static Log log = LogFactory.getLog(LogInterceptor.class);

	// 配置切入点,该方法无方法体,主要为方便同类中其他方法使用此处配置的切入点
	@Pointcut("execution(public * *(..))")
	public void aspect()
	{
	}
	

	/*
	 * 配置前置通知,使用在方法aspect()上注册的切入点 同时接受JoinPoint切入点对象,可以没有该参数
	 */
	@Before("aspect()")
	public void before(JoinPoint joinPoint)
	{
		String name = ((MethodSignature)(joinPoint.getSignature())).getMethod().getName();
		System.out.println("方法名：" +  name);
		if (log.isInfoEnabled())
		{
			System.out.println(joinPoint.getKind());
			log.info("hello");
			log.debug("hello");
			// 权限校验
		}
	}

	// 配置后置通知,使用在方法aspect()上注册的切入点
	@After("aspect()")
	public void after(JoinPoint joinPoint)
	{
		if (log.isInfoEnabled())
		{
			log.info("after " + joinPoint);
		}
	}

	// 配置环绕通知,使用在方法aspect()上注册的切入点
	@Around("aspect()")
	public void around(JoinPoint joinPoint)
	{
		long start = System.currentTimeMillis();
		try
		{
			((ProceedingJoinPoint) joinPoint).proceed();
			long end = System.currentTimeMillis();
			if (log.isInfoEnabled())
			{
				log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
			}
		} catch (Throwable e)
		{
			long end = System.currentTimeMillis();
			if (log.isInfoEnabled())
			{
				log.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : "
						+ e.getMessage());
			}
		}
	}

	// 配置后置返回通知,使用在方法aspect()上注册的切入点
	@AfterReturning("aspect()")
	public void afterReturn(JoinPoint joinPoint)
	{
		if (log.isInfoEnabled())
		{
			log.info("afterReturn " + joinPoint);
		}
	}

	// 配置抛出异常后通知,使用在方法aspect()上注册的切入点
	@AfterThrowing(pointcut = "aspect()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex)
	{
		if (log.isInfoEnabled())
		{
			log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
		}
	}
	
	


}
