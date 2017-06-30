package org.xjtusicd3.partner.filter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class ReadTxt {
	public static void main(String args[])throws Exception{
		File file = new File("E:\\computer.txt");//Text文件
		BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
		String s = null;
		String aString = "";
		while((s = br.readLine())!=null){//使用readLine方法，一次读一行
			System.out.println(s);
			aString = aString +s+"\tn\t1\r\n";
		}
		File dic = new File("E:\\computer.dic");
		if(!dic.exists()){
			dic.createNewFile();
		}
		byte bytes[]=new byte[512];
		bytes=aString.getBytes(); //新加的
		FileOutputStream fos=new FileOutputStream(dic);
		fos.write(bytes);
		fos.close();
		br.close();;
	}
}
