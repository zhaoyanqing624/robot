package org.xjtusicd3.partner.annotation;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;  
import org.springframework.web.context.request.ServletRequestAttributes;
import org.xjtusicd3.database.helper.UserHelper;
import org.xjtusicd3.database.model.LogPersistence;
import org.xjtusicd3.database.model.UserPersistence;
import org.xjtusicd3.partner.service.LogService;

/**
 * 切点类
 * 
 */
@Aspect
@Component
public class SystemLogAspect {

	 @Autowired   
	private LogService logService;
    
    private final static int LOG_NORMAL = 1;  
    private final static int LOG_UNNORMAL = 0;  
    
    public SystemLogAspect(){  
        System.out.println("SystemLogAspect is initing!!!!");  
    }  
  //本地异常日志记录对象  
    public Logger logger = Logger.getLogger(SystemLogAspect.class);  
   // private  static  final Logger logger = Logger.getLogger(SystemLogAspect.class);
    //Controller层切点     
    @Pointcut("@annotation(org.xjtusicd3.partner.annotation.SystemControllerLog)")  
    public void controllerAspect(){       
    }  
    
    //Service层切点    
//    @Pointcut("@annotation(org.xjtusicd3.partner.annotation.SystemServiceLog)")    
//     public  void serviceAspect() {    
//    }  
 
    
    
    
    @AfterReturning("controllerAspect()")  
     public void doBefore(JoinPoint joinPoint){  
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(); 
        HttpSession session = request.getSession();  
        String urlPath1 = request.getRequestURL().toString();
        String queryString = request.getQueryString();
      
        //读取session中的用户      
        String username = (String) session.getAttribute("UserName");     
        List<UserPersistence> list = UserHelper.getUserInfo(username);
        //获取请求ip      
        String ip = getIP(request); 
        try {  
        	   
            System.out.println("=====后置通知开始=====");    
            System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
            System.out.println("方法描述:" + getControllerMethodDescription(joinPoint)); 
            System.out.println("请求url："+urlPath1);
            if(username != null){
            	System.out.println("请求人:" + username);    
            }else{
            	System.out.println("请求人: 游客");  
            }
            System.out.println("请求IP:" + ip);   
 
            
            String params = ""; 
          /* 
            if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
                for ( int i = 0; i < joinPoint.getArgs().length; i++) {    
                   params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";    
               }    
           } 
           */ 
            //System.out.println("params.length() = " + params.length());  
            //System.out.println("params = " + params);  
            LogPersistence myLog = new LogPersistence();
			myLog.setLogId(UUID.randomUUID().toString());
            myLog.setLogMethod(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");	
			myLog.setOperation(getControllerMethodDescription(joinPoint));
			 if (queryString==null) {
					String urlPath = urlPath1;
					myLog.setUrl(urlPath);
				}else {
					String urlPath2 = urlPath1+"?"+queryString;
					//StringBuffer urlPath = new StringBuffer(urlPath2);
					myLog.setUrl(urlPath2);
				}
			
			
			myLog.setIp(ip);
            //myLog.setNormal(LOG_NORMAL);
			
			if(username != null){
				myLog.setUserId(list.get(0).getUSERID());
			}else{
				myLog.setUserId("00000000-0000-0000-0000-000000000000");
			}
			Date date=new Date();
		    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String time = format.format(date);
		    myLog.setLogTime(time);
						
			logService.insertLog(myLog);
			logger.info("here wait for moment");
			System.out.println("=====后置通知结束=====");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    public  static String getControllerMethodDescription(JoinPoint joinPoint)  throws Exception {      
        String targetName = joinPoint.getTarget().getClass().getName();    //获得执行方法的类名    
        String methodName = joinPoint.getSignature().getName();            //获得执行方法的方法名  
        Object[] arguments = joinPoint.getArgs();                          //获取切点方法的所有参数类型  
        Class targetClass = Class.forName(targetName);      
        Method[] methods = targetClass.getMethods();    //获取公共方法，不包括类私有的  
        String description = "";      
         for (Method method : methods) {      
             if (method.getName().equals(methodName)) {      
                Class[] clazzs = method.getParameterTypes();     //对比方法中参数的个数      
                 if (clazzs.length == arguments.length) {      
                    description = method.getAnnotation(SystemControllerLog.class).description();      
                     break;      
                }      
            }      
        }      
         return description;      
    }    
    
    public static String getIP(HttpServletRequest request) {  
        String ip = request.getHeader("x-forwarded-for");  
        if (!checkIP(ip)) {  
            ip = request.getHeader("Proxy-Client-IP");  
        }  
        if (!checkIP(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");  
        }  
        if (!checkIP(ip)) {  
            ip = request.getRemoteAddr();  
        }  
        return ip;  
    }  
    private static boolean checkIP(String ip) {  
        if (ip == null || ip.length() == 0 || "unkown".equalsIgnoreCase(ip)  
                || ip.split(".").length != 4) {  
            return false;  
        }  
        return true;  
    }  
    
    
    
    /**  
     * 异常通知 用于拦截service层记录异常日志  
     */  
    /*
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")   
     public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {    
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
        HttpSession session = request.getSession();    
        //读取session中的用户    
        User user = (User) session.getAttribute("user");      
        //获取请求ip    
        String ip = request.getRemoteAddr();    
        //获取用户请求方法的参数并序列化为JSON格式字符串    
        String params = "";    
        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
            for ( int i = 0; i < joinPoint.getArgs().length; i++) {    
               params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";    
           }    
       }    
        
     
        
         try {    
              
            System.out.println("=====异常通知开始=====");    
            System.out.println("异常代码:" + e.getClass().getName());    
            System.out.println("异常信息:" + e.getMessage());    
            System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
            System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));    
            System.out.println("请求人:" + user.getName());    
            System.out.println("请求IP:" + ip);    
            System.out.println("请求参数:" + params);    
     
           
                        
            Log myLog = new Log();           
            myLog.setMethod(joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
			myLog.setNormal(LOG_NORMAL);
			if(user != null){
				myLog.setUserID(user.getUserID());
				myLog.setUserName(user.getUserName());
			}
			else{
				myLog.setUserName("游客");
			}
		
			myLog.setCommand(getServiceMthodDescription(joinPoint));			
			myLog.setRequestIp(ip);
			myLog.setExceptionCode(e.getClass().getName());                 
            myLog.setExceptionDetail(e.getMessage());                
            myLog.setParam(params);    
            
 		
            //保存数据库    
            logService.insertLog(myLog);    
            System.out.println("=====异常通知结束=====");    
        }  catch (Exception ex) {    
           
        	e.printStackTrace(); }
         
    }    
    
    */
    /**  
     * 获取注解中对方法的描述信息 用于service层注解  
     *  
     * @param joinPoint 切点  
     * @return 方法描述  
     * @throws Exception  
     */    
     /*
    public  static String getServiceMthodDescription(JoinPoint joinPoint)    
             throws Exception {    
        String targetName = joinPoint.getTarget().getClass().getName();    
        String methodName = joinPoint.getSignature().getName();    
        Object[] arguments = joinPoint.getArgs();    
        Class targetClass = Class.forName(targetName);    
        Method[] methods = targetClass.getMethods();    
        String description = "";    
         for (Method method : methods) {    
             if (method.getName().equals(methodName)) {    
                Class[] clazzs = method.getParameterTypes();    
                 if (clazzs.length == arguments.length) {    
                    description = method.getAnnotation(SystemServiceLog. class).description();    
                     break;    
                }    
            }    
        }    
         return description;    
    }    
    
    */
  
     
     
     
     
     
}
