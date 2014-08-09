package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.Department;
import com.fms.dao.DeptDao;

public class DeptDaoImpl extends BaseDaoImpl implements DeptDao {

	public List<Department> findAllDept(String likeStr,Integer index,Integer length) {
		String hql = "select a from Department a where 1=1 ";
		List param = new ArrayList();
		if(null!=likeStr && !"".equals(likeStr)){
			hql+=" and a.name like '%"+likeStr+"%'";
			//param.add("'%"+ likeStr +"%'");
		}
		return this.findPageList(hql, new Object[]{},index,length);
	}
	
	public Integer findDataCount(String className,String name) {
		String hql = "select count(id) from "+className.trim() +" a where 1=1 ";
		List param = new ArrayList();
		if(null!=name && !"".equals(name)){
			hql+=" and a.name like '%"+name+"%'";
		}
		return this.count(hql, new Object[]{});
	}
	
	public Department findDeptById(String id){
		String hql = "select a from Department a where a.id = ? ";
		List param = new ArrayList();
		param.add(id);
		return (Department) this.findUniqueResult(hql, param.toArray());
	}
	
	public void saveDept(Department dept){
		this.saveOrUpdate(dept);
	}

	public String findDeptByCode(String code) {
		String hql = "select a.code from Department a where a.code = ? ";
		List param = new ArrayList();
		param.add(code);
		return (String) this.findUniqueResult(hql, param.toArray());
	}

	public void delDeptById(String [] ids) {
		String hql = "DELETE FROM Department a where a.id = ? ";
		List param = new ArrayList();
		param.add(ids[0]);
		for(int i = 1 ; i < ids.length ; i++){
			hql+=" or a.id = ? ";
			param.add(ids[i]);
		}
		this.batchUpdateOrDelete(hql, param.toArray());
	}

	public List<Department> findAllDept() {
		// TODO Auto-generated method stub
		String hql = "select a from Department a where 1=1 ";
		List param = new ArrayList();
		return this.findListNoCache(hql);
	}
}
