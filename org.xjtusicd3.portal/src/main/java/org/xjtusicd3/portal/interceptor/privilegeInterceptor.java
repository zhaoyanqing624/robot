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
          
        Method method = invocation.getMethod();//��ȡ�����صķ���  
        Object[] arguments = invocation.getArguments();//��ȡ���ط����Ĳ���  
        PrivilegeInfo privilegeInfo = AnnotationUtils.findAnnotation(method, PrivilegeInfo.class);//���ݷ����ҵ�ע�⣬���ע���������Զ����ע�⣬����ὲ  
        if(privilegeInfo!=null) {  
            String value = (String) AnnotationUtils.getAnnotationAttributes(privilegeInfo).get("value");//��ȡȨ��ֵ  
            boolean isAccessed = false;  
            MenuCondition isPrivilege = new MenuCondition();//����һ��Ȩ����  
            //��session�л�ȡȨ��  
            for (MenuCondition privilege : privileges) {  
                 
                 * ���Ŀ�귽��û��ʹ��PrivilegeInfoע�⣬�����������Ȩ���ַ�����Ϊ���ַ��� 
                 * ��Ĭ���û�ӵ�����Ȩ�� 
                   
                if ("".equals(methodAccess)) {  
                    isAccessed = true;  
                    break;  
                }  
                 
                 * �û�ԭ��Ȩ���б����е�Ȩ����Ŀ�귽����PrivilegeInfoע�����õ�Ȩ�޽���ƥ�� 
                   
                String truePrivikey = privilege.getlPrivyKey()+"";  
                if (truePrivikey!= null &&   
                        StringUtils.equalsIgnoreCase(methodAccess, truePrivikey)) {  
                    isAccessed = true;  
                    isPrivilege = privilege;  
                    break;  
                }  
            }  
             
             * 3.����û�ӵ��Ȩ�ޣ������Ŀ�귽���������û�У��򲻵���Ŀ�귽����ֻ������ʾ 
               
            if (isAccessed) {  
                 
                 * ���⣬ĳЩȨ����Ҫ�����⴦�� 
                 * �����û���ϢȨ�ޣ��ڷ���ִ����Ϸ��ص�ʱ��Ҫ���绰����������Ĩ�� 
                   
                //����֪ͨǰ�����⴦��  
                this.beforeReslove();  
                Object proceed = invocation.proceed();//����Ŀ�귽��  
                //����֪ͨ�������⴦��  
                proceed =  this.afterReslove();  
                return proceed;  
                  
            } else{  
                ResultUtils.throwExcepion(ResultUtils.createFail(Config.FBD_MESSAGE, 301, null));  
            }  
                  
        return null;  
    }     
  
}*/