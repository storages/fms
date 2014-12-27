package com.fms.dao;

import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.PurchaseBill;
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
	 * 批量保存采购单
	 * @param data
	 * @return
	 */
	List<PurchaseBill> betchSavePurchaseBill(List<PurchaseBill> data);
}
