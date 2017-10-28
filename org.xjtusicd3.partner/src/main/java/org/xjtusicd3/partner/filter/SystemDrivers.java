//package org.xjtusicd3.partner.filter;
//
//import java.util.List;
//
//import com.registry.RegistryKey;
//import com.registry.RegistryValue;
//
//public class SystemDrivers {
////    	public static void main(String[] args) {
////    		System.out.println(getCPU());
////    		System.out.println(getEquipmentModel());
////    		System.out.println(getGraphicCard());
////    		System.out.println(getAudioCard());
////    		System.out.println(getAudioCard());
////    		System.out.println(getStorage());
////    		getMotherBoard();
////    		System.out.println(getMotherBoard());
////    		System.out.println(getOSID());
////System.out.println(getMotherBoard());
////		}
//		public static String teString(){
//			return "zhao";
//		}
//    	public static String getCPU(){
//        	//获取CPU名称
//    		String cpuName="";
//            RegistryKey LOCALMACHINE = RegistryKey.getRootKeyForIndex(RegistryKey.HKEY_LOCAL_MACHINE_INDEX);
//            RegistryKey rt = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Enum\\ACPI");
//            List<RegistryKey> rts = rt.getSubKeys();
//            for(RegistryKey rrt:rts){
//            	if (rrt.getName().contains("GenuineIntel")) {
//            		RegistryKey rt_1 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Enum\\ACPI\\"+rrt.getName());
//            		List<RegistryKey> rts_1 = rt_1.getSubKeys();
//            		RegistryKey rt_2 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Enum\\ACPI\\"+rrt.getName()+"\\"+rts_1.get(0).getName());
//            		RegistryValue rValue = rt_2.getValue("FriendlyName");
//            		cpuName = rValue.toString().substring(rValue.toString().lastIndexOf("Value")+6).trim();
//    			}else {
//    			}
//            }
//            return cpuName;
//    	}
//
//        
//        public static String getEquipmentModel(){
//            //获取计算机型号
//        	RegistryKey LOCALMACHINE = RegistryKey.getRootKeyForIndex(RegistryKey.HKEY_LOCAL_MACHINE_INDEX);
//            RegistryKey rt2 = new RegistryKey(LOCALMACHINE, "\\HARDWARE\\DESCRIPTION\\System\\BIOS");
//            RegistryValue rValue2_1 = rt2.getValue("SystemProductName");
//            RegistryValue rValue2_2 = rt2.getValue("BIOSReleaseDate");
//            String PCnumber = rValue2_1.toString().substring(rValue2_1.toString().lastIndexOf("Value")+6).trim();
//            String PCtime = rValue2_2.toString().substring(rValue2_2.toString().lastIndexOf("Value")+6).trim();
//            return PCnumber+","+PCtime;
//        }
//
//        
//        public static String getGraphicCard(){
//            //显卡
//        	String GraphicCard="";
//        	String GraphicCardversion="";
//        	String GraphicCardtime="";
//        	RegistryKey LOCALMACHINE = RegistryKey.getRootKeyForIndex(RegistryKey.HKEY_LOCAL_MACHINE_INDEX);
//            RegistryKey rt3 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E968-E325-11CE-BFC1-08002BE10318}");
//            List<RegistryKey> rts3 = rt3.getSubKeys();
//            for(RegistryKey rrt:rts3){
//              	RegistryKey rt3_1 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E968-E325-11CE-BFC1-08002BE10318}\\"+rrt.getName());
//              	String properties = rrt.getName();
//            	if (properties.equals("Properties")) {
//    			}else {
//            		List<RegistryKey> rtss3 = rt3_1.getSubKeys();
//                	if (rtss3.size()>0) {
//                        RegistryValue rValue3_1 = rt3_1.getValue("DriverDesc");
//                        RegistryValue rValue3_2 = rt3_1.getValue("DriverVersion");
//                        RegistryValue rValue3_3 = rt3_1.getValue("DriverDate");
//                        	GraphicCard = rValue3_1.toString().substring(rValue3_1.toString().lastIndexOf("Value")+6).trim();
//                        	GraphicCardversion = rValue3_2.toString().substring(rValue3_2.toString().lastIndexOf("Value")+6).trim();
//                        	GraphicCardtime = rValue3_3.toString().substring(rValue3_3.toString().lastIndexOf("Value")+6).trim();
//        			}
//    			}
//            }
//            return GraphicCard+","+GraphicCardversion+","+GraphicCardtime;
//        }
//
//
//        public static String getAudioCard(){
//            //声卡
//        	String AudioCard="";
//        	String AudioCardversion="";
//        	String AudioCardtime="";
//        	RegistryKey LOCALMACHINE = RegistryKey.getRootKeyForIndex(RegistryKey.HKEY_LOCAL_MACHINE_INDEX);
//            RegistryKey rt4 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E96C-E325-11CE-BFC1-08002BE10318}");
//            List<RegistryKey> rts4 = rt4.getSubKeys();
//            for(RegistryKey rrt:rts4){
//            	RegistryKey rt4_1 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E96C-E325-11CE-BFC1-08002BE10318}\\"+rrt.getName());
//            	String properties = rrt.getName();
//            	if (!(properties.equals("Properties"))) {
//            		List<RegistryKey> rtss4 = rt4_1.getSubKeys();
//                	if (rtss4.size()>0) {
//                        RegistryValue rValue4_1 = rt4_1.getValue("DriverDesc");
//                        RegistryValue rValue4_2 = rt4_1.getValue("DriverVersion");
//                        RegistryValue rValue4_3 = rt4_1.getValue("DriverDate");
//                        	 AudioCard = rValue4_1.toString().substring(rValue4_1.toString().lastIndexOf("Value")+6).trim();
//                        	 AudioCardversion = rValue4_2.toString().substring(rValue4_2.toString().lastIndexOf("Value")+6).trim();
//                        	 AudioCardtime = rValue4_3.toString().substring(rValue4_3.toString().lastIndexOf("Value")+6).trim();
//        			}
//            	}
//            }
//            return AudioCard+","+AudioCardversion+","+AudioCardtime;
//        }
//
//
//        public static String getStorage(){
//            //存储
//        	String Storagename="";
//        	String Storageversion="";
//        	String Storagetime="";
//        	
//        	RegistryKey LOCALMACHINE = RegistryKey.getRootKeyForIndex(RegistryKey.HKEY_LOCAL_MACHINE_INDEX);
//            RegistryKey rt5 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E96A-E325-11CE-BFC1-08002BE10318}");
//            List<RegistryKey> rts5 = rt5.getSubKeys();
//            for(RegistryKey rrt:rts5){
//            	RegistryKey rt5_1 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E96A-E325-11CE-BFC1-08002BE10318}\\"+rrt.getName());
//            	RegistryValue rV = rt5_1.getValue("CoInstallers32");
//            	if (rV==null) {
//            		if (rt5_1.getValue("DriverDesc")!=null) {
//                        RegistryValue rValue5_1 = rt5_1.getValue("DriverDesc");
//                        RegistryValue rValue5_2 = rt5_1.getValue("DriverVersion");
//                        RegistryValue rValue5_3 = rt5_1.getValue("DriverDate");
//                        	 Storagename = rValue5_1.toString().substring(rValue5_1.toString().lastIndexOf("Value")+6).trim();
//                        	 Storageversion = rValue5_2.toString().substring(rValue5_2.toString().lastIndexOf("Value")+6).trim();
//                        	 Storagetime = rValue5_3.toString().substring(rValue5_3.toString().lastIndexOf("Value")+6).trim();
//    				}
//    			}
//            }
//            return Storagename+","+Storageversion+","+Storagetime;
//        }
//
////        public static String getMotherBoard(){
////            //主板
////        	String Mainboard="";
////        	String Mainboardversion="";
////        	String Mainboardtime="";
////        	RegistryKey LOCALMACHINE = RegistryKey.getRootKeyForIndex(RegistryKey.HKEY_LOCAL_MACHINE_INDEX);
////            RegistryKey rt6 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E97D-E325-11CE-BFC1-08002BE10318}");
////            List<RegistryKey> rts6 = rt6.getSubKeys();
////            for(RegistryKey rrt:rts6){
////            	String properties = rrt.getName();
////            	RegistryKey rt6_1 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E97D-E325-11CE-BFC1-08002BE10318}\\"+rrt.getName());
////            	RegistryValue rV = rt6_1.getValue("WdTimerTic");
////            	if (rV!=null) {
////            		RegistryValue rValue6_1 = rt6_1.getValue("DriverDesc");
////        	        RegistryValue rValue6_2 = rt6_1.getValue("DriverVersion");
////        	        RegistryValue rValue6_3 = rt6_1.getValue("DriverDate");
////		        	 Mainboard = rValue6_1.toString().substring(rValue6_1.toString().lastIndexOf("Value")+6).trim();
////		        	 Mainboardversion = rValue6_2.toString().substring(rValue6_2.toString().lastIndexOf("Value")+6).trim();
////		        	 Mainboardtime = rValue6_3.toString().substring(rValue6_3.toString().lastIndexOf("Value")+6).trim();
////				}
////            }
////			return Mainboard+","+Mainboardversion+","+Mainboardtime;
////        }
//        public static String getMotherBoard(){
//            //主板
//        	String Mainboard="";
//        	String Mainboardversion="";
//        	String Mainboardtime="";
//        	RegistryKey LOCALMACHINE = RegistryKey.getRootKeyForIndex(RegistryKey.HKEY_LOCAL_MACHINE_INDEX);
//            RegistryKey rt6 = new RegistryKey(LOCALMACHINE, "\\HARDWARE\\DESCRIPTION\\System\\BIOS");
//            RegistryValue value = rt6.getValue("BIOSVendor");
//            RegistryValue value2 = rt6.getValue("BaseBoardProduct");
//            RegistryValue value3 = rt6.getValue("BIOSReleaseDate");
//            String producter = value.toString().substring(value.toString().lastIndexOf("Value")+6).trim();
//            String BIOS = value2.toString().substring(value2.toString().lastIndexOf("Value")+6).trim();
//            String time = value3.toString().substring(value3.toString().lastIndexOf("Value")+6).trim();
//            
//			return producter+","+BIOS+","+time;
//        }
//        
//        public static String getNetworkCard1(){
//            //有限网卡
//        	String network="";
//        	RegistryKey LOCALMACHINE = RegistryKey.getRootKeyForIndex(RegistryKey.HKEY_LOCAL_MACHINE_INDEX);
//            RegistryKey rt7 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E972-E325-11CE-BFC1-08002BE10318}");
//            List<RegistryKey> rts7 = rt7.getSubKeys();
//            for(RegistryKey rrt:rts7){
//            	RegistryKey rt7_1 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E972-E325-11CE-BFC1-08002BE10318}\\"+rrt.getName());
//            	RegistryValue rValue7_IPv4 = rt7_1.getValue("*LsoV1IPv4");
//            	RegistryValue rValue7_IPv6 = rt7_1.getValue("*LsoV2IPv6");
//            	RegistryValue SSID = rt7_1.getValue("SSID");
//            	if (SSID==null) {
//            		if (rValue7_IPv4!=null&&rValue7_IPv6!=null) {
//    	                RegistryValue rValue7_1 = rt7_1.getValue("DriverDesc");
//    	                RegistryValue rValue7_2 = rt7_1.getValue("DriverVersion");
//    	                RegistryValue rValue7_3 = rt7_1.getValue("DriverDate");
//    	                String Network = rValue7_1.toString().substring(rValue7_1.toString().lastIndexOf("Value")+6).trim();
//    	                String Networkversion = rValue7_2.toString().substring(rValue7_2.toString().lastIndexOf("Value")+6).trim();
//    	                String Networktime = rValue7_3.toString().substring(rValue7_3.toString().lastIndexOf("Value")+6).trim();
//    	                network = Network+","+Networkversion+","+Networktime;
//    	  			}
//    			}
//
//            }
//			return network;
//        }
//        
//        public static String getNetworkCard2(){
//            //无线网卡
//        	String network="";
//        	RegistryKey LOCALMACHINE = RegistryKey.getRootKeyForIndex(RegistryKey.HKEY_LOCAL_MACHINE_INDEX);
//            RegistryKey rt7 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E972-E325-11CE-BFC1-08002BE10318}");
//            List<RegistryKey> rts7 = rt7.getSubKeys();
//            for(RegistryKey rrt:rts7){
//            	RegistryKey rt7_1 = new RegistryKey(LOCALMACHINE, "\\SYSTEM\\ControlSet001\\Control\\Class\\{4D36E972-E325-11CE-BFC1-08002BE10318}\\"+rrt.getName());
//            	RegistryValue rValue7_IPv4 = rt7_1.getValue("*LsoV1IPv4");
//            	RegistryValue rValue7_IPv6 = rt7_1.getValue("*LsoV2IPv6");
//            	RegistryValue SSID = rt7_1.getValue("SSID");
//            	if (SSID!=null) {
//                    RegistryValue rValue7_1 = rt7_1.getValue("DriverDesc");
//                    RegistryValue rValue7_2 = rt7_1.getValue("DriverVersion");
//                    RegistryValue rValue7_3 = rt7_1.getValue("DriverDate");
//                    String Network = rValue7_1.toString().substring(rValue7_1.toString().lastIndexOf("Value")+6).trim();
//                    String Networkversion = rValue7_2.toString().substring(rValue7_2.toString().lastIndexOf("Value")+6).trim();
//                    String Networktime = rValue7_3.toString().substring(rValue7_3.toString().lastIndexOf("Value")+6).trim();
//                    network = Network+","+Networkversion+","+Networktime;
//    			}
//            }
//			return network;
//        }
//        
//        //获得操作系统的ID
//     public static String getOSID(){
//    	 RegistryKey LOCALMACHINE = RegistryKey.getRootKeyForIndex(RegistryKey.HKEY_LOCAL_MACHINE_INDEX);
//    	 RegistryKey rt7 = new RegistryKey(LOCALMACHINE, "\\SOFTWARE\\Microsoft\\Internet Explorer\\Registration");
//    	 RegistryValue rValue = rt7.getValue("ProductId");
//    	 String osid = rValue.toString().substring(rValue.toString().lastIndexOf("Value")+6).trim();
//    	 return osid;
//    	   
//       }
//        
//        
//}
