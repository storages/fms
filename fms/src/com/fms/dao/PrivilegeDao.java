package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.Employee;
import com.fms.core.entity.Privilege;

public interface PrivilegeDao extends BaseDao {
	public List<Privilege> findAllPrivilege(String likeStr,Integer index,Integer length) ;
}
