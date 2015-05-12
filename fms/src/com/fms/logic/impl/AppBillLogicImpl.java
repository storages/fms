package com.fms.logic.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fms.commons.AppBillStatus;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.AppBillHead;
import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.BomImg;
import com.fms.core.entity.Material;
import com.fms.core.entity.PurchaseBill;
import com.fms.core.entity.PurchaseItem;
import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;
import com.fms.dao.AppBillDao;
import com.fms.dao.BomDao;
import com.fms.dao.MaterialDao;
import com.fms.dao.OrderDao;
import com.fms.dao.PurchaseBillDao;
import com.fms.logic.AppBillLogic;

public class AppBillLogicImpl implements AppBillLogic {

	private AppBillDao appBillDao;
	private PurchaseBillDao purchaseBillDao;
	private MaterialDao materialDao;
	private OrderDao orderDao;
	private BomDao bomDao;

	public List<AppBillItem> findAppBillItem(AclUser loginUser, Quotation q) {
		return appBillDao.findAppBillItem(q);
	}

	public AppBillDao getAppBillDao() {
		return appBillDao;
	}

	public void setAppBillDao(AppBillDao appBillDao) {
		this.appBillDao = appBillDao;
	}

	public PurchaseBillDao getPurchaseBillDao() {
		return purchaseBillDao;
	}

	public void setPurchaseBillDao(PurchaseBillDao purchaseBillDao) {
		this.purchaseBillDao = purchaseBillDao;
	}

	public MaterialDao getMaterialDao() {
		return materialDao;
	}

	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public BomDao getBomDao() {
		return bomDao;
	}

	public void setBomDao(BomDao bomDao) {
		this.bomDao = bomDao;
	}

	public AppBillHead saveAppBillHead(AclUser loginUser, AppBillHead head) {
		Integer serialNo = this.appBillDao.getSerialNo("AppBillHead");
		// head = this.appBillDao.findHeadById(head.getId());
		if (serialNo == null || serialNo == 0) {
			serialNo = 1;
		} else if (serialNo > 0) {
			serialNo += 1;
		}
		head.setSerialNo(serialNo);
		String appBillNo = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddmmss");
		appBillNo = "R" + sdf.format(new Date());
		head.setAppNo(appBillNo);
		head.setAppDate(new Date());
		head.setAppStatus(AppBillStatus.UNAPPLY);
		this.appBillDao.saveAppBillHead(head);
		return head;
	}

	public AppBillItem saveAppBillItem(AclUser loginUser, AppBillItem item) {
		return this.appBillDao.saveAppBillItem(item);
	}

	public Integer findDataCount(AclUser loginUser, String appNo, Date beginappDate, Date endappDate, String appStatus) {
		return this.appBillDao.findDataCount(appNo, beginappDate, endappDate, appStatus);
	}

	public List<AppBillHead> findAppBillHeads(AclUser loginUser, String appNo, Date beginappDate, Date endappDate, String appStatus, int index, int length) {
		return this.appBillDao.findAppBillHeads(appNo, beginappDate, endappDate, appStatus, index, length);
	}

	public List<AppBillItem> findItemByHid(AclUser loginUser, String hid, Date beginappDate, Date endappDate, String appStatus, AclUser user) {
		return this.appBillDao.findItemByHid(hid, beginappDate, endappDate, appStatus, user);
	}

	public AppBillHead findHeadById(AclUser loginUser, String hid) {
		return this.appBillDao.findHeadById(hid);
	}

	public AppBillItem findItemById(AclUser loginUser, String id) {
		return this.appBillDao.findItemById(id);
	}

	public List<AppBillItem> findItemByHeadIds(AclUser loginUser, String[] ids) {
		return this.appBillDao.findAppBillItemByHeadIds(ids);
	}

	public List<AppBillHead> betchSaveAppBillHead(AclUser loginUser, List<AppBillHead> datas) {
		datas = this.appBillDao.betchSaveAppBillHead(datas);
		refreshItems(loginUser, datas);
		return datas;
	}

