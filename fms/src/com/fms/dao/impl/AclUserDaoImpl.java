package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.AclUser;
import com.fms.dao.AclUserDao;
import com.fms.utils.MD5Util;

/**
 * 用户 访问数据库类
 * 
 * @author Administrator
 * 
 */
public class AclUserDaoImpl extends BaseDaoImpl implements AclUserDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fms.user.dao.AclUserDao#loginAclUser(java.lang.String,
	 * java.lang.String)
	 * 用户登录（用户没有被禁用的才可以登录）
	 */
	public AclUser loginAclUser(String userName, String password) {
		List list = new ArrayList();
		String hql = "SELECT a FROM AclUser a where a.userName=? and a.password=? ";
		list.add(userName);
		list.add(password);
		AclUser aclUser = (AclUser) this.findUniqueResult(hql, list.toArray());
		return aclUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fms.user.dao.AclUserDao#saveAclUser(com.fms.user.entity.AclUser)
	 */
	public void saveAclUser(AclUser aclUser) {
		// java.sql.Date currDate = new java.sql.Date(new Date().getTime());
		if (null != aclUser.getId() && !"".equals(aclUser.getId())) {
			aclUser.setModifyDate(new Date());
		} else {
			aclUser.setCreateDate(new Date());
		}
		this.saveOrUpdate(aclUser);
	}

	public List<AclUser> findAllUser(String userflag) {
		List list = new ArrayList();
		String hql = "";
		if("L".equals(userflag)){
			hql = "SELECT a FROM AclUser a ";
		}else if("S".equals(userflag)){
			hql = "SELECT a FROM AclUser a where a.userFlag = ?";
			list.add("P");
		}
		hql+=" order by a.userFlag ";
		List<AclUser> users = this.find(hql, list.toArray());
		return users;
	}

	public void deleteAclUser(String[] ids) {
		String hql = "DELETE FROM AclUser a where 1=1 AND a.id= ? ";
		List list = new ArrayList();
		list.add(ids[0]);
		for (int i = 1; i < ids.length; i++) {
			hql += " or a.id= ? ";
			list.add(ids[i]);
		}
		this.batchUpdateOrDelete(hql, list.toArray());
	}
	
	public AclUser findUserById(String id){
		String hql = "select a from AclUser a where a.id = ? ";
		AclUser aclUser = (AclUser) this.findUniqueResult(hql, new Object[]{id});
		return aclUser;
	}
}
