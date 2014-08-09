package com.fms.logic;


import java.util.List;

import com.fms.core.entity.Employee;

public interface EmployeeLogic {
	public void saveEmpl(Employee modal);
	public List<Employee> loadEmployee();
	public void updateEmpl(Employee modal);
	public void deleteEmpl(int id);
}
