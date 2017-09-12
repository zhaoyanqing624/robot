package org.xjtusicd3.partner.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zxzz {
	public static void main(String[] args) {
		String s = "abcxyz123";
		String[] dict = {"cxy","123"};
		String s2 = "aaabbcc";
		String[] dict2 = {"aaa","bbc"};
		result(s2, dict2);
		
		System.out.println(getCharacterPosition(s2, 1, dict2[1]));
	}
	
	
	public static void result(String s,String[] dict){
		int temp=0;
		for(int i=0;i<dict.length;i++){
			String string = dict[i];
			if (s.indexOf(string)!=-1) {
				temp = getCharacterPosition(s, 1, dict[i]) + dict[i].length();
				System.err.println("zzz"+temp);
				if (getCharacterPosition(s, 1, dict[i])>=temp) {
					
				}else{
					
				}
				String result = s.replace(string, "<b>"+string+"</b>");
				s = result;
				System.out.println(result);
			}
		}
		
	}
	
    public static int getCharacterPosition(String string ,int i,String character){   
        Matcher slashMatcher = Pattern.compile(character).matcher(string);  
        int mIdx = 0;  
        while(slashMatcher.find()) {  
           mIdx++;  
           if(mIdx == i){  
              break;  
           }  
        }  
        return slashMatcher.start();  
     } 
	
}
