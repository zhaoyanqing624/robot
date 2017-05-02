package org.xjtusicd3.partner.filter;

import java.util.List;

import com.registry.RegistryKey;
import com.registry.RegistryValue;

public class SystemSoftware {

    public static void main(String[] args) {
//        System.loadLibrary("reg_x64.dll");
        RegistryKey LOCALMACHINE = RegistryKey.getRootKeyForIndex(RegistryKey.HKEY_LOCAL_MACHINE_INDEX);
        
        RegistryKey rt = new RegistryKey(LOCALMACHINE, "\\SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall");
        
        List<RegistryKey> rts = rt.getSubKeys();
        for(RegistryKey rrt:rts){
            System.out.println(rrt.getName());
            if(rrt.hasSubKeys()){
                RegistryKey rrtt = rrt.getSubKey(rrt.getName());
                System.out.println(rrtt.getName());
            }else{
                RegistryValue rv = rrt.getValue("DisplayName");
                if (rv==null) {
				}else {
	                String path = rv.toString().substring(rv.toString().lastIndexOf("Value")+6).trim();
	                System.out.println("名称："+path);
	                System.out.println("------------------------");
				}
            }
            
        }
    }
}
