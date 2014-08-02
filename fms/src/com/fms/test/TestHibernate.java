package com.fms.test;

import java.util.Date;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fms.core.entity.AclUser;
import com.fms.logic.AclUserLogic;
import com.fms.resouce.config.HibernateSessionFactory;
import com.fms.utils.MD5Util;

public class TestHibernate {
	private AclUserLogic logic = null;
	
	
	public AclUserLogic getLogic() {
		return logic;
	}

	public void setLogic(AclUserLogic logic) {
		this.logic = logic;
	}

	@Test
	public void testHibernate() {
		Session session = HibernateSessionFactory.getSession();
		session.getTransaction().begin();
		AclUser user = new AclUser();
		user.setLoginName("esdf");
		user.setUserName("g");
		session.save(user);
		session.getTransaction().commit();
	}
	
	@Test
	public void testSpring() {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		Date date = (Date) context.getBean("date");
		System.out.println(date);
	}
	
	@Test
	public void testSaveUser(){
		Session session = HibernateSessionFactory.getSession();
		session.getTransaction().begin();
		AclUser user = new AclUser();
		user.setLoginName("esdf");
		user.setUserName("g");
		user.setPassword(MD5Util.encryptData("123456"));
		System.out.println(user.getPassword());
		session.save(user);
		session.getTransaction().commit();
	}
}
