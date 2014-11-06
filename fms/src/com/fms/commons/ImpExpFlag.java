package com.fms.commons;

/**
 * 进出库标记
 * @author Administrator
 *
 */
public class ImpExpFlag {

	/**
	 * 进库
	 */
	public static final String INSTORAGE = "0";
	/**
	 * 出库
	 */
	public static final String OUTSTORAGE = "1";
	
	/**
	 * 根据标识符翻译成中文意思
	 * @return
	 */
	public static String descImpExpByValue(String value){
		if(null!=value && !"".equals(value.trim())){
			if(value.trim().equals(ImgExgFlag.IMG)){
				return "进库";
			}else if(value.trim().equals(ImgExgFlag.EXG)){
				return "出库";
			}
		}
		return null;
	}
	
	/**
	 * 根据中文意思解析成标识符
	 * @param value
	 * @return
	 */
	public static String getImpExpByValue(String value){
		if(null!=value && !"".equals(value.trim())){
			if(value.trim().equals("进库")){
				return ImpExpFlag.INSTORAGE;
			}else if(value.trim().equals("出库")){
				return ImpExpFlag.OUTSTORAGE;
			}
		}
		return null;
	}
}
