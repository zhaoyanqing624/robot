package org.xjtusicd3.partner.filter;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
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
        System.out.println("本地ip地址:    " + ip);
        System.out.println("操作系统的名称：    " + props.getProperty("os.name"));
        return ip+","+props.getProperty("os.name");
    }
    
}
