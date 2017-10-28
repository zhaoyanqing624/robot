/*package org.xjtusicd3.portal.interceptor;

import java.lang.reflect.Method;  
import java.util.List;  
import java.util.Map;  
  
import javax.servlet.http.HttpServletRequest;  
  
import org.aopalliance.intercept.MethodInterceptor;  
import org.aopalliance.intercept.MethodInvocation;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.core.annotation.AnnotationUtils;  
import org.springframework.web.servlet.ModelAndView;  
import org.springframework.web.servlet.View;  
  
  
*//** 
 * @author landong.sun 
 * 
 *//*  
public class privilegeInterceptor implements MethodInterceptor{  
      
    @Autowired  
    SessionProvider sessionProvider;  
  
    @Override  
    public Object invoke(MethodInvocation invocation) throws Throwable {  
          
        Method method = invocation.getMethod();//获取被拦截的方法    
        Object[] arguments = invocation.getArguments();//获取拦截方法的参数  
        PrivilegeInfo privilegeInfo = AnnotationUtils.findAnnotation(method, PrivilegeInfo.class);//根据方法找到注解，这个注解是我们自定义的注解，下面会讲  
        if(privilegeInfo!=null) {  
            String value = (String) AnnotationUtils.getAnnotationAttributes(privilegeInfo).get("value");//获取权限值  
            boolean isAccessed = false;  
            MenuCondition isPrivilege = new MenuCondition();//这是一个权限类   
            //从session中获取权限  
            for (MenuCondition privilege : privileges) {  
                 
                  * 如果目标方法没有使用PrivilegeInfo注解，则解析出来的权限字符串就为空字符串 
                 * 则默认用户拥有这个权限 
                   
                if ("".equals(methodAccess)) {  
                    isAccessed = true;  
                    break;  
                }  
                 
                 * 用户原有权限列表中有的权限与目标方法上PrivilegeInfo注解配置的权限进行匹配
                   
                String truePrivikey = privilege.getlPrivyKey()+"";  
                if (truePrivikey!= null &&   
                        StringUtils.equalsIgnoreCase(methodAccess, truePrivikey)) {  
                    isAccessed = true;  
                    isPrivilege = privilege;  
                    break;  
                }  
            }  
             
            * 3.如果用户拥有权限，则调用目标方法　，如果没有，则不调用目标方法，只给出提示 
               
            if (isAccessed) {  
                 
                  * 特殊，某些权限需要做特殊处理 
                 * 比如用户信息权限，在方法执行完毕返回的时候，要将电话号码与邮箱抹除 
                   
               //环绕通知前置特殊处理  
                this.beforeReslove();  
                Object proceed = invocation.proceed();//调用目标方法  
                //环绕通知后置特殊处理  
                proceed =  this.afterReslove();  
                return proceed;  
                  
            } else{  
                ResultUtils.throwExcepion(ResultUtils.createFail(Config.FBD_MESSAGE, 301, null));  
            }  
                  
        return null;  
    }     
  
}*/