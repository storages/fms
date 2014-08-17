package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Employee;
import com.fms.core.entity.Scmcoc;
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


	public List<Employee> findAllEmpl(String likeStr,Integer index,Integer length) {
		return employeeDao.findAllEmpl(likeStr,(index*length-length),length);
	}


	public void updateEmpl(Employee modal) {
		// TODO Auto-generated method stub
	}


	public void deleteEmpl(String [] ids) {
		// TODO Auto-generated method stub
		userDao.deleteAclUserByEmpId(ids);
		String hql = "delete Employee a where 1=1 and ";
		for(int i=0 ; i<ids.length; i++){
			hql+=" a.id = ? or ";
		}
		hql = hql.substring(0,hql.trim().length()-2);
		employeeDao.batchUpdateOrDelete(hql, ids);
		
	}

	public Employee getEmplById(String id) {
		// TODO Auto-generated method stub
		return (Employee) employeeDao.get(Employee.class, id);
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
		if(isuser){
			list.add(user.getLoginName());
			aclUser= (AclUser) userDao.findUniqueResult(hql, list.toArray());
			if(null!=aclUser){
				throw new Exception("登录用户名已存在");
			}
		}
		//保存员工
		employeeDao.saveOrUpdate(empl);
		if(isuser){
			user.setUserFlag("P");//普通
			user.setEmployee(empl);
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

	public int countListEmpl(String str) {
		// TODO Auto-generated method stub
		String hql = "select count(id) from Employee a where 1=1";
		List param = new ArrayList();
		if(null!=str && !"".equals(str)){
			hql+=" and a.name like '%"+str+"%'";
			//param.add("'%"+name+"%'");
		}
		return userDao.count(hql, param.toArray());
	}

	public void deleteEmpl(String id) {
		// TODO Auto-generated method stub
		
	}





}
