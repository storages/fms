package com.fms.logic;

import com.fms.core.entity.PurchaseBill;

/**
 * 采购单Logic
 * @author Administrator
 *
 */
public interface PurchaseBillLogic {

	/**
	 * 单个保存采购单表头
	 * @param head
	 * @return
	 */
	PurchaseBill saveOrUpdatePurchaseBill(PurchaseBill head);
}
