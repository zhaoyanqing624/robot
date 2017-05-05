package org.xjtusicd3.partner.filter;

import java.util.List;

import com.registry.RegistryKey;
import com.registry.RegistryValue;

public class SystemDriver {

    public static void main(String[] args) {
//        System.loadLibrary("reg_x64.dll");
    	
    	
    	//获取CPU名称
        RegistryKey LOCALMACHINE = RegistryKey.getRootKeyForIndex(RegistryKey.HKEY_LOCAL_MACHINE_INDEX);
        RegistryKey rt = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Enum\\ACPI");
        List<RegistryKey> rts = rt.getSubKeys();
        for(RegistryKey rrt:rts){
        	if (rrt.getName().contains("GenuineIntel")) {
        		RegistryKey rt_1 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Enum\\ACPI\\"+rrt.getName());
        		List<RegistryKey> rts_1 = rt_1.getSubKeys();
        		RegistryKey rt_2 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Enum\\ACPI\\"+rrt.getName()+"\\"+rts_1.get(0).getName());
        		RegistryValue rValue = rt_2.getValue("FriendlyName");
        		String cpuName = rValue.toString().substring(rValue.toString().lastIndexOf("Value")+6).trim();
        		System.out.println(cpuName);
			}else {
			}
        }
        
        
        //获取计算机型号
        RegistryKey rt2 = new RegistryKey(LOCALMACHINE, "\\HARDWARE\\DESCRIPTION\\System\\BIOS");
        RegistryValue rValue2_1 = rt2.getValue("SystemProductName");
        RegistryValue rValue2_2 = rt2.getValue("BIOSReleaseDate");
        String PCnumber = rValue2_1.toString().substring(rValue2_1.toString().lastIndexOf("Value")+6).trim();
        String PCtime = rValue2_2.toString().substring(rValue2_2.toString().lastIndexOf("Value")+6).trim();
        System.out.println(PCnumber+PCtime);
        
        
        //显卡
        RegistryKey rt3 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E968-E325-11CE-BFC1-08002BE10318}");
        List<RegistryKey> rts3 = rt3.getSubKeys();
        for(RegistryKey rrt:rts3){
          	RegistryKey rt3_1 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E968-E325-11CE-BFC1-08002BE10318}\\"+rrt.getName());
          	String properties = rrt.getName();
        	if (properties.equals("Properties")) {
			}else {
        		List<RegistryKey> rtss3 = rt3_1.getSubKeys();
            	if (rtss3.size()>0) {
                    RegistryValue rValue3_1 = rt3_1.getValue("DriverDesc");
                    RegistryValue rValue3_2 = rt3_1.getValue("DriverVersion");
                    RegistryValue rValue3_3 = rt3_1.getValue("DriverDate");
                    String GraphicCard = rValue3_1.toString().substring(rValue3_1.toString().lastIndexOf("Value")+6).trim();
                    String GraphicCardversion = rValue3_2.toString().substring(rValue3_2.toString().lastIndexOf("Value")+6).trim();
                    String GraphicCardtime = rValue3_3.toString().substring(rValue3_3.toString().lastIndexOf("Value")+6).trim();
                    System.out.println("显卡："+GraphicCard+GraphicCardversion+GraphicCardtime);
    			}
			}
        }

        
        //声卡
        RegistryKey rt4 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E96C-E325-11CE-BFC1-08002BE10318}");
        List<RegistryKey> rts4 = rt4.getSubKeys();
        for(RegistryKey rrt:rts4){
        	RegistryKey rt4_1 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E96C-E325-11CE-BFC1-08002BE10318}\\"+rrt.getName());
        	String properties = rrt.getName();
        	if (properties.equals("Properties")) {
			}else {
        		List<RegistryKey> rtss4 = rt4_1.getSubKeys();
            	if (rtss4.size()>0) {
                    RegistryValue rValue4_1 = rt4_1.getValue("DriverDesc");
                    RegistryValue rValue4_2 = rt4_1.getValue("DriverVersion");
                    RegistryValue rValue4_3 = rt4_1.getValue("DriverDate");
                    String AudioCard = rValue4_1.toString().substring(rValue4_1.toString().lastIndexOf("Value")+6).trim();
                    String AudioCardversion = rValue4_2.toString().substring(rValue4_2.toString().lastIndexOf("Value")+6).trim();
                    String AudioCardtime = rValue4_3.toString().substring(rValue4_3.toString().lastIndexOf("Value")+6).trim();
                    System.out.println("声卡："+AudioCard+AudioCardversion+AudioCardtime);
    			}
			}
        }

        
        //存储
        RegistryKey rt5 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E96A-E325-11CE-BFC1-08002BE10318}");
        List<RegistryKey> rts5 = rt5.getSubKeys();
        for(RegistryKey rrt:rts5){
        	RegistryKey rt5_1 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E96A-E325-11CE-BFC1-08002BE10318}\\"+rrt.getName());
        	RegistryValue rV = rt5_1.getValue("CoInstallers32");
        	if (rV==null) {
        		if (rt5_1.getValue("DriverDesc")!=null) {
                    RegistryValue rValue5_1 = rt5_1.getValue("DriverDesc");
                    RegistryValue rValue5_2 = rt5_1.getValue("DriverVersion");
                    RegistryValue rValue5_3 = rt5_1.getValue("DriverDate");
                    String Storagename = rValue5_1.toString().substring(rValue5_1.toString().lastIndexOf("Value")+6).trim();
                    String Storageversion = rValue5_2.toString().substring(rValue5_2.toString().lastIndexOf("Value")+6).trim();
                    String Storagetime = rValue5_3.toString().substring(rValue5_3.toString().lastIndexOf("Value")+6).trim();
                    System.out.println("存储："+Storagename+Storageversion+Storagetime);
				}
			}
        }
        
        //主板
        RegistryKey rt6 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E97D-E325-11CE-BFC1-08002BE10318}");
        List<RegistryKey> rts6 = rt6.getSubKeys();
        for(RegistryKey rrt:rts6){
        	RegistryKey rt6_1 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E97D-E325-11CE-BFC1-08002BE10318}\\"+rrt.getName());
        	RegistryValue rV = rt6_1.getValue("WdTimerTic");
        	if (rV!=null) {
            	RegistryValue rValue6_1 = rt6_1.getValue("DriverDesc");
    	        RegistryValue rValue6_2 = rt6_1.getValue("DriverVersion");
    	        RegistryValue rValue6_3 = rt6_1.getValue("DriverDate");
    	        String Mainboard = rValue6_1.toString().substring(rValue6_1.toString().lastIndexOf("Value")+6).trim();
    	        String Mainboardversion = rValue6_2.toString().substring(rValue6_2.toString().lastIndexOf("Value")+6).trim();
    	        String Mainboardtime = rValue6_3.toString().substring(rValue6_3.toString().lastIndexOf("Value")+6).trim();
    	        System.out.println("主板："+Mainboard+Mainboardversion+Mainboardtime);
			}
        }
        
        
        //网卡
        RegistryKey rt7 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E972-E325-11CE-BFC1-08002BE10318}");
        List<RegistryKey> rts7 = rt7.getSubKeys();
        for(RegistryKey rrt:rts7){
        	RegistryKey rt7_1 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E972-E325-11CE-BFC1-08002BE10318}\\"+rrt.getName());
        	RegistryValue rValue7_IPv4 = rt7_1.getValue("*LsoV1IPv4");
        	RegistryValue rValue7_IPv6 = rt7_1.getValue("*LsoV2IPv6");
        	RegistryValue SSID = rt7_1.getValue("SSID");
        	if (SSID!=null) {
                RegistryValue rValue7_1 = rt7_1.getValue("DriverDesc");
                RegistryValue rValue7_2 = rt7_1.getValue("DriverVersion");
                RegistryValue rValue7_3 = rt7_1.getValue("DriverDate");
                String Network = rValue7_1.toString().substring(rValue7_1.toString().lastIndexOf("Value")+6).trim();
                String Networkversion = rValue7_2.toString().substring(rValue7_2.toString().lastIndexOf("Value")+6).trim();
                String Networktime = rValue7_3.toString().substring(rValue7_3.toString().lastIndexOf("Value")+6).trim();
                System.out.println("无线网卡："+Network+Networkversion+Networktime);
			}else {
	        	if (rValue7_IPv4!=null&&rValue7_IPv6!=null) {
	                RegistryValue rValue7_1 = rt7_1.getValue("DriverDesc");
	                RegistryValue rValue7_2 = rt7_1.getValue("DriverVersion");
	                RegistryValue rValue7_3 = rt7_1.getValue("DriverDate");
	                String Network = rValue7_1.toString().substring(rValue7_1.toString().lastIndexOf("Value")+6).trim();
	                String Networkversion = rValue7_2.toString().substring(rValue7_2.toString().lastIndexOf("Value")+6).trim();
	                String Networktime = rValue7_3.toString().substring(rValue7_3.toString().lastIndexOf("Value")+6).trim();
	                System.out.println("有线网卡："+Network+Networkversion+Networktime);
	  			}
			}

        }
        
        
    }
}
