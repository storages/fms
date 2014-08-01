package com.fms.logic;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.dao.AclUserDao;
import com.fms.utils.MD5Util;

public class AclUserLogicImpl implements AclUserLogic {

	/**
	 * ���ݷ��ʶ���
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
		AclUser user = userDao.loginAclUser(userName, descPassword);
		return user;
	}

	public void saveAclUser(AclUser aclUser) {
		if(null==aclUser.getPassword() && "".equals(aclUser.getPassword())){
			String descPassword = MD5Util.encryptData(aclUser.getPassword());
			aclUser.setPassword(descPassword);
		}
		this.userDao.saveAclUser(aclUser);
	}

	public List<AclUser> findAllUser(String userflag) {
		return this.userDao.findAllUser(userflag);
	}

	public void deleteAclUser(String[] ids) {
		this.userDao.deleteAclUser(ids);		
	}

	public AclUser findUserById(String id) {
		return this.userDao.findUserById(id);
	}

}