package org.xjtusicd3.partner.filter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;

public class ReadTxt {
	public static void main(String args[])throws Exception{
		File file = new File("E:\\faqKeywords.txt");//Text文件
		BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
		String s = null;
		String aString = "";
		while((s = br.readLine())!=null){//使用readLine方法，一次读一行
			System.out.println(s);
			String[] resultArray = s.split("\t");
			System.out.println(resultArray[1]);
		}
		br.close();
		
		//增加字段文件
//		FileWriter fileWriter = new FileWriter("E:\\faqKeywords.txt");
//		BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\faqKeywords.txt",true),"UTF-8"));
//		String string = "1231231231" + "\t我怎么回事\r\n";
//		fileWriter.write(string);
//		fileWriter.flush();
//		fileWriter.close(); 
	}
}
