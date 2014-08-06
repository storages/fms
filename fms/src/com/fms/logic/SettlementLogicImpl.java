package com.fms.logic;

import java.util.List;

import com.fms.core.entity.Settlement;
import com.fms.dao.SettlementDao;

public class SettlementLogicImpl implements SettlementLogic {

	protected SettlementDao settlementDao;
	
	public List<Settlement> findAllSettlement(String searhStr) {
		return settlementDao.findAllSettlement(searhStr);
	}

	public void saveSettlement(Settlement settlement) {
		this.settlementDao.saveSettlement(settlement);
	}

	public SettlementDao getSettlementDao() {
		return settlementDao;
	}

	public void setSettlementDao(SettlementDao settlementDao) {
		this.settlementDao = settlementDao;
	}

	public Settlement findSettById(String id) {
		return this.settlementDao.findSettById(id);
	}

	public String findSettByCode(String code) {
		return this.settlementDao.findSettByCode(code);
	}

	public void delSettltById(String[] ids) {
		this.settlementDao.delSettltById(ids);
	}

}
