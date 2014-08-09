package com.fms.logic.impl;

import java.util.List;

import com.fms.core.entity.Employee;
import com.fms.dao.EmployeeDao;
import com.fms.logic.EmployeeLogic;

public class EmployeeLogicImpl implements EmployeeLogic {
	private EmployeeDao  employeeDao;



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

	
	
	

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}



	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}



}
