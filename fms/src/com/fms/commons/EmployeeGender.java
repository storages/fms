package com.fms.commons;

/**
 * Ա���Ա�
 * @author Administrator
 *
 */
public class EmployeeGender {

	/**
	 * Ů��
	 */
	public final static String FEMALE ="0";
	/**
	 * ����
	 */
	 public final static String MALE ="1";
	 /**
	  * ����
	  */
	 public final static String OTHER = "2";
	 
	 
	 /**
	  * ��ֵת���ɶ�Ӧ������
	  * @param value
	  * @return
	  */
	 public static String valueToKey(String value){
		 String returnKey = "";
		 if(EmployeeGender.FEMALE.equals(value)){
			 returnKey = "Ů��";
		 }else if(EmployeeGender.MALE.equals(value)){
			 returnKey = "����";
		 }else if(EmployeeGender.OTHER.equals(value)){
			 returnKey = "�����Ա�";
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
		 if("Ů��".equals(key)){
			 returnValue = EmployeeGender.FEMALE;
		 }else if("����".equals(key)){
			 returnValue = EmployeeGender.MALE;
		 }else if("�����Ա�".equals(key)){
			 returnValue = EmployeeGender.OTHER;
		 }
		 return returnValue;
	 }
}
