package com.fms.user.logic;

import java.util.List;

import com.fms.core.entity.AclUser;


/**
 * 用户   逻辑处理类
 * @author Administrator
 *
 */
public interface AclUserLogic {

	/**
	 * 登录
	 * @param loginName
	 * @param password
	 * @return
	 */
	public AclUser loginAclUser(String userName, String password);
	
	/**
	 * 保存用户
	 * @param aclUser
	 */
	public void saveAclUser(AclUser aclUser);
	
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
