package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Employee;
import com.fms.dao.AclUserDao;
import com.fms.dao.EmployeeDao;
import com.fms.logic.EmployeeLogic;
import com.fms.utils.AjaxResult;

public class EmployeeLogicImpl implements EmployeeLogic {
	private EmployeeDao  employeeDao;
	private AclUserDao  userDao;



	public void saveEmpl(Employee modal) {
		// TODO Auto-generated method stub
		employeeDao.saveOrUpdate(modal);
		
	}
	
	public List<Employee> loadEmployee() {
		// TODO Auto-generated method stub
		return null;
	}





	public void updateEmpl(Employee modal) {
		// TODO Auto-generated method stub
		
	}





	public void deleteEmpl(int id) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 创建员工并且创建用户
	 * @param empl
	 * @param isuser
	 * @param user
	 * @throws Exception 
	 */
	public void saveEmplAndUser(Employee empl,boolean isuser,AclUser user) throws Exception{
		AjaxResult result=new AjaxResult();
		AclUser aclUser =null;
		//判断用户是否存在
		List list = new ArrayList();
		String hql = "SELECT a FROM AclUser a where a.userName=?";
		list.add(user.getUserName());
		if(isuser){
			aclUser= (AclUser) userDao.findUniqueResult(hql, list.toArray());
			if(null!=aclUser){
				throw new Exception("登录用户名已存在");
			}
		}
		//保存员工
		employeeDao.saveOrUpdate(empl);
		if(isuser){
			userDao.saveAclUser(user);
		}
		
	}

	
	
	

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}



	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	public AclUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(AclUserDao userDao) {
		this.userDao = userDao;
	}
	



}
