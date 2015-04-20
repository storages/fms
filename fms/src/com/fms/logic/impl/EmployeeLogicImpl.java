package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Employee;
import com.fms.core.entity.Scmcoc;
import com.fms.dao.AclUserDao;
import com.fms.dao.EmployeeDao;
import com.fms.dao.OperateLogsDao;
import com.fms.logic.EmployeeLogic;
import com.fms.utils.AjaxResult;

public class EmployeeLogicImpl implements EmployeeLogic {
	private EmployeeDao  employeeDao;
	private AclUserDao  userDao;
	private OperateLogsDao operaterLogsDao;


	public void saveEmpl(AclUser loginUser,Employee modal) {
		// TODO Auto-generated method stub
		employeeDao.saveOrUpdate(modal);
		
	}
	
	public List<Employee> loadEmployee(AclUser loginUser) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Employee> findAllEmpl(AclUser loginUser,String likeStr,Integer index,Integer length) {
		return employeeDao.findAllEmpl(likeStr,(index*length-length),length);
	}


	public void updateEmpl(AclUser loginUser,Employee modal) {
		// TODO Auto-generated method stub
	}


	public void deleteEmpl(AclUser loginUser,String [] ids) {
		// TODO Auto-generated method stub
		userDao.deleteAclUserByEmpId(ids);
		String hql = "delete Employee a where 1=1 and ";
		operaterLogsDao.saveDeleteLogs(loginUser, ids, Employee.class);
		for(int i=0 ; i<ids.length; i++){
			hql+=" a.id = ? or ";
		}
		hql = hql.substring(0,hql.trim().length()-2);
		employeeDao.batchUpdateOrDelete(hql, ids);
		
	}

	public Employee getEmplById(AclUser loginUser,String id) {
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
	public void saveEmplAndUser(AclUser loginUser,Employee empl,boolean isuser,AclUser user) throws Exception{
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
		if(null!=empl.getId()&&empl.getId().length()>0){
			operaterLogsDao.saveEditLogs(loginUser, empl, empl.getId());
		}else{
			operaterLogsDao.saveNewLogs(loginUser, empl);
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

	public int countListEmpl(AclUser loginUser,String str) {
		// TODO Auto-generated method stub
		String hql = "select count(id) from Employee a where 1=1";
		List param = new ArrayList();
		if(null!=str && !"".equals(str)){
			hql+=" and a.name like '%"+str+"%'";
			//param.add("'%"+name+"%'");
		}
		return userDao.count(hql, param.toArray());
	}

	public void deleteEmpl(AclUser loginUser,String id) {
		// TODO Auto-generated method stub
		
	}

	public void updateEmplUseByparam(AclUser logUser,String key,boolean param) {
		// TODO Auto-generated method stub
	    Employee employee= (Employee) employeeDao.get(Employee.class, key);
	    employee.setWfloginUser(param);
	    operaterLogsDao.saveEditLogs(logUser, employee, key);
	    employeeDao.saveOrUpdate(employee);
	}


	public OperateLogsDao getOperaterLogsDao() {
		return operaterLogsDao;
	}

	public void setOperaterLogsDao(OperateLogsDao operaterLogsDao) {
		this.operaterLogsDao = operaterLogsDao;
	}



}
