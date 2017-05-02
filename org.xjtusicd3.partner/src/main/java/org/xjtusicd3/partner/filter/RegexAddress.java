package org.xjtusicd3.partner.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexAddress {
	public static String province(String string){
		String str = string;
		String regex = "0(.*)1";
		String province = "";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
			province = matcher.group(1);
		}
		return province;
	}
	public static String city(String string){
		String str = string;
		String regex = "1(.*)2";
		String city = "";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
			city = matcher.group(1);
		}
		return city;
	}
	public static String district(String string){
		String str = string;
		String regex = "2(.*)3";
		String district = "";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
			district = matcher.group(1);
		}
		return district;
	}
	public static String replaceAddress(String string){
		String str  = string;
		String address = str.replaceAll("\\d", "");
		return address;
	}
}
