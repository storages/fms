﻿package com.fms.dao;

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
	
	public void deleteAclUserByEmpId(String [] empId);
	/**
	 * 更具用户名查询用户
	 * @param name
	 * @return
	 */
	public boolean findUserByName(String name);
	
	/**
	 * 根据用户id来查询用户
	 * @param id
	 * @return
	 */
	public AclUser findUserById(String id);
	
}