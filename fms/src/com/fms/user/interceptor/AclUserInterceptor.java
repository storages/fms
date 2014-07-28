package com.fms.user.interceptor;

import java.util.Map;

import com.fms.base.interceptor.AbstractInterceptor;
import com.fms.user.action.AclUserAction;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * �û�������
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
		System.out.println("==============�û���������ʼ===============");

		// ���û���¼������������
		Object action = actionInvocation.getAction();
		if (action instanceof AclUserAction) {
			System.out.println("========�û���¼������������==========");
			return actionInvocation.invoke();
		}
		
		// ȷ��Session���Ƿ����LOGIN  
        Map session = actionInvocation.getInvocationContext().getSession();  
        String login = (String) session.get(LOGIN_KEY);  
        if (login != null && login.length() > 0) {  
            // ���ڵ�����½��к���������  
            System.out.println("===========����ִ�е�¼!============");  
            return actionInvocation.invoke();  
        } else {  
            // ������ֹ��������������LOGIN  
            System.out.println("===========�Ƿ���¼!��ת����¼ҳ��============");  
            return LOGIN_PAGE;  
        }  
	}
}
