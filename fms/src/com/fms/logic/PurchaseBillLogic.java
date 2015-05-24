package com.fms.logic;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Material;
import com.fms.core.entity.PurchaseBill;
import com.fms.core.entity.PurchaseItem;

/**
 * 采购单Logic
 * 
 * @author Administrator
 * 
 */
public interface PurchaseBillLogic {

	/**
	 * 单个保存采购单表头
	 * 
	 * @param head
	 * @return
	 */
	PurchaseBill saveOrUpdatePurchaseBill(AclUser loginUser, PurchaseBill head);

	/**
	 * 根据申请单号来查询采购单信息
	 * 
	 * @param appNos
	 * @return
	 */
	List<PurchaseItem> findBillItemByAppNo(AclUser loginUser, String[] appNos);

	/**
	 * 批量删除采购单数据（包括表头表体）
	 * 
	 * @param items
	 */
	void betchDelPurchase(AclUser loginUser, List<PurchaseItem> items);

	/**
	 * 获取采购单表头的总条数
	 * 
	 * @param appBillNo
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public int getDataCount(AclUser loginUser, String appBillNo, Date startDate, Date endDate);

	/**
	 * 分页查询采购单表头列表数据
	 * 
	 * @param appNo
	 * @param beginappDate
	 * @param endappDate
	 * @param index
	 * @param length
	 * @return
	 */
	public List<PurchaseBill> findPurchaseHeads(AclUser loginUser, String appNo, Date beginappDate, Date endappDate, int index, int length);

	/**
	 * 根据id查询采购单表头
	 * 
	 * @param id
	 * @return
	 */
	public PurchaseBill findPurchaseById(AclUser loginUser, String id);

	/**
	 * 批量保存采购单表头
	 * 
	 * @param heads
	 */
	public void saveOrUpdate(AclUser loginUser, List<PurchaseBill> heads);

	/**
	 * 查找采购单表头对应的详细列表
	 * 
	 * @param loginUser
	 * @param hid
	 * @return
	 */
	public List<PurchaseItem> findItemByHid(AclUser loginUser, String hid, String hsCode);

	/**
	 * 根据多个采购单表头查找采对应的详细列表
	 * 
	 * @param loginUser
	 * @param hid
	 * @return
	 */
	public List<PurchaseItem> findItemByHids(AclUser loginUser, String[] hid);

	/**
	 * 生效/回卷采购单
	 * 
	 * @return
	 */
	public Boolean purchEffect(AclUser loginUser, String[] hid, Boolean flag);

	/**
	 * 根据id查找采购单表体
	 * 
	 * @param id
	 * @return
	 */
	public PurchaseItem findPurchaseItemById(String id);

	/**
	 * 批量保存采购单表体
	 * 
	 * @param loginUser
	 * @param items
	 * @return
	 */
	public List<PurchaseItem> betchSavePurchaseItems(AclUser loginUser, List<PurchaseItem> items);

	/**
	 * 根据多个采购单表头id查找表头
	 * 
	 * @param loginUser
	 * @param hid
	 * @return
	 */

	public List<PurchaseBill> findPurchaseBillByIds(String[] hid);

	/**
	 * 导出采购单
	 * 
	 * @param hid
	 * @return
	 */
	public String exportPurchase(String[] hid);

	/**
	 * 根据条件查询采购单表头
	 * 
	 * @param isComplete
	 * @param purchStatus
	 * @return
	 */
	public List<PurchaseBill> findPurchaseBill(Boolean isComplete, String purchStatus);

	/**
	 * 根据采购单号，物料编码，物料名称查询采购单表体中的物料信息
	 * 
	 * @param purchaseNo
	 * @param hsCode
	 * @param hsName
	 * @return
	 */
	public List<Material> findPurchaseItemMaterialByNo(String purchaseNo, String hsCode, String hsName);
}
