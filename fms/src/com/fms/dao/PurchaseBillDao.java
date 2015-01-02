package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.PurchaseBill;
import com.fms.core.entity.PurchaseItem;
import com.fms.core.entity.Quotation;

/**
 * 采购单Dao
 * @author Administrator
 *
 */
public interface PurchaseBillDao extends BaseDao{
	/**
	 * 根据报价单查找采购单
	 * @param q
	 * @return
	 */
	List<PurchaseBill> findPurchaseBill(Quotation quotation);
	
	/**
	 * 批量保存采购单表头
	 * @param data
	 * @return
	 */
	List<PurchaseBill> betchSavePurchaseBill(List<PurchaseBill> data);
	
	/**
	 * 单个保存采购单表头
	 * @param head
	 * @return
	 */
	PurchaseBill saveOrUpdatePurchaseBill(PurchaseBill head);
	
	/**
	 * 根据采购单表头Id来查询采购单表体
	 * @param id
	 * @return
	 */
	List<PurchaseItem> findItemById(String id);
	
	/**
	 * 根据采购单表头Id查询表头信息
	 * @param id
	 * @return
	 */
	PurchaseBill findPurchaseBillById(String id);
}
