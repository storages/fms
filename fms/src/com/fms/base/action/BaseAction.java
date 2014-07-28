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
	 * �����ȡ������
	 */
	protected ActionContext context = ActionContext.getContext();
	
	/**
	 * �����ȡSession
	 */
	protected Map session = context.getSession();
	
	/**
	 * �����ȡRequest
	 */
	protected Map request = (Map) this.context.get("request");
	
	/**
	 * �����ȡHttpServletResponse
	 */
	protected HttpServletResponse response = ServletActionContext.getResponse();

}
