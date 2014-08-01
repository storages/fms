package com.fms.user.dao;

import java.util.List;

import com.fms.core.entity.AclUser;

public interface AclUserDao {

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
	 * 根据用户id来查询用户
	 * @param id
	 * @return
	 */
	public AclUser findUserById(String id);
	
}