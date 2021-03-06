package com.fms.commons;

/**
 * 原料成品标记
 * @author Administrator
 *
 */
public class ImgExgFlag {
	/**
	 * 原料
	 */
	public static final String IMG = "I";
	/**
	 * 成品
	 */
	public static final String EXG = "E";
	
	/**
	 * 把值翻译成中文
	 * @param value
	 * @return
	 */
	public static String descValue(String value){
		if(null!=value && !"".equals(value.trim())){
			if(value.trim().equals(ImgExgFlag.IMG)){
				return "原料";
			}else if(value.trim().equals(ImgExgFlag.EXG)){
				return "成品";
			}
		}
		return null;
	}
	
	/**
	 * 把中文解析成值
	 * @param value
	 * @return
	 */
	public static String parseValue(String value){
		if(null!=value && !"".equals(value.trim())){
			if(value.trim().equals("原料")){
				return ImgExgFlag.IMG;
			}else if(value.trim().equals("成品")){
				return ImgExgFlag.EXG;
			}
		}
		return null;
	}
}
