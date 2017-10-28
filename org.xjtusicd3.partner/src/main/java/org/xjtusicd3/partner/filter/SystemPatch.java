//package org.xjtusicd3.partner.filter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.registry.RegistryKey;
//import com.registry.RegistryValue;
//
//public class SystemPatch {
//	public static List<String> PatchList(){
////      System.loadLibrary("reg_x64.dll");
//      RegistryKey LOCALMACHINE = RegistryKey.getRootKeyForIndex(RegistryKey.HKEY_LOCAL_MACHINE_INDEX);
//      ArrayList list = new ArrayList<>();
//      RegistryKey rt = new RegistryKey(LOCALMACHINE, "\\SOFTWARE\\Wow6432Node\\Microsoft\\Updates");
//      List<RegistryKey> rts = rt.getSubKeys();
//      for(RegistryKey rrt:rts){
//          RegistryKey rKey = new RegistryKey(LOCALMACHINE, "\\SOFTWARE\\Wow6432Node\\Microsoft\\Updates\\"+rrt.getName());
//          List<RegistryKey> rKeys = rKey.getSubKeys();
//          for(RegistryKey rrt2:rKeys){
//          if(rrt2.hasSubKeys()){
//              RegistryKey rrtt = rrt2.getSubKey(rrt2.getName());
//          }else{
//              RegistryValue rv = rrt2.getValue("PackageName");
//              if (rv==null) {
//				}else {
//	                String path = rv.toString().substring(rv.toString().lastIndexOf("Value")+6).trim();
////	                System.out.println("编号"+rrt2.getName());
////	                System.out.println("名称："+path);
////	                System.out.println("------------------------");
//	                list.add(path);
//				}
//          }
//          }
//      }
//	return list;      
//      }
//}
