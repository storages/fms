package com.fms.commons;

/**
 * �ͻ���Ӧ�̳�����
 * @author Administrator
 *
 */
public class ApplyType {

	/**
	 * ��Ӧ��
	 */
	public static final String APPLY = "0";
	/**
	 * �ͻ�
	 */
	public static final String CUSSTOM = "1";
	
	 /**
	  * ��ֵת���ɶ�Ӧ������
	  * @param value
	  * @return
	  */
	 public static String valueToKey(String value){
		 String returnKey = "";
		 if(ApplyType.APPLY.equals(value)){
			 returnKey = "��Ӧ��";
		 }else if(ApplyType.CUSSTOM.equals(value)){
			 returnKey = "�ͻ�";
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
		 if("��Ӧ��".equals(key)){
			 returnValue = ApplyType.APPLY;
		 }else if("�ͻ�".equals(key)){
			 returnValue = ApplyType.CUSSTOM;
		 }
		 return returnValue;
	 }
}
