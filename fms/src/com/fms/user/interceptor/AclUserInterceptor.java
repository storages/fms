package com.fms.user.interceptor;

import java.util.Map;

import com.fms.base.interceptor.AbstractInterceptor;
import com.fms.user.action.AclUserAction;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * 用户拦截器
 * 
 * @author Administrator
 * 
 */
public class AclUserInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String LOGIN_KEY = "login";  
    public static final String LOGIN_PAGE = "edit"; 
	
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		System.out.println("==============用户拦截器开始===============");

		// 对用户登录不做该项拦截
		Object action = actionInvocation.getAction();
		if (action instanceof AclUserAction) {
			System.out.println("========用户登录不做该项拦截==========");
			return actionInvocation.invoke();
		}
		
		// 确认Session中是否存在LOGIN  
        Map session = actionInvocation.getInvocationContext().getSession();  
        String login = (String) session.get(LOGIN_KEY);  
        if (login != null && login.length() > 0) {  
            // 存在的情况下进行后续操作。  
            System.out.println("===========可以执行登录!============");  
            return actionInvocation.invoke();  
        } else {  
            // 否则终止后续操作，返回LOGIN  
            System.out.println("===========非法登录!跳转到登录页面============");  
            return LOGIN_PAGE;  
        }  
	}
}