	/**
	 * 更新表体中的表头
	 * 
	 * @param loginUser
	 * @param datas
	 */
	private void refreshItems(AclUser loginUser, List<AppBillHead> datas) {
		if (!datas.isEmpty()) {
			List<AppBillItem> newItems = new ArrayList<AppBillItem>();
			for (AppBillHead head : datas) {
				List<AppBillItem> items = this.findItemByHid(loginUser, head.getId(), null, null, null, null);
				for (AppBillItem item : items) {
					item.setHead(head);
					newItems.add(item);
				}
			}
			this.betchSaveAppBillItem(loginUser, newItems);
		}
	}

	public List<AppBillItem> betchSaveAppBillItem(AclUser loginUser, List<AppBillItem> datas) {
		List<AppBillItem> newList = new ArrayList<AppBillItem>();
		if (datas != null && datas.size() > 0) {
			AppBillHead head = this.appBillDao.findHeadById(datas.get(0).getHead().getId());
			this.appBillDao.betchSaveAppBillItem(datas);
			newList = this.appBillDao.findAppBillItemByHead(head);
			Double num = 0d;
			Double amount = 0d;
			Boolean isVerify = true;// 记录表体中是否全部都审批通过了
			for (AppBillItem item : newList) {
				num += item.getTotalQty() == null ? 0d : item.getTotalQty();
				amount += item.getAmount() == null ? 0d : item.getAmount();
				if (!AppBillStatus.APPROVED.equals(item.getAppStatus())) {
					isVerify = false;
				}
			}
			head.setItemQty(newList.size());
			head.setTotalAmount(amount);
			head.setTotalQty(num);
			head.setApprovaledQty(this.countVerifyQty(loginUser, newList));
			head.setUnApprovalQty(this.countUnVerifyQty(newList));
			if (isVerify) {
				head.setAppStatus(AppBillStatus.APPROVED);// 设置表头状态审批通过
			} else {
				head.setAppStatus(AppBillStatus.UNAPPLY);// 设置表头状态未申请
			}
			head = this.appBillDao.saveAppBillHead(head);
			// head = this.findHeadById(head.getId());
			for (AppBillItem item : newList) {
				item.setHead(head);
			}
		}
		return this.appBillDao.betchSaveAppBillItem(newList);
	}

	/**
	 * 统计已审核的数量
	 * 
	 * @param list
	 * @return
	 */
	private Double countVerifyQty(AclUser loginUser, List<AppBillItem> list) {
		Double num = 0d;
		for (AppBillItem item : list) {
			if (AppBillStatus.APPROVED.equals(item.getAppStatus())) {
				num += 1;
			}
		}
		return num;
	}

	/**
	 * 统计未审核的数量
	 * 
	 * @param list
	 * @return
	 */
	private Double countUnVerifyQty(List<AppBillItem> list) {
		Double num = 0d;
		for (AppBillItem item : list) {
			if (AppBillStatus.APPROVALING.equals(item.getAppStatus()) || AppBillStatus.APPROVEDNOT.equals(item.getAppStatus()) || AppBillStatus.UNAPPLY.equals(item.getAppStatus())) {
				num += 1;
			}
		}
		return num;
	}

	public Quotation findQuotationByCondention(Material m, Scmcoc scm, Date date) {
		return this.findQuotationByCondention(m, scm, date);
	}

	public void delAppBillItem(AclUser loginUser, String[] ids) {
		if (null != ids && ids.length > 0) {
			Double num = 0d;
			Double amount = 0d;
			AppBillItem item = this.appBillDao.findItemById(ids[0]);
			AppBillHead head = this.appBillDao.findHeadById(item.getHead().getId());
			this.appBillDao.delAppBillItem(ids);
			List<AppBillItem> list = this.appBillDao.findAppBillItemByHead(head);
			for (AppBillItem abi : list) {
				num += item.getTotalQty() == null ? 0d : item.getTotalQty();
				amount += item.getAmount() == null ? 0d : item.getAmount();
			}
			head.setItemQty(list.size());
			head.setTotalAmount(amount);
			head.setTotalQty(num);
			head.setApprovaledQty(this.countVerifyQty(loginUser, list));
			head.setUnApprovalQty(this.countUnVerifyQty(list));
			head = this.saveAppBillHead(loginUser, head);
		}
	}

