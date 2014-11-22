package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.AclUser;

public interface AclUserDao extends BaseDao{

	/**
	 * 用户登录（用户没有被禁用的才可以登录）
	 * 
	 * @param loginName
	 * @param password
	 * @return
	 */
	public abstract AclUser loginAclUser(String userName, String password);

	/**
	 * 保存用户
	 * @param aclUser
	 */
	
	public abstract void saveAclUser(AclUser aclUser);

	/**
	 * 获取所有用户
	 * @return
	 */
	public List<AclUser> findAllUser(String userflag);
	
	/**
	 * 删除用户
	 * @param ids
	 */
	public void deleteAclUser(String [] ids);
	
  /**
   * 更具员工ID 删除用户	
   * @param empId
   */
	public void deleteAclUserByEmpId(String [] empId);
	/**
	 * 根据用户名查询用户
	 * @param name
	 * @return true:表示数据库中存在这个用户名       false:表示数据库中不存在这个用户名
	 */
	public boolean findUserByName(String name);
	
	/**
	 * 根据用户id来查询用户
	 * @param id
	 * @return
	 */
	public AclUser findUserById(String id);
	
}