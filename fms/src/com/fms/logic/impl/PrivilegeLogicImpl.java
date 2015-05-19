package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Privilege;
import com.fms.dao.OperateLogsDao;
import com.fms.dao.PrivilegeDao;
import com.fms.logic.PrivilegeLogic;

public class PrivilegeLogicImpl implements PrivilegeLogic {
	
	private PrivilegeDao privilegeDao;
	private OperateLogsDao operaterLogsDao;

	public void savePrivilege(AclUser loginUser, Privilege modal) {
		// TODO Auto-generated method stub
		privilegeDao.saveOrUpdate(modal);
	}

	public void updatePrivilege(AclUser loginUser, Privilege modal) {
		// TODO Auto-generated method stub
		privilegeDao.saveOrUpdate(modal);
	}

	public int countListPrivilege(AclUser loginUser, String str) {
		// TODO Auto-generated method stub
		String hql = "select count(id) from Privilege a where 1=1";
		List param = new ArrayList();
		if (null != str && !"".equals(str)) {
			param.add(str);
			param.add(str);
			hql += " and a.name like '%?%' OR a.code like '%?%'";
			// param.add("'%"+name+"%'");
		}
		return privilegeDao.count(hql, param.toArray());
	}

	public void deletePrivilege(AclUser loginUser, String id) {
		// TODO Auto-generated method stub
		
	}

	public Privilege getPrivilegeById(AclUser loginUser, String id) {
		// TODO Auto-generated method stub
		return (Privilege) privilegeDao.get(Privilege.class, id);
	}

	public void deletePrivilege(AclUser loginUser, String[] ids) {
		// TODO Auto-generated method stub
		String hql = "delete Privilege a where 1=1 and ";
		//operaterLogsDao.saveDeleteLogs(loginUser, ids, Employee.class);
		for (int i = 0; i < ids.length; i++) {
			hql += " a.id = ? or ";
		}
		hql = hql.substring(0, hql.trim().length() - 2);
		privilegeDao.batchUpdateOrDelete(hql, ids);
	}

	public List<Privilege> findAllPrivilege(AclUser loginUser, String likeStr,
			Integer index, Integer length) {
		// TODO Auto-generated method stub
		return privilegeDao.findAllPrivilege(likeStr, index, length);
	}

	public PrivilegeDao getPrivilegeDao() {
		return privilegeDao;
	}

	public void setPrivilegeDao(PrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}

	public OperateLogsDao getOperaterLogsDao() {
		return operaterLogsDao;
	}

	public void setOperaterLogsDao(OperateLogsDao operaterLogsDao) {
		this.operaterLogsDao = operaterLogsDao;
	}
	
	
	
	
	

}
