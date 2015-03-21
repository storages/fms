package com.fms.logic.impl;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.ParameterSet;
import com.fms.dao.ParamsDao;
import com.fms.logic.ParamsLogic;

public class ParamsLogicImpl implements ParamsLogic {

	private ParamsDao paramsDao;
	
	public ParameterSet getParameterValue(AclUser loginUser) {
		return paramsDao.getParameterValue();
	}

	public void saveParameterValue(AclUser loginUser,ParameterSet param) {
		paramsDao.saveParameterValue(param);
	}
	
	
	

	public ParamsDao getParamsDao() {
		return paramsDao;
	}

	public void setParamsDao(ParamsDao paramsDao) {
		this.paramsDao = paramsDao;
	}

	
	

}
