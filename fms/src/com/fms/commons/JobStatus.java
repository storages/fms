package com.fms.commons;

/**
 * ����״̬
 * @author Administrator
 *
 */
public class JobStatus {

	/**
	 * ������
	 */
	public final static String PROBATION = "0";
	/**
	 * ת��
	 */
	public final static String POSITIVE = "1";
	/**
	 * ��ְ
	 */
	public final static String QUIT = "2";
	/**
	 * �ݼ�
	 */
	public final static String VACATION = "3";
	
	
	/**
	  * ��ֵת���ɶ�Ӧ������
	  * @param value
	  * @return
	  */
	 public static String valueToKey(String value){
		 String returnKey = "";
		 if(JobStatus.PROBATION.equals(value)){
			 returnKey = " ������";
		 }else if(JobStatus.POSITIVE.equals(value)){
			 returnKey = "ת��";
		 }else if(JobStatus.QUIT.equals(value)){
			 returnKey = "��ְ";
		 }else if(JobStatus.VACATION.equals(value)){
			 returnKey = "�ݼ�";
		 }
		 return returnKey;
	 }
	 
	 /**
	  * ������ת���ɶ�Ӧ��ֵ
	  * @param key
	  * @return
	  */
	 public static String keyToValue(String key){
		 String returnValue = "";
		 if("������".equals(key)){
			 returnValue = JobStatus.PROBATION;
		 }else if("ת��".equals(key)){
			 returnValue = JobStatus.POSITIVE;
		 }else if("��ְ".equals(key)){
			 returnValue = JobStatus.QUIT;
		 }else if("�ݼ�".equals(key)){
			 returnValue = JobStatus.VACATION;
		 }
		 return returnValue;
	 }
}
