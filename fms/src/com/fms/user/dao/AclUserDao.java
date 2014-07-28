package com.fms.user.dao;

import java.util.List;

import com.fms.core.entity.AclUser;

public interface AclUserDao {

	/**
	 * 查询用户
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
	public List<AclUser> findAllUser();
	
	/**
	 * 删除用户
	 * @param ids
	 */
	public void deleteAclUser(String [] ids);
}