package com.fms.utils;

/**
 * 用于AJax 返回对象
 * @author Administrator
 *
 */
public class AjaxResult<T>  {
	private boolean success;  //是否成功
	private String msg;      //存储消息
	private T  obj;    //存储对象
	
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
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}

	

}
