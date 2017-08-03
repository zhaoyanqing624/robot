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

//��������һ�����
@Component
// ��������һ������Bean
@Aspect
public class LogInterceptor
{

	private final static Log log = LogFactory.getLog(LogInterceptor.class);

	// ���������,�÷����޷�����,��ҪΪ����ͬ������������ʹ�ô˴����õ������
	@Pointcut("execution(public * *(..))")
	public void aspect()
	{
	}
	

	/*
	 * ����ǰ��֪ͨ,ʹ���ڷ���aspect()��ע�������� ͬʱ����JoinPoint��������,����û�иò���
	 */
	@Before("aspect()")
	public void before(JoinPoint joinPoint)
	{
		String name = ((MethodSignature)(joinPoint.getSignature())).getMethod().getName();
		System.out.println("��������" +  name);
		if (log.isInfoEnabled())
		{
			System.out.println(joinPoint.getKind());
			log.info("hello");
			log.debug("hello");
			// Ȩ��У��
		}
	}

	// ���ú���֪ͨ,ʹ���ڷ���aspect()��ע��������
	@After("aspect()")
	public void after(JoinPoint joinPoint)
	{
		if (log.isInfoEnabled())
		{
			log.info("after " + joinPoint);
		}
	}

	// ���û���֪ͨ,ʹ���ڷ���aspect()��ע��������
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

	// ���ú��÷���֪ͨ,ʹ���ڷ���aspect()��ע��������
	@AfterReturning("aspect()")
	public void afterReturn(JoinPoint joinPoint)
	{
		if (log.isInfoEnabled())
		{
			log.info("afterReturn " + joinPoint);
		}
	}

	// �����׳��쳣��֪ͨ,ʹ���ڷ���aspect()��ע��������
	@AfterThrowing(pointcut = "aspect()", throwing = "ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex)
	{
		if (log.isInfoEnabled())
		{
			log.info("afterThrow " + joinPoint + "\t" + ex.getMessage());
		}
	}
	
	


}
