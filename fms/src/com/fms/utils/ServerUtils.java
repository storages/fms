package com.fms.utils;

public class ServerUtils {
	/**
	 * 判断是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (null != str && !"".equals(str)) {
			String reg = "\\d+(\\.\\d+)?";
			return str.trim().matches(reg);
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(isNumeric(null));
		System.out.println(-1.0 < 0.0);
	}

}
