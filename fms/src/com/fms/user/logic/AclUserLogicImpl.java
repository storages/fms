package com.fms.user.logic;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.user.dao.AclUserDao;
import com.fms.utils.MD5Util;

public class AclUserLogicImpl implements AclUserLogic {

	/**
	 * 数据访问对象
	 */
	private AclUserDao userDao = null;
	
	
	public AclUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(AclUserDao userDao) {
		this.userDao = userDao;
	}

	public AclUser loginAclUser(String userName, String password) {
		String descPassword = MD5Util.encryptData(password);
		System.out.println(password+"--------"+descPassword+"---------");
		AclUser user = userDao.loginAclUser(userName, descPassword);
		return user;
	}

	public void saveAclUser(AclUser aclUser) {
		String descPassword = MD5Util.encryptData(aclUser.getPassword());
		aclUser.setPassword(descPassword);
		this.userDao.saveAclUser(aclUser);
	}

	public List<AclUser> findAllUser() {
		return this.userDao.findAllUser();
	}

	public void deleteAclUser(String[] ids) {
		this.userDao.deleteAclUser(ids);		
	}

}
