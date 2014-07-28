package com.fms.commons;

/**
 * 员工性别
 * @author Administrator
 *
 */
public class EmployeeGender {

	/**
	 * 女性
	 */
	public final static String FEMALE ="0";
	/**
	 * 男性
	 */
	 public final static String MALE ="1";
	 /**
	  * 其它
	  */
	 public final static String OTHER = "2";
	 
	 
	 /**
	  * 把值转换成对应的名称
	  * @param value
	  * @return
	  */
	 public static String valueToKey(String value){
		 String returnKey = "";
		 if(EmployeeGender.FEMALE.equals(value)){
			 returnKey = "女性";
		 }else if(EmployeeGender.MALE.equals(value)){
			 returnKey = "男性";
		 }else if(EmployeeGender.OTHER.equals(value)){
			 returnKey = "其他性别";
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
		 if("女性".equals(key)){
			 returnValue = EmployeeGender.FEMALE;
		 }else if("男性".equals(key)){
			 returnValue = EmployeeGender.MALE;
		 }else if("其他性别".equals(key)){
			 returnValue = EmployeeGender.OTHER;
		 }
		 return returnValue;
	 }
}
