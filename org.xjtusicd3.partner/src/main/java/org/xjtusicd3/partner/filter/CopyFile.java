package org.xjtusicd3.partner.filter;

import java.io.File;

public class CopyFile {
	public String copyFile(String path,String useremail,String time) {
		File fold = new File(path);
		String newPath="E:\\eclipse\\workspace\\xiaoduo-master\\org.xjtusicd3.partner\\src\\main\\webapp\\robot\\user\\"+useremail+"\\userImage\\"+time+"\\";
		File file = new File(newPath);
		if(!file.exists()){
			file.mkdirs();
		}
		String newPath1 = newPath+fold.getName();
		File file2 = new File(newPath+fold.getName());
		fold.renameTo(file2);
		return newPath1;
		} 
}
