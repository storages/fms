package com.fms.user.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.AclUser;

/**
 * 用户 访问数据库类
 * 
 * @author Administrator
 * 
 */
public class AclUserDaoImpl extends BaseDao implements AclUserDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fms.user.dao.AclUserDao#loginAclUser(java.lang.String,
	 * java.lang.String)
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

	public List<AclUser> findAllUser() {
		List list = new ArrayList();
		String hql = "SELECT a FROM AclUser a ";
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
}
