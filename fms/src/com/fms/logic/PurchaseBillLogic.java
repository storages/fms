package com.fms.logic;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.AppBillHead;
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
	PurchaseBill saveOrUpdatePurchaseBill(AclUser loginUser,PurchaseBill head);
	
	/**
	 * 根据申请单号来查询采购单信息
	 * @param appNos
	 * @return
	 */
	List<PurchaseItem> findBillItemByAppNo(AclUser loginUser,String[] appNos);
	
	/**
	 * 批量删除采购单数据（包括表头表体）
	 * @param items
	 */
	void betchDelPurchase(AclUser loginUser,List<PurchaseItem> items);
	
	/**
	 * 获取采购单表头的总条数
	 * @param appBillNo
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int getDataCount(AclUser loginUser,String appBillNo,Date startDate,Date endDate);
	
	/**
	 * 分页查询采购单表头列表数据
	 * @param appNo
	 * @param beginappDate
	 * @param endappDate
	 * @param index
	 * @param length
	 * @return
	 */
	public List<PurchaseBill> findPurchaseHeads(AclUser loginUser,String appNo, Date beginappDate,Date endappDate, int index, int length);
	
	/**
	 * 根据id查询采购单表头
	 * @param id
	 * @return
	 */
	public PurchaseBill findPurchaseById(AclUser loginUser,String id);
	
	/**
	 * 批量保存采购单表头
	 * @param heads
	 */
	public void saveOrUpdate(AclUser loginUser,List<PurchaseBill> heads);
}
