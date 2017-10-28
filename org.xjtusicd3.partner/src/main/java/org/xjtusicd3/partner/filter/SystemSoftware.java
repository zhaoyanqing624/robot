//package org.xjtusicd3.partner.filter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.registry.RegistryKey;
//import com.registry.RegistryValue;
//
//public class SystemSoftware {
//
//    public static List<String> SoftList() {
////        System.loadLibrary("reg_x64.dll");
//    	String result="";
//        RegistryKey LOCALMACHINE = RegistryKey.getRootKeyForIndex(RegistryKey.HKEY_LOCAL_MACHINE_INDEX);
//        ArrayList list = new ArrayList<>();
//        RegistryKey rt = new RegistryKey(LOCALMACHINE, "\\SOFTWARE\\Wow6432Node\\Microsoft\\Windows\\CurrentVersion\\Uninstall");
//        
//        List<RegistryKey> rts = rt.getSubKeys();
//        for(RegistryKey rrt:rts){
//            if(rrt.hasSubKeys()){
//                RegistryKey rrtt = rrt.getSubKey(rrt.getName());
//            }else{
//                RegistryValue rv = rrt.getValue("DisplayName");
//                RegistryValue rv2 = rrt.getValue("DisplayVersion");
//                if (rv==null) {
//				}else {
//					if (rv2!=null) {
//		                String path = rv.toString().substring(rv.toString().lastIndexOf("Value")+6).trim();
//		                String path2 = rv2.toString().substring(rv2.toString().lastIndexOf("Value")+6).trim();
//		                String soft = path+","+path2;
//		                list.add(soft);
////		                System.out.println(path2);
////		                System.out.println("------------------------");
//					}
//				}
//            }
//            
//        }
//		return list;
//    }
//}
