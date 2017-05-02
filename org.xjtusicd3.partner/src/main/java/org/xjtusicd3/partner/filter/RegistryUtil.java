package org.xjtusicd3.partner.filter;

import java.io.UnsupportedEncodingException;

public class RegistryUtil {
    /**
     * 将dll获取的字符串拼接回原来的形式.
     * 因为dll内以前的方法只是单纯的将byte复制到java的char里
     * if ( uniBuf != NULL )
        {
        for ( i = 0 ; i < len ; ++i )
            uniBuf[i] = (jchar)  buf[i];
        result = (*env)->NewString( env, uniBuf, (jsize)len );
        free( uniBuf );
        }
        return result;
 
     * @param str    从dll获取的字符串
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String decode(String str) {
        String result = null;
        char[] charbuf = str.toCharArray();
        byte[] bytebuf = new byte[charbuf.length];
        for(int i=0;i<charbuf.length;i++){
            bytebuf[i] = (byte)charbuf[i];
        }
        try {
            result = new String(bytebuf,"GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
     
    /**
     * 相反要传入中文的字符来操作,需要修改中文为他所识别的乱码...即将中文按两字节一个,拆分开
     * @param str
     * @return
     */
    public static String encode(String str) {
        byte[] bytebuf = null;
        try {
            bytebuf = str.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        char[] charbuf = new char[bytebuf.length];
        for(int i=0;i<bytebuf.length;i++){
            charbuf[i] = (char)bytebuf[i];
        }
        return new String(charbuf,0,charbuf.length);
    }
}
