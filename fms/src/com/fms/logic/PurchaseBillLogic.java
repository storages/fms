package com.fms.logic;

import java.util.List;

import com.fms.core.entity.PurchaseBill;
import com.fms.core.entity.PurchaseItem;

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
	
	/**
	 * 根据申请单号来查询采购单信息
	 * @param appNos
	 * @return
	 */
	List<PurchaseItem> findBillItemByAppNo(String[] appNos);
	
	/**
	 * 批量删除采购单数据（包括表头表体）
	 * @param items
	 */
	void betchDelPurchase(List<PurchaseItem> items);
}
