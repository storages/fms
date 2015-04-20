package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.Employee;
import com.fms.core.entity.OperateLogs;

public interface OperateLogsDao extends BaseDao {
	
	public void saveNewLogs(AclUser logUser,Object obj);
	public void saveEditLogs(AclUser logUser, Object newObj, String id);
	public void saveDeleteLogs(AclUser logUser,String[] ids,Class clazz);
	public List<OperateLogs> findAllLogs(String likeStr,Integer index,Integer length) ;

}
