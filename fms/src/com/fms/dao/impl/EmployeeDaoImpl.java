package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.Employee;
import com.fms.core.entity.Scmcoc;
import com.fms.dao.DeptDao;
import com.fms.dao.EmployeeDao;

public class EmployeeDaoImpl extends BaseDaoImpl implements EmployeeDao {
	
	
	public List<Employee> findAllEmpl(String likeStr,Integer index,Integer length) {
		String hql = "select a from Employee a ";
		List param = new ArrayList();
		if(null!=likeStr && !"".equals(likeStr)){
			hql+=" and a.name like '%"+likeStr+"%'";
			//param.add("'%"+ likeStr +"%'");
		}
		return this.findPageList(hql, param.toArray(),index,length);
	}
                 
}
