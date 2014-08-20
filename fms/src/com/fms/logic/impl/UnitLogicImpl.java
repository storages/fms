package com.fms.logic.impl;

import java.util.List;

import com.fms.core.entity.Unit;
import com.fms.dao.UnitDao;
import com.fms.logic.UnitLogic;

public class UnitLogicImpl implements UnitLogic {
	
	private UnitDao unitDao ;

	public List<Unit> findAllUnit(String likeStr, Integer index, Integer length) {
		return this.unitDao.findAllUnit(likeStr, index, length);
	}

	public Integer findDataCount(String className, String name) {
		return this.unitDao.findDataCount(className, name);
	}

	public Unit findUnitById(String id) {
		return this.unitDao.findUnitById(id);
	}

	public void saveUnit(Unit unit) {
		this.unitDao.saveUnit(unit);
	}

	public String findUnitByCode(String code) {
		return this.unitDao.findUnitByCode(code);
	}

	public void delUnitById(String[] ids) {
		this.unitDao.delUnitById(ids);
	}

	public UnitDao getUnitDao() {
		return unitDao;
	}

	public void setUnitDao(UnitDao unitDao) {
		this.unitDao = unitDao;
	}
	
	

}
