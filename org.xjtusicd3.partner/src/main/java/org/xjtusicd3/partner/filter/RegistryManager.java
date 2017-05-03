package org.xjtusicd3.partner.filter;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
 
 
import com.ice.jni.registry.NoSuchKeyException;
import com.ice.jni.registry.NoSuchValueException;
import com.ice.jni.registry.Registry;
import com.ice.jni.registry.RegistryException;
import com.ice.jni.registry.RegistryKey;
import com.ice.jni.registry.RegistryValue;
 
public class RegistryManager {
	
	public static String encode(String str){
		char []c=str.toCharArray();
		byte []b=new byte[c.length];
		for(int i=0;i<c.length;i++){
		b[i]=(byte) c[i];
		}
		String newStr=null;
		try {
		newStr=new String(b,"gbk");
		} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
		}
		return newStr;
		}  
	
    public static void main(String[] args) throws NoSuchKeyException,RegistryException {
        RegistryKey registryKey = Registry.openSubkey(Registry.HKEY_LOCAL_MACHINE, "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Uninstall",RegistryKey.ACCESS_READ);
         
        Enumeration<?> enums = registryKey.keyElements();
        // 360保险箱 0xB1A3 0xCFD5 0xCFE4
        while(enums.hasMoreElements()) {
            String childRKName = (String)enums.nextElement();
             
//            if(childRKName.equals("360安全卫士")) {
                RegistryKey rk = registryKey.openSubKey(encode(childRKName), RegistryKey.ACCESS_READ);
                RegistryValue rv = null;
                try {
                    rv = rk.getValue("DisplayName");
                } catch (NoSuchValueException e1) {
                    // 仅仅 catch 运行时异常的话会终止程序运行，只有具体到是哪种 Exception，才会继续执行~
                    e1.printStackTrace();
                }
                 
                // 如果上句代码抛出异常，则这里的rv必定为null，不做处理的话必然会报空指针~
                if(rv == null) continue;
                String s = null;
                try {
                    s = new String(rv.getByteData(), "GBK");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
//               // System.out.println(s + " ====================== " + 
//                        RegistryUtil.decode(new String(rv.getByteData())));
//            }
        }
    }
}
