import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.xjtusicd3.common.util.JsonUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Main {
	static int i = 0;
    private static String check() throws Exception {
    	String message= "";
        Runtime runtime = Runtime.getRuntime();  
        Process process = null;  
        process = runtime  
                .exec("cmd /c reg query HKEY_LOCAL_MACHINE\\SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall\\");  
        BufferedReader in = new BufferedReader(new InputStreamReader(process  
                .getInputStream(),"GBK"));  
        String string = null;  
        while ((string = in.readLine()) != null ) {  
            process = runtime.exec("cmd /c reg query " + string  
                    + " /v DisplayName");  
            BufferedReader name = new BufferedReader(new InputStreamReader(  
                    process.getInputStream(),"GBK"));  
             message = queryValue(string);
        }  
        in.close();  
        process.destroy();
		return message;  
  
    }  
    private static String queryValue(String string) throws IOException {
    		String resultString="";
	        String nameString = "";  
	        String versionString = "";  
	        String installSource = "";
	        String publisherString="";  
	        String uninstallPathString = "";  
	          
	        Runtime runtime = Runtime.getRuntime();  
	        Process process = null;  
	        BufferedReader br = null;  
	          
	        process = runtime.exec("cmd /c reg query " +"\""+ string +"\""+ " /v DisplayName");  
	        br = new BufferedReader(new InputStreamReader(process  
	                .getInputStream(),"GBK"));  
	        br.readLine();br.readLine();//去掉前两行无用信息  
	        if((nameString=br.readLine())!=null){  
	            nameString=nameString.replaceAll("DisplayName    REG_SZ    ", "");  //去掉无用信息  
	        }  
	          
	  
	        process = runtime.exec("cmd /c reg query " + "\""+ string +"\"" + " /v DisplayVersion");  
	        br = new BufferedReader(new InputStreamReader(process  
	                .getInputStream(),"GBK"));  
	        br.readLine();br.readLine();//去掉前两行无用信息  
	        if((versionString=br.readLine())!=null){  
	            versionString=versionString.replaceAll("DisplayVersion    REG_SZ    ", ""); //去掉无用信息  
	        }
	        
	        process = runtime.exec("cmd /c reg query " + "\""+ string +"\"" + " /v InstallSource");  
	        br = new BufferedReader(new InputStreamReader(process  
	                .getInputStream(),"GBK"));  
	        br.readLine();br.readLine();//去掉前两行无用信息  
	        if((installSource=br.readLine())!=null){  
	        	installSource=installSource.replaceAll("InstallSource    REG_SZ    ", ""); //去掉无用信息  
	        } 
	          
	        process = runtime.exec("cmd /c reg query " + "\""+ string +"\"" + " /v Publisher");  
	        br = new BufferedReader(new InputStreamReader(process  
	                .getInputStream(),"GBK"));  
	        br.readLine();br.readLine();//去掉前两行无用信息  
	        if((publisherString=br.readLine())!=null){  
	            publisherString =publisherString.replaceAll("Publisher    REG_SZ    ", ""); //去掉无用信息  
	        }  
	          
	        process = runtime.exec("cmd /c reg query " + "\""+ string +"\"" + " /v UninstallString");  
	        br = new BufferedReader(new InputStreamReader(process  
	                .getInputStream(),"GBK"));  
	        br.readLine();br.readLine();//去掉前两行无用信息  
	        if((uninstallPathString=br.readLine())!=null){  
	            uninstallPathString=uninstallPathString.replaceAll("UninstallString    REG_SZ    ", "");    //去掉无用信息  
	        }  
	          
//	        resultString[1]= versionString ;//== null ? null : new String(versionString.getBytes(),"GB-2312");  
//	        resultString[2]= publisherString ;//== null ? null : new String(publisherString.getBytes(),"GB-2312");  
//	        resultString[3]= uninstallPathString ;//== null ? null : new String(uninstallPathString.getBytes(),"GB-2312");  
//	        if(resultString[0]==null) resultString=null;    //没有名字的不显示  
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("name",nameString);
	        jsonObject.put("version",versionString);
	        jsonObject.put("installSource",installSource);
	        jsonObject.put("publisher", publisherString);
	        jsonObject.put("uninstall", uninstallPathString);
	        resultString = JsonUtil.toJsonString(jsonObject);
	        
	        if (nameString==null) {
				return null;
			}else {
				 	i++;
			        System.out.println(resultString);
			        System.out.println(i);
			        return resultString;
			}
    }
    public static int numList(){
		int a = 0;
		
    	return a;
    }
    public static void main(String[] args) throws Exception {
		check();
	}
}  