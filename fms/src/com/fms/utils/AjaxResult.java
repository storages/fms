package com.fms.utils;

/**
 * ����AJax ���ض���
 * @author Administrator
 *
 */
public class AjaxResult {
	private boolean success;  //�Ƿ�ɹ�
	private String msg;      //�洢��Ϣ
	private Object  obj;    //�洢����
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	

}
