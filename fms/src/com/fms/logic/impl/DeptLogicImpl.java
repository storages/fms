package com.fms.logic.impl;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Department;
import com.fms.dao.DeptDao;
import com.fms.logic.DeptLogic;

public class DeptLogicImpl implements DeptLogic {

	private DeptDao deptDao;
	
	public List<Department> findAllDept(AclUser loginUser,String likeStr,Integer index,Integer length) {
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



	public Integer findDataCount(AclUser loginUser,String className, String name) {
		return this.deptDao.findDataCount(className, name);
	}



	public Department findDeptById(AclUser loginUser,String id) {
		return this.deptDao.findDeptById(id);
	}



	public void saveDept(AclUser loginUser,Department dept) {
		this.deptDao.saveDept(dept);
	}



	public String findDeptByCode(AclUser loginUser,String code) {
		return this.deptDao.findDeptByCode(code);
	}



	public void delDeptById(AclUser loginUser,String [] ids) {
		this.deptDao.delDeptById(ids);
	}
	public List<Department> findDept(AclUser loginUser) {
		// TODO Auto-generated method stub
		return this.deptDao.findAllDept();
	}

	
}
