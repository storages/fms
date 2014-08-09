package com.fms.logic.impl;

import java.util.List;

import com.fms.core.entity.Department;
import com.fms.dao.DeptDao;
import com.fms.logic.DeptLogic;

public class DeptLogicImpl implements DeptLogic {

	private DeptDao deptDao;
	
	public List<Department> findAllDept(String likeStr,Integer index,Integer length) {
		return this.deptDao.findAllDept(likeStr, index, length);
	}
	/**
	 * 获取所有的部门  在增加员工的时候使用
	 * @return
	 */
	public List<Department> findAllDep(){
	        //	deptDao.find
		return null;
	}
	
	
	public DeptDao getDeptDao() {
		return deptDao;
	}
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}



	public Integer findDataCount(String className, String name) {
		return this.deptDao.findDataCount(className, name);
	}



	public Department findDeptById(String id) {
		return this.deptDao.findDeptById(id);
	}



	public void saveDept(Department dept) {
		this.deptDao.saveDept(dept);
	}



	public String findDeptByCode(String code) {
		return this.deptDao.findDeptByCode(code);
	}



	public void delDeptById(String [] ids) {
		this.deptDao.delDeptById(ids);
	}

	
}
