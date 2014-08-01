package com.fms.interceptor;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.fms.commons.CommonConstant;
import com.fms.core.entity.AclUser;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sun.jmx.snmp.UserAcl;
/**
 * 用于拦截是否登录和权限的拦截器
 * @author Administrator
 *
 */
public class AclLoginInterceptor  implements Interceptor{
	private String loginUrl="login";
	private String resultUlr="tologin";

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		// TODO Auto-generated method stub
		
	}

	public String intercept(ActionInvocation context) throws Exception {
		// TODO Auto-generated method stub
	 Map<String,Object> session=context.getInvocationContext().getSession();
	 AclUser user= (AclUser) session.get(CommonConstant.LOGINUSER);
	HttpServletRequest  request=  ServletActionContext.getRequest();
	 String path = request.getServletPath();
	 if(path.contains(loginUrl)){//访问登录的页面
		return  context.invoke();
	 }else{
	 if(user!=null){
		 //已经登录
		return context.invoke();
	 }else{
		 //未登录
		 return resultUlr;
	 }
	 }
	}

}
