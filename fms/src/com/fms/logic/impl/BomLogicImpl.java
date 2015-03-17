package com.fms.logic.impl;

import java.util.List;

import com.fms.core.entity.BomExg;
import com.fms.dao.BomDao;
import com.fms.logic.BomLogic;

public class BomLogicImpl implements BomLogic {

	protected BomDao bomDao;

	public List<BomExg> findBomExg() {
		// TODO Auto-generated method stub
		return null;
	}

	public BomDao getBomDao() {
		return bomDao;
	}

	public void setBomDao(BomDao bomDao) {
		this.bomDao = bomDao;
	}

}
