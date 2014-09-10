package com.fms.logic.impl;

import java.util.List;

import com.fms.core.entity.MaterialType;
import com.fms.dao.MaterialTypeDao;
import com.fms.logic.MaterialTypeLogic;

public class MaterialTypeLogicImpl implements MaterialTypeLogic {

	private MaterialTypeDao dao;
	
	public List<MaterialType> findAllType() {
		return dao.findAllType();
	}
	
	
	
	
	
	public MaterialTypeDao getDao() {
		return dao;
	}
	public void setDao(MaterialTypeDao dao) {
		this.dao = dao;
	}

	
}
