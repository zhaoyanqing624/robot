package org.xjtusicd3.partner.filter;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**MD5算法加密
 *  author  zhaoyanqing
 *  date  2017年8月1日 09:32:13
 */
public class MD5 {
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        //加密后的字符串
        String newstr=new String(md5.digest(str.getBytes("utf-8")));
        byte[] b = md5.digest(str.getBytes("utf-8"));
        String result = "";
    	for (int i = 0; i < b.length; i++) {
    	    String tmp = Integer.toHexString(b[i] & 0xFF);
    	    if (tmp.length() == 1) {
    		result += "0" + tmp;
    	    } else {
    		result += tmp;
    	    }
    	}
        return result;
    }
}
