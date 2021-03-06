﻿package com.fms.logic.impl;

import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.dao.AclUserDao;
import com.fms.dao.OperateLogsDao;
import com.fms.logic.AclUserLogic;
import com.fms.utils.AjaxResult;
import com.fms.utils.MD5Util;

public class AclUserLogicImpl implements AclUserLogic {

	/**
	 * 数据访问对象
	 */
	private AclUserDao userDao;
	private OperateLogsDao operaterLogsDao;
	
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

	public void saveAclUser(AclUser loginUser,AclUser aclUser) {
		/*if(null!=aclUser.getPassword() && !"".equals(aclUser.getPassword())){
			String descPassword = MD5Util.encryptData(aclUser.getPassword());
			aclUser.setPassword(descPassword);
		}*/
		this.userDao.saveAclUser(aclUser);
	}

	public List<AclUser> findAllUser(AclUser loginUser,String userflag) {
		return this.userDao.findAllUser(userflag);
	}

	public void deleteAclUser(AclUser loginUser,String[] ids) {
		this.userDao.deleteAclUser(ids);		
	}

	public AclUser findUserById(AclUser loginUser,String id) {
		return this.userDao.findUserById(id);
	}

	public AjaxResult saveUserByNoName(AclUser loginUser,AclUser user) {
		AjaxResult result=new AjaxResult();
		result.setSuccess(false);
        boolean isue= userDao.findUserByName(user.getLoginName());	
        if(!isue){
        	userDao.saveAclUser(user);
        	result.setSuccess(true);
        }else{
        	result.setSuccess(false);
        	result.setMsg("用户名已经存在");
        }
		return result;
	}

	public void deleteUserByEmpl(AclUser loginUser,String[] ids) {
		// TODO Auto-generated method stub
		userDao.deleteAclUserByEmpId(ids);
	}
	public boolean findUserByName(AclUser loginUser,String name){
		return userDao.findUserByName(name);
	}
}
