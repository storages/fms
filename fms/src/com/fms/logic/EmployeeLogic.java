package com.fms.logic;


import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Employee;

public interface EmployeeLogic {
	public void saveEmpl(Employee modal);
	public List<Employee> loadEmployee();
	public void updateEmpl(Employee modal);
	public void deleteEmpl(int id);
	public void saveEmplAndUser(Employee empl,boolean isuser,AclUser user) throws Exception;
	/**
	 * 获取员工分页
	 * @param likeStr
	 * @param index
	 * @param length
	 * @return
	 */
	public List<Employee> findAllEmpl(String likeStr,Integer index,Integer length);
	
}
