package com.fms.dao;

import java.util.Date;
import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.AppBillHead;
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
	
	/**
	 *	根据申请单号来查询采购单信息
	 */
	public List<PurchaseItem> findBillItemByAppNo(String[] appNos);
	/**
	 * 根据申请单id来查询采购单信息
	 * @param appBillItemIds
	 * @return
	 */
	List<PurchaseItem> findBillItemByAppBillItemIds(String[] appBillItemIds);
	/**
	 * 删除采购单表体
	 * @param data
	 */
	void deletePurchaseItem(List<PurchaseItem> data);
	/**
	 * 删除采购单表头
	 * @param data
	 */
	void deletePurchaseHead(List<PurchaseBill> data);
	/**
	 * 根据表体获取表头
	 * @param list
	 * @return
	 */
	List<PurchaseBill> getHeadByPurchaseItem(List<PurchaseItem> list);
	
	/**
	 * 获取采购单的总条数
	 * @param appBillNo
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int getDataCount(String appBillNo,Date startDate,Date endDate);
	
	/**
	 * 获取采购单表头信息(分页)
	 * @param appNo
	 * @param beginappDate
	 * @param endappDate
	 * @param index
	 * @param length
	 * @return
	 */
	public List<PurchaseBill> findPurchaseHeads(String appBillNo, Date startDate, Date endDate, int index, int length);
	
	/**
	 * 根据id查询采购单
	 * @param id
	 * @return
	 */
	public PurchaseBill findPurchaseById(String id);
	
	/**
	 * 查找采购单表头对应的详细列表
	 * @param loginUser
	 * @param hid
	 * @return
	 *//*
	public List<PurchaseItem> findItemByHid(String hid);*/
}
