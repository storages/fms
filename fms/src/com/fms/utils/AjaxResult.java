package com.fms.utils;

/**
 * 用于AJax 返回对象
 * @author Administrator
 *
 */
public class AjaxResult<T>  {
	private boolean success;  //是否成功
	private String msg;      //存储消息
	private Object obj;    //存储对象
	
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
