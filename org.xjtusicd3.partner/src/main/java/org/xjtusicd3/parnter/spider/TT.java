package org.xjtusicd3.parnter.spider;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Frank
 * 替换匹配的文本
 */
public class TT {
	
    public static String zhuanyi(String string){
    	string = string.replace("\"", "\\\"");
    	return string;
    }
    public static void main(String[] args) {
    	String test1 = "zyq";
        // 创建一个正则表达式模式，用以匹配一个单词（\b=单词边界）
        String patt = "er\\d*.*4";

        // 用于测试的输入字符串
        String input = "er98980938\"zhao\"20495z809348095";
        String iString = zhuanyi(input);
        System.out.println("Input:" + input);
        System.out.println("zhao:" + iString);

        // 从正则表达式实例中运行方法并查看其如何运行
        Pattern r = Pattern.compile(patt);
        Matcher m = r.matcher(input);
        while(m.find()){
        	System.out.println("ReplaceAll:" + m.group());
        }
		
        

//        // appendReplacement方法
//        m.reset();
//        StringBuffer sb = new StringBuffer();
//        while (m.find()) {
//            // 将匹配之前的字符串复制到sb,再将匹配结果替换为："favour"，并追加到sb
//            m.appendReplacement(sb, "favour");
//        }
//        System.out.println(sb.toString());
//        m.appendTail(sb);
//        System.out.println(sb.toString());
    }
}