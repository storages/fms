package com.fms.dao.impl;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.ParameterSet;
import com.fms.dao.ParamsDao;

public class ParamsDaoImpl extends BaseDaoImpl implements ParamsDao {

	public ParameterSet getParameterValue() {
		String hql = "FROM ParameterSet ";
		return (ParameterSet) this.findUniqueResult(hql, null);
	}

	public void saveParameterValue(ParameterSet param) {
		this.saveOrUpdate(param);

	}

}
