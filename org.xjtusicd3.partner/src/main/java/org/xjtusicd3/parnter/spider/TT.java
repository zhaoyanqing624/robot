package org.xjtusicd3.parnter.spider;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Frank
 * �滻ƥ����ı�
 */
public class TT {
	
    public static String zhuanyi(String string){
    	string = string.replace("\"", "\\\"");
    	return string;
    }
    public static void main(String[] args) {
    	String test1 = "zyq";
        // ����һ��������ʽģʽ������ƥ��һ�����ʣ�\b=���ʱ߽磩
        String patt = "er\\d*.*4";

        // ���ڲ��Ե������ַ���
        String input = "er98980938\"zhao\"20495z809348095";
        String iString = zhuanyi(input);
        System.out.println("Input:" + input);
        System.out.println("zhao:" + iString);

        // ��������ʽʵ�������з������鿴���������
        Pattern r = Pattern.compile(patt);
        Matcher m = r.matcher(input);
        while(m.find()){
        	System.out.println("ReplaceAll:" + m.group());
        }
		
        

//        // appendReplacement����
//        m.reset();
//        StringBuffer sb = new StringBuffer();
//        while (m.find()) {
//            // ��ƥ��֮ǰ���ַ������Ƶ�sb,�ٽ�ƥ�����滻Ϊ��"favour"����׷�ӵ�sb
//            m.appendReplacement(sb, "favour");
//        }
//        System.out.println(sb.toString());
//        m.appendTail(sb);
//        System.out.println(sb.toString());
    }
}