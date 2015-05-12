package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fms.commons.PurchaseBillStatus;
import com.fms.core.entity.AclUser;
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

	public PurchaseBill saveOrUpdatePurchaseBill(AclUser loginUser, PurchaseBill head) {
		return this.purchaseBillDao.saveOrUpdatePurchaseBill(head);
	}

	public List<PurchaseItem> findBillItemByAppNo(AclUser loginUser, String[] appNos) {
		return this.purchaseBillDao.findBillItemByAppNo(appNos);
	}

	public void betchDelPurchase(AclUser loginUser, List<PurchaseItem> items) {
		Set<PurchaseBill> headLs = new HashSet<PurchaseBill>();
		for (PurchaseItem item : items) {
			headLs.add(item.getPurchaseBill());
		}
		// 删除采购单的表体数据
		this.purchaseBillDao.deletePurchaseItem(items);
		// 删除采购单的表头数据
		if (!headLs.isEmpty()) {
			List<PurchaseBill> list = new ArrayList<PurchaseBill>(headLs);
			this.purchaseBillDao.deletePurchaseHead(list);
		}
	}

	public int getDataCount(AclUser loginUser, String appBillNo, Date startDate, Date endDate) {
		return this.purchaseBillDao.getDataCount(appBillNo, startDate, endDate);
	}

	public List<PurchaseBill> findPurchaseHeads(AclUser loginUser, String appNo, Date beginappDate, Date endappDate, int index, int length) {
		return this.purchaseBillDao.findPurchaseHeads(appNo, beginappDate, endappDate, index, length);
	}

	public PurchaseBill findPurchaseById(AclUser loginUser, String id) {
		return this.purchaseBillDao.findPurchaseById(id);
	}

	public void saveOrUpdate(AclUser loginUser, List<PurchaseBill> heads) {
		this.purchaseBillDao.batchSaveOrUpdate(heads);
	}

	public List<PurchaseItem> findItemByHid(AclUser loginUser, String hid, String hsCode) {
		return this.purchaseBillDao.findItemById(hid, hsCode);
	}

	public List<PurchaseItem> findItemByHids(AclUser loginUser, String[] hid) {
		return this.purchaseBillDao.findItemByHids(hid);
	}

	public Boolean purchEffect(AclUser loginUser, String[] hid, Boolean flag) {
		List<PurchaseItem> items = this.findItemByHids(loginUser, hid);
		for (PurchaseItem item : items) {
			item.setIsBuy(flag);
			item.setPurchaseDate(new Date());
		}
		this.purchaseBillDao.batchSaveOrUpdate(items);
		List<PurchaseBill> pbills = this.purchaseBillDao.findPurchaseBillByIds(hid);
		if (pbills != null && pbills.size() > 0) {
			for (PurchaseBill pb : pbills) {
				pb.setPurchStatus(flag ? PurchaseBillStatus.EFFECTED : PurchaseBillStatus.UNEFFECT);
				pb = this.purchaseBillDao.saveOrUpdatePurchaseBill(pb);
				items = this.findItemByHid(loginUser, pb.getId(), null);
				for (PurchaseItem it : items) {
					it.setPurchaseBill(pb);
				}
				this.purchaseBillDao.batchSaveOrUpdate(items);
			}
			return Boolean.TRUE;
		}
		return null;
	}

	public PurchaseItem findPurchaseItemById(String id) {
		return (PurchaseItem) this.purchaseBillDao.findPurchaseItemById(id);
	}

	public List<PurchaseItem> betchSavePurchaseItems(AclUser loginUser, List<PurchaseItem> items) {
		return this.purchaseBillDao.batchSaveOrUpdate(items);
	}

	public List<PurchaseBill> findPurchaseBillByIds(String[] hid) {
		return this.purchaseBillDao.findPurchaseBillByIds(hid);
	}

	public String exportPurchase(String[] hid) {

		return null;
	}
}