	public void delAppBillHead(AclUser loginUser, String[] ids) {
		if (null != ids && ids.length > 0) {
			try {
				this.appBillDao.deleteItemsByHeadId(ids);
				this.appBillDao.deleteAppBillHead(ids);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void submitApp(AclUser loginUser, String[] ids) {
		List<AppBillItem> items = this.appBillDao.findAppBillItemByHeadIds(ids);
		if (items != null && items.size() > 0) {
			for (AppBillItem item : items) {
				item.setAppStatus(AppBillStatus.APPROVALING);
			}
			items = this.appBillDao.batchSaveOrUpdate(items);
			List<AppBillHead> heads = this.appBillDao.findAppBillHead(ids);
			if (heads != null && heads.size() > 0) {
				for (AppBillHead head : heads) {
					head.setAppStatus(AppBillStatus.APPROVALING);
				}
				this.appBillDao.betchSaveAppBillHead(heads);
			}
		}
	}

	/**
	 * 根据表体id来查询表头
	 * 
	 * @param itemId
	 * @return
	 */
	public AppBillHead findHeadByItemId(AclUser loginUser, String itemId) {
		return this.appBillDao.findHeadByItemId(itemId);
	}

	/**
	 * 审批申请单
	 */
	public List<AppBillItem> verifyItem(AclUser loginUser, String[] itemIds, String verifyFlag, AclUser user, String mess) {
		List<AppBillItem> data = new ArrayList<AppBillItem>();
		Set<AppBillHead> hData = new HashSet<AppBillHead>();
		if (null != itemIds && itemIds.length > 0 && !"".equals(verifyFlag)) {
			data = this.appBillDao.findItemByIds(itemIds);
			for (AppBillItem item : data) {
				AppBillHead head = item.getHead();
				head.setAppStatus(verifyFlag);
				hData.add(head);
				if ("3".equals(verifyFlag)) {
					item.setNoPassReason(mess);
				}
				item.setAppStatus(verifyFlag);
				item.setVerifyDate(new Date());
				item.setVerifyUser(user.getLoginName());
			}
			List<AppBillHead> l = new ArrayList<AppBillHead>(hData);
			this.appBillDao.batchSaveOrUpdate(l);
			data = this.betchSaveAppBillItem(loginUser, data);
			// 把审批通过的申请单转换成采购单
			appBillConvertPurchaseBill(loginUser, data);
		}
		return data;
	}

	/**
	 * 把审批通过的申请单转换成采购单
	 * 
	 * @param data
	 */
	private void appBillConvertPurchaseBill(AclUser loginUser, List<AppBillItem> data) {
		List<PurchaseBill> purchaseBills = new ArrayList<PurchaseBill>();
		List<PurchaseItem> PurchaseItems = new ArrayList<PurchaseItem>();
		Map<String, PurchaseBill> PurchaseMap = new HashMap<String, PurchaseBill>();
		if (null != data && data.size() > 0) {
			int no = 0;
			for (AppBillItem item : data) {
				if (item.getAppStatus().equals(AppBillStatus.APPROVEDNOT)) {
					continue;
				}
				String key = item.getScmcoc().getCode() + "/" + item.getScmcoc().getName();
				PurchaseBill purch = null;
				if (PurchaseMap.get(key) != null) {
					purch = PurchaseMap.get(key);
				} else {
					// 创建新采购单表头
					purch = new PurchaseBill();
					Integer serialNo = this.appBillDao.getSerialNo("PurchaseBill");
					if (serialNo == null || serialNo == 0) {
						serialNo = 1;
					} else if (serialNo > 0) {
						serialNo += 1;
					}
					purch.setSerialNo(serialNo);// 流水号

					String purchaseNo = "";
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
					purchaseNo = "C" + sdf.format(new Date()) + (no + 1);
					purch.setPurchaseNo(purchaseNo);// 采购单号
					purch.setAppBillNo(item.getHead().getAppNo());// 申请单号
					purch.setScmcoc(item.getScmcoc());// 供应商
					purch.setOrderNo(item.getHead().getOrderNo());// 订单号
					// 先保存采购单表头
					purch = this.purchaseBillDao.saveOrUpdatePurchaseBill(purch);
					purchaseBills.add(purch);
					key = item.getScmcoc().getCode() + "/" + item.getScmcoc().getName();
					PurchaseMap.put(key, purch);
					no++;
				}

				// 创建详细信息
				PurchaseItem purchaseItem = new PurchaseItem();

				purchaseItem.setMaterial(item.getMaterial());// 物料
				purchaseItem.setQty(item.getTotalQty());// 采购数量
				purchaseItem.setPrice(item.getPrice());// 单价
				purchaseItem.setAmount(item.getAmount());// 金额
				purchaseItem.setLinkAppBillItemId(item.getId());
				purchaseItem.setPurchaseBill(purch);
				purchaseItem = (PurchaseItem) this.purchaseBillDao.saveOrUpdateNoCache(purchaseItem);

			}
			// 更新采购单表头信息
			updatePurchaseBillInfo(loginUser, purchaseBills);
		}
	}

	/**
	 * 根据采购单表体来更新采购单表头信息
	 * 
	 * @param purchaseBills
	 */
	private void updatePurchaseBillInfo(AclUser loginUser, List<PurchaseBill> purchaseBills) {
		try {
			for (PurchaseBill head : purchaseBills) {
				head = this.purchaseBillDao.findPurchaseBillById(head.getId());
				List<PurchaseItem> items = this.purchaseBillDao.findItemById(head.getId(), null);
				if (items != null && items.size() > 0) {
					Double amount = 0d;
					for (PurchaseItem i : items) {
						amount += i.getAmount();
					}
					head.setTotalAmount(amount);
					head.setItemNo(items.size());
					this.purchaseBillDao.saveOrUpdatePurchaseBill(head);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<AppBillItem> findItemByIds(AclUser loginUser, String[] ids) {
		return this.appBillDao.findItemByIds(ids);
	}

	public List<AppBillHead> findHeadsByHeadIds(AclUser loginUser, String[] headIds) {
		return this.appBillDao.findHeadsByHeadIds(headIds);
	}

	public void cancelAppBill(AclUser loginUser, String[] appBillItemIds) {
		if (null != appBillItemIds && !"".equals(appBillItemIds)) {
			List<PurchaseItem> list = this.purchaseBillDao.findBillItemByAppBillItemIds(appBillItemIds);
			for (PurchaseItem item : list) {
				if (item.getIsBuy()) {
					return;
				}
			}
			List<PurchaseBill> heads = getHeadsByPurchaseItem(loginUser, list);
			// 删除表体
			this.purchaseBillDao.deletePurchaseItem(list);
			// 删除表头
			deleteHeadNotItem(heads);
		}

	}

	private List<PurchaseBill> getHeadsByPurchaseItem(AclUser loginUser, List<PurchaseItem> list) {
		return this.purchaseBillDao.getHeadByPurchaseItem(list);
	}

	private void deleteHeadNotItem(List<PurchaseBill> list) {
		List<PurchaseBill> heads = new ArrayList<PurchaseBill>();
		for (PurchaseBill bill : list) {
			List<PurchaseItem> items = this.purchaseBillDao.findItemById(bill.getId(), null);
			if (null == items || items.size() <= 0) {
				heads.add(bill);
			}
		}
		if (null != heads && heads.size() >= 0) {
			this.purchaseBillDao.deleteAll(heads);
		}
	}

	public List<Material> findAllMaterialByCondent(String hsCode, String hsName, String imgExgFlag, String isFromBom, String orderNo) {
		try {
			if ("N".equals(isFromBom)) {
				return this.materialDao.findAllMaterialExgs(hsCode, hsName, null, imgExgFlag, -1, -1);
			} else if ("Y".equals(isFromBom)) {
				List<Material> imgList = new ArrayList<Material>();
				// 首先根据订单号查询出所有的成品
				List<Material> exgMaterials = this.orderDao.findAllExgByOrderNo(orderNo);
				if (null != exgMaterials && exgMaterials.size() > 0) {
					String[] exgCodes = new String[exgMaterials.size()];
					for (int i = 0; i < exgMaterials.size(); i++) {
						exgCodes[i] = exgMaterials.get(i).getHsCode();
					}
					// 根据成品到BOM表中查找对应的原料
					List<BomImg> bomImgs = this.bomDao.findBomImgByHsCodes(exgCodes, hsCode, hsName);
					for (BomImg bi : bomImgs) {
						imgList.add(bi.getMaterial());
					}
				}
				return imgList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
