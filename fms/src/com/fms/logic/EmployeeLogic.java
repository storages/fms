package com.fms.logic;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Employee;

public interface EmployeeLogic {
	public void saveEmpl(AclUser loginUser, Employee modal);

	public List<Employee> loadEmployee(AclUser loginUser);

	public void updateEmpl(AclUser loginUser, Employee modal);

	public void deleteEmpl(AclUser loginUser, String id);

	public void deleteEmpl(AclUser loginUser, String[] ids);

	public Employee getEmplById(AclUser loginUser, String id);

	public void saveEmplAndUser(AclUser loginUser, Employee empl, boolean isuser, AclUser user) throws Exception;

	/**
	 * 获取员工分页
	 * 
	 * @param likeStr
	 * @param index
	 * @param length
	 * @return
	 */
	public List<Employee> findAllEmpl(AclUser loginUser, String likeStr, Integer index, Integer length);

	public int countListEmpl(AclUser loginUser, String str);

	public void updateEmplUseByparam(AclUser logUser, String key, boolean param);

	public List<Employee> findAllEmpl();
}
