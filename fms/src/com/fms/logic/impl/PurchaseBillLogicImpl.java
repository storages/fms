package com.fms.logic.impl;

import com.fms.core.entity.PurchaseBill;
import com.fms.dao.PurchaseBillDao;
import com.fms.logic.PurchaseBillLogic;

public class PurchaseBillLogicImpl implements PurchaseBillLogic {

	protected PurchaseBillDao purchaseBillDao;

	public PurchaseBillDao getPurchaseBillDao() {
		return purchaseBillDao;
	}

	public void setPurchaseBillDao(PurchaseBillDao purchaseBillDao) {
		this.purchaseBillDao = purchaseBillDao;
	}

	public PurchaseBill saveOrUpdatePurchaseBill(PurchaseBill head) {
		return this.purchaseBillDao.saveOrUpdatePurchaseBill(head);
	}
	
	
}
