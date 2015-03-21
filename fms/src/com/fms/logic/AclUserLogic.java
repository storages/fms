package com.fms.logic;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.utils.AjaxResult;


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
	public void saveAclUser(AclUser loginUser,AclUser aclUser);
	
	/**
	 * 获取所有用户
	 * @return
	 */
	public List<AclUser> findAllUser(AclUser loginUser,String userflag);
	
	/**
	 * 删除用户
	 * @param ids
	 */
	public void deleteAclUser(AclUser loginUser,String [] ids);
	
	/**
	 * 根据员工ID 删除用户
	 * @param ids
	 */
	public void deleteUserByEmpl(AclUser loginUser,String [] ids);
	
	/**
	 * 根据用户id来查询用户
	 * @param id
	 * @return
	 */
	public AclUser findUserById(AclUser loginUser,String id);
	
	
	public AjaxResult saveUserByNoName(AclUser loginUser,AclUser user);
	
	public boolean findUserByName(AclUser loginUser,String name);
}
