package com.fms.base.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {
	/**
	 * 
	 */
	protected static final long serialVersionUID = 1L;

	/**
	 * 父类获取上下文
	 */
	protected ActionContext context = ActionContext.getContext();
	
	/**
	 * 父类获取Session
	 */
	protected Map session = context.getSession();
	
	/**
	 * 父类获取Request
	 */
	protected Map request = (Map) this.context.get("request");
	
	/**
	 * 父类获取HttpServletResponse
	 */
	protected HttpServletResponse response = ServletActionContext.getResponse();

}
