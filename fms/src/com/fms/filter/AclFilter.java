package com.fms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fms.commons.CommonConstant;
import com.fms.core.entity.AclUser;


public class AclFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest  ret=(HttpServletRequest) request;
		HttpServletResponse rpe=(HttpServletResponse) response;
		String contextPath=ret.getContextPath();
		String path= ret.getServletPath();
		
			if(path.contains("login")){
				chain.doFilter(request, response);
			}else{
			AclUser  user=	 (AclUser) ret.getSession().getAttribute(CommonConstant.LOGINUSER);
				if(null!=user){
					chain.doFilter(request, response);
				}else{
					rpe.sendRedirect(contextPath+"/loginAction_gologin.action");
				}
				
			}
			
		
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
