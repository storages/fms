package com.fms.commons;

/**
 * 工作状态
 * @author Administrator
 *
 */
public class JobStatus {

	/**
	 * 试用期
	 */
	public final static String PROBATION = "0";
	/**
	 * 转正
	 */
	public final static String POSITIVE = "1";
	/**
	 * 离职
	 */
	public final static String QUIT = "2";
	/**
	 * 休假
	 */
	public final static String VACATION = "3";
	
	
	/**
	  * 把值转换成对应的名称
	  * @param value
	  * @return
	  */
	 public static String valueToKey(String value){
		 String returnKey = "";
		 if(JobStatus.PROBATION.equals(value)){
			 returnKey = " 试用期";
		 }else if(JobStatus.POSITIVE.equals(value)){
			 returnKey = "转正";
		 }else if(JobStatus.QUIT.equals(value)){
			 returnKey = "离职";
		 }else if(JobStatus.VACATION.equals(value)){
			 returnKey = "休假";
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
		 if("试用期".equals(key)){
			 returnValue = JobStatus.PROBATION;
		 }else if("转正".equals(key)){
			 returnValue = JobStatus.POSITIVE;
		 }else if("离职".equals(key)){
			 returnValue = JobStatus.QUIT;
		 }else if("休假".equals(key)){
			 returnValue = JobStatus.VACATION;
		 }
		 return returnValue;
	 }
}
