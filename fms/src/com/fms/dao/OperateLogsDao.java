package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.AclUser;

public interface OperateLogsDao extends BaseDao {
	
	public void saveNewLogs(AclUser logUser,Object obj);
	public void saveEditLogs(AclUser logUser, Object newObj, String id);
	public void saveDeleteLogs(AclUser logUser,String[] ids,Class clazz);
	
}
