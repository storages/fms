package com.fms.utils;

public class ServerUtils {
	/**
	 * 判断是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){ 
		String reg = "\\d+(\\.\\d+)?"; 
	    return str.trim().matches(reg);  
	 } 

}
