package com.fms.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fms.core.entity.AclUser;
import com.fms.dao.AclUserDao;
import com.fms.utils.MD5Util;

public class CreateUserListener implements ServletContextListener,ApplicationContextAware{

	private AclUserDao userDao;
	private ApplicationContext ac;

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent sc) {
		System.out.println("----开始初始化默认用户----");
		ac = WebApplicationContextUtils.getWebApplicationContext(sc.getServletContext());
		userDao = (AclUserDao) ac.getBean("userDao");

		Boolean isExist = this.userDao.findUserByName("admin");
		if(!isExist){
			AclUser aclUser = new AclUser();
			aclUser.setLoginName("管理员");
			aclUser.setUserName("admin");
			aclUser.setPassword(MD5Util.encryptData("admin"));
			aclUser.setUserFlag("S");
			userDao.saveAclUser(aclUser);
			System.out.println("----初始化新用户成功----");
		}
		System.out.println("----初始化默认用户结束----");
	}

	public AclUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(AclUserDao userDao) {
		this.userDao = userDao;
	}

	public void setApplicationContext(ApplicationContext ac)
			throws BeansException {
		this.ac= ac;
		
	}

	

	
}
