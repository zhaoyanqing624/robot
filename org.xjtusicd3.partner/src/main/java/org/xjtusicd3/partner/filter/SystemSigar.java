package org.xjtusicd3.partner.filter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class SystemSigar {
	public static void main(String[] args) throws SigarException {
		file();
	}
	//获取内存
	public static String memory() throws SigarException {
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        // 内存总量
        String ram =  Math.ceil((mem.getTotal() / 1024L/1024/1024)) + "GB";
		return ram;
    }
    
    //获取硬盘
    public static String file() throws SigarException{
    	long totalsize=0;
        Sigar sigar = new Sigar();
        FileSystem fslist[] = sigar.getFileSystemList();
        for (int i = 0; i < fslist.length; i++) {
            FileSystem fs = fslist[i];
            if (fs.getSysTypeName().equals("NTFS")) {
                FileSystemUsage usage = null;
                usage = sigar.getFileSystemUsage(fs.getDirName());
                totalsize =totalsize + usage.getTotal();
			}else {
				
			}
        }
        String total = Math.ceil((totalsize / 1024L/1024)) + "GB";
		return total;
    	
    }
    
    //
    public static String property() throws UnknownHostException {
        Runtime r = Runtime.getRuntime();
        Properties props = System.getProperties();
        InetAddress addr;
        addr = InetAddress.getLocalHost();
        String ip = addr.getHostAddress();
        Map<String, String> map = System.getenv();
        return ip;
    }
    
    public static String os() {
        OperatingSystem OS = OperatingSystem.getInstance();
//        // 操作系统内核类型如： 386、486、586等x86
//        System.out.println("操作系统:    " + OS.getArch());
//        System.out.println("操作系统CpuEndian():    " + OS.getCpuEndian());//
//        System.out.println("操作系统DataModel():    " + OS.getDataModel());//
//        // 系统描述
//        System.out.println("操作系统的描述:    " + OS.getDescription());
//        // 操作系统类型
//        // System.out.println("OS.getName():    " + OS.getName());
//        // System.out.println("OS.getPatchLevel():    " + OS.getPatchLevel());//
//        // 操作系统的卖主
//        System.out.println("操作系统的卖主:    " + OS.getVendor());
//        // 卖主名称
//        System.out.println("操作系统的卖主名:    " + OS.getVendorCodeName());
//        // 操作系统名称
//        System.out.println("操作系统名称:    " + OS.getVendorName());
//        // 操作系统卖主类型
//        System.out.println("操作系统卖主类型:    " + OS.getVendorVersion());
//        // 操作系统的版本号
//        System.out.println("操作系统的版本号:    " + OS.getVersion());
        return OS.getVendorName()+","+OS.getArch()+","+OS.getVersion();
    }
    
}
