package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fms.core.entity.AppBillHead;
import com.fms.core.entity.PurchaseBill;
import com.fms.core.entity.PurchaseItem;
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

	public List<PurchaseItem> findBillItemByAppNo(String[] appNos) {
		return this.purchaseBillDao.findBillItemByAppNo(appNos);
	}

	public void betchDelPurchase(List<PurchaseItem> items) {
		Set<PurchaseBill> headLs = new HashSet<PurchaseBill>();
		for(PurchaseItem item:items){
			headLs.add(item.getPurchaseBill());
		}
		//删除采购单的表体数据
		this.purchaseBillDao.deletePurchaseItem(items);
		//删除采购单的表头数据
		if(!headLs.isEmpty()){
			List<PurchaseBill> list = new ArrayList<PurchaseBill>(headLs);
			this.purchaseBillDao.deletePurchaseHead(list);
		}
	}

	public int getDataCount(String appBillNo, Date startDate, Date endDate) {
		return this.purchaseBillDao.getDataCount(appBillNo, startDate, endDate);
	}

	public List<PurchaseBill> findPurchaseHeads(String appNo, Date beginappDate,
			Date endappDate, int index, int length) {
		return this.purchaseBillDao.findPurchaseHeads(appNo, beginappDate, endappDate, index, length);
	}

	public PurchaseBill findPurchaseById(String id) {
		return this.purchaseBillDao.findPurchaseById(id);
	}

	public void saveOrUpdate(List<PurchaseBill> heads) {
		this.purchaseBillDao.batchSaveOrUpdate(heads);
	}
	
	
}
