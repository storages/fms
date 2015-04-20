package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Employee;
import com.fms.core.entity.OperateLogs;
import com.fms.dao.OperateLogsDao;
import com.fms.logic.OperateLogsLogic;

public class OperateLogsLogicImpl implements OperateLogsLogic {
	private  OperateLogsDao logDao;

	public List<OperateLogs> findAllEmpl(AclUser loginUser, String likeStr,
			Integer index, Integer length) {
		return logDao.findAllLogs(likeStr,(index*length-length),length);
	}

	public OperateLogs getEmplById(AclUser loginUser, String id) {
		// TODO Auto-generated method stub
		 return (OperateLogs) logDao.get(OperateLogs.class, id);
	}

	public OperateLogsDao getLogDao() {
		return logDao;
	}

	public void setLogDao(OperateLogsDao logDao) {
		this.logDao = logDao;
	}

	public int countListLogs(AclUser loginUser, String str) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String hql = "select count(id) from OperateLogs a where 1=1";
		List param = new ArrayList();
		if(null!=str && !"".equals(str)){
			hql+=" and a.name like '%"+str+"%'";
			//param.add("'%"+name+"%'");
		}
		return logDao.count(hql, param.toArray());
	}

	
	
	
}
