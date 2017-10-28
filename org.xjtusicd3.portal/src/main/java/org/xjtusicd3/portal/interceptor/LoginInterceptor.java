package org.xjtusicd3.portal.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LoginInterceptor implements MethodInterceptor {
   
    public Object invoke(MethodInvocation arg0) throws Throwable {
        String name = (String)arg0.getArguments()[0];
        if (name.equals("flash")) {
            System.out.println("这才是真正的用户！ ");
            return arg0.proceed();
        } else {
            System.out.println("非法的用户~~~ ");
            return null;
        }
    }
}