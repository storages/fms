package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.Employee;

public interface EmployeeDao  extends BaseDao {
	
	public List<Employee> findAllEmpl(Boolean isCustom,String likeStr,Integer index,Integer length) ;

}
