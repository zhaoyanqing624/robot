package org.xjtusicd3.partner.filter;

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
    private static String memory() throws SigarException {
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        // 内存总量
        String ram =  mem.getTotal() / 1024L/1000 + "MB";
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
        String total = totalsize / 1024L/1024 + "GB";
		return total;
    	
    }
    
}
