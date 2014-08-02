package com.fms.commons;

/**
 * 客户供应商常量类
 * @author Administrator
 *
 */
public class ApplyType {

	/**
	 * 供应商
	 */
	public static final String APPLY = "0";
	/**
	 * 客户
	 */
	public static final String CUSSTOM = "1";
	
	 /**
	  * 把值转换成对应的名称
	  * @param value
	  * @return
	  */
	 public static String valueToKey(String value){
		 String returnKey = "";
		 if(ApplyType.APPLY.equals(value)){
			 returnKey = "供应商";
		 }else if(ApplyType.CUSSTOM.equals(value)){
			 returnKey = "客户";
		 }
		 return returnKey;
	 }
	 
	 
	 /**
	  * 把名称转换成对应的值
	  * @param key
	  * @return
	  */
	 public static String keyToValue(String key){
		 String returnValue = "";
		 if("供应商".equals(key)){
			 returnValue = ApplyType.APPLY;
		 }else if("客户".equals(key)){
			 returnValue = ApplyType.CUSSTOM;
		 }
		 return returnValue;
	 }
}
