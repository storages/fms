package com.fms.logic.impl;

import java.util.List;

import com.fms.core.entity.BomExg;
import com.fms.dao.BomDao;
import com.fms.logic.BomLogic;

public class BomLogicImpl implements BomLogic {

	protected BomDao bomDao;

	public List<BomExg> findBomExg(String hsName, String hsCode, String hsModel, Integer index, Integer length) {
		return this.bomDao.findBomExg(hsName, hsCode, hsModel, index, length);
	}

	/**
	 * 按条件查找记录数
	 * 
	 * @param className
	 * @param searchStr
	 * @return
	 */
	public Integer findDataCount(String className, String hsName, String hsCode, String hsModel) {
		return bomDao.findDataCount(className, hsName, hsCode, hsModel);
	}

	public BomDao getBomDao() {
		return bomDao;
	}

	public void setBomDao(BomDao bomDao) {
		this.bomDao = bomDao;
	}

}
