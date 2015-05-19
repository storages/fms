package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.Privilege;
import com.fms.dao.PrivilegeDao;

public class PrivilegeDaoImpl extends BaseDaoImpl implements PrivilegeDao {

	public List<Privilege> findAllPrivilege(String likeStr, Integer index,
			Integer length) {
		String hql = "select a from Privilege a where 1=1";
		List param = new ArrayList();
		if(null!=likeStr && !"".equals(likeStr)){
			param.add(likeStr);
			param.add(likeStr);
			hql+=" and a.name like '%?%' or a.code like %?%";
			//param.add("'%"+ likeStr +"%'");
		}
		return this.findPageList(hql, param.toArray(),index,length);
	}

}
