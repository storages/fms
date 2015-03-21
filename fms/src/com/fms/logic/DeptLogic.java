package com.fms.logic;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Department;

public interface DeptLogic {

	/**
	 * 查询所有的部门信息[分页]
	 * @param likeStr
	 * @param index
	 * @param length
	 * @return
	 */
	public List<Department> findAllDept(AclUser loginUser,String likeStr,Integer index,Integer length);
	
	/**
	 * 查询数据的条数
	 * @param className
	 * @param name
	 * @return
	 */
	public Integer findDataCount(AclUser loginUser,String className,String name);
	
	/**
	 * 根据id查询部门
	 * @param id
	 * @return
	 */
	public Department findDeptById(AclUser loginUser,String id);
	
	/**
	 * 保存单个实体
	 * @param dept
	 */
	public void saveDept(AclUser loginUser,Department dept);
	
	/**
	 * 查找部门编码是否重复
	 * @param code
	 * @return
	 */
	public String findDeptByCode(AclUser loginUser,String code);
	
	/**
	 * 根据Id来删除部门信息
	 */
	public void delDeptById(AclUser loginUser,String [] ids);
	
	/**
	 * 查询所有的部门
	 */
	public List<Department> findDept(AclUser loginUser);
}
