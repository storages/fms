package com.fms.dao;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.Department;

public class DeptDaoImpl extends BaseDao implements DeptDao {

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
}
