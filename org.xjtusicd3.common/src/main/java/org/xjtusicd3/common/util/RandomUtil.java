package org.xjtusicd3.common.util;

import java.util.Random;

public class RandomUtil {

	public static String getRandomStr(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();  
	}

}
