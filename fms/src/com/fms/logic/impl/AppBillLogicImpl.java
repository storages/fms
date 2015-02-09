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
import com.fms.core.entity.Material;
import com.fms.core.entity.PurchaseBill;
import com.fms.core.entity.PurchaseItem;
import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;
import com.fms.dao.AppBillDao;
import com.fms.dao.PurchaseBillDao;
import com.fms.logic.AppBillLogic;

public class AppBillLogicImpl implements AppBillLogic {

	private AppBillDao appBillDao;
	private PurchaseBillDao purchaseBillDao;

	public List<AppBillItem> findAppBillItem(Quotation q) {
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

	public AppBillHead saveAppBillHead(AppBillHead head) {
		Integer serialNo = this.appBillDao.getSerialNo("AppBillHead");
		//head = this.appBillDao.findHeadById(head.getId());
		if(serialNo==null || serialNo==0){
			serialNo = 1;
		}else if(serialNo>0){
			serialNo+=1;
		}
		head.setSerialNo(serialNo);
		String appBillNo = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddmmss");
		appBillNo = "R"+sdf.format(new Date());
		head.setAppNo(appBillNo);
		head.setAppDate(new Date());
		head.setAppStatus(AppBillStatus.UNAPPLY);
		this.appBillDao.saveAppBillHead(head);
		return head;
	}

	public AppBillItem saveAppBillItem(AppBillItem item) {
		return this.appBillDao.saveAppBillItem(item);
	}

	public Integer findDataCount(String appNo, Date beginappDate,Date endappDate,String appStatus) {
		return this.appBillDao.findDataCount(appNo, beginappDate, endappDate,appStatus);
	}

	public List<AppBillHead> findAppBillHeads(String appNo, Date beginappDate,Date endappDate,String appStatus, int index, int length) {
		return this.appBillDao.findAppBillHeads(appNo, beginappDate, endappDate,appStatus, index, length);
	}

	public List<AppBillItem> findItemByHid(String hid,Date beginappDate,Date endappDate,String appStatus,AclUser user) {
		return this.appBillDao.findItemByHid(hid,beginappDate,endappDate,appStatus,user);
	}

	public AppBillHead findHeadById(String hid) {
		return this.appBillDao.findHeadById(hid);
	}

	public AppBillItem findItemById(String id) {
		return this.appBillDao.findItemById(id);
	}
	
	public List<AppBillItem> findItemByHeadIds(String[] ids) {
		return this.appBillDao.findAppBillItemByHeadIds(ids);
	}

	public List<AppBillHead> betchSaveAppBillHead(List<AppBillHead> datas) {
		return this.appBillDao.betchSaveAppBillHead(datas);
	}

	
	public List<AppBillItem> betchSaveAppBillItem(List<AppBillItem> datas) {
		List<AppBillItem> newList = new ArrayList<AppBillItem>();
		if(datas!=null&&datas.size()>0){
			AppBillHead head = this.appBillDao.findHeadById(datas.get(0).getHead().getId());
			this.appBillDao.betchSaveAppBillItem(datas);
			newList = this.appBillDao.findAppBillItemByHead(head);
			Double num = 0d;
			Double amount = 0d;
			Boolean isVerify = true;//记录表体中是否全部都审批通过了
			for(AppBillItem item:newList){
				num+=item.getTotalQty()==null?0d:item.getTotalQty();
				amount+=item.getAmount()==null?0d:item.getAmount();
				if(!AppBillStatus.APPROVED.equals(item.getAppStatus())){
					isVerify = false;
				}
			}
			head.setItemQty(newList.size());
			head.setTotalAmount(amount);
			head.setTotalQty(num);
			head.setApprovaledQty(this.countVerifyQty(newList));
			head.setUnApprovalQty(this.countUnVerifyQty(newList));
			if(isVerify){
				head.setAppStatus(AppBillStatus.APPROVED);//设置表头状态审批通过
			}else{
				head.setAppStatus(AppBillStatus.UNAPPLY);//设置表头状态未申请
			}
			head = this.appBillDao.saveAppBillHead(head);
			//head = this.findHeadById(head.getId());
			for(AppBillItem item:newList){
				item.setHead(head);
			}
		}
		return this.appBillDao.betchSaveAppBillItem(newList);
	}

	/**
	 * 统计已审核的数量
	 * @param list
	 * @return
	 */
	private Double countVerifyQty(List<AppBillItem> list){
		Double num = 0d;
		for(AppBillItem item:list){
			if(AppBillStatus.APPROVED.equals(item.getAppStatus())){
				num+=1;
			}
		}
		return num;
	}
	
	/**
	 * 统计未审核的数量
	 * @param list
	 * @return
	 */
	private Double countUnVerifyQty(List<AppBillItem> list){
		Double num = 0d;
		for(AppBillItem item:list){
			if(AppBillStatus.APPROVALING.equals(item.getAppStatus())||AppBillStatus.APPROVEDNOT.equals(item.getAppStatus())||AppBillStatus.UNAPPLY.equals(item.getAppStatus())){
				num+=1;
			}
		}
		return num;
	}
	
	public Quotation findQuotationByCondention(Material m, Scmcoc scm, Date date) {
		return this.findQuotationByCondention(m, scm, date);
	}

	public void delAppBillItem(String[] ids) {
		if(null!=ids && ids.length>0){
			Double num = 0d;
			Double amount = 0d;
			AppBillItem item = this.appBillDao.findItemById(ids[0]);
			AppBillHead head = this.appBillDao.findHeadById(item.getHead().getId());
			this.appBillDao.delAppBillItem(ids);
			List<AppBillItem> list = this.appBillDao.findAppBillItemByHead(head);
			for(AppBillItem abi:list){
				num+=item.getTotalQty()==null?0d:item.getTotalQty();
				amount+=item.getAmount()==null?0d:item.getAmount();
			}
			head.setItemQty(list.size());
			head.setTotalAmount(amount);
			head.setTotalQty(num);
			head.setApprovaledQty(this.countVerifyQty(list));
			head.setUnApprovalQty(this.countUnVerifyQty(list));
			head = this.saveAppBillHead(head);
		}
	}
	
	
	public void delAppBillHead(String[] ids) {
		if(null!=ids && ids.length>0){
			try{
				this.appBillDao.deleteItemsByHeadId(ids);
				this.appBillDao.deleteAppBillHead(ids);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public void submitApp(String [] ids){
		List<AppBillItem> items = this.appBillDao.findAppBillItemByHeadIds(ids);
		if(items!=null&&items.size()>0){
			for(AppBillItem item:items){
				item.setAppStatus(AppBillStatus.APPROVALING);
			}
			items = this.appBillDao.batchSaveOrUpdate(items);
			List<AppBillHead> heads = this.appBillDao.findAppBillHead(ids);
			if(heads!=null&&heads.size()>0){
				for(AppBillHead head:heads){
					head.setAppStatus(AppBillStatus.APPROVALING);
				}
				this.appBillDao.betchSaveAppBillHead(heads);
			}
		}
	}
	
	/**
	 * 根据表体id来查询表头
	 * @param itemId
	 * @return
	 */
	public AppBillHead findHeadByItemId(String itemId){
		return this.appBillDao.findHeadByItemId(itemId);
	}
	
	/**
	 * 审批申请单
	 */
	public List<AppBillItem> verifyItem(String [] itemIds,String verifyFlag,AclUser user,String mess){
		List<AppBillItem> data = new ArrayList<AppBillItem>();
		Set<AppBillHead> hData = new HashSet<AppBillHead>();
		if(null!=itemIds && itemIds.length>0 && !"".equals(verifyFlag)){
			data = this.appBillDao.findItemByIds(itemIds);
			for(AppBillItem item:data){
				AppBillHead head = item.getHead();
				head.setAppStatus(verifyFlag);
				hData.add(head);
				if("3".equals(verifyFlag)){
					item.setNoPassReason(mess);
				}
				item.setAppStatus(verifyFlag);
				item.setVerifyDate(new Date());
				item.setVerifyUser(user.getLoginName());
			}
			List<AppBillHead> l = new ArrayList<AppBillHead>(hData);
			this.appBillDao.batchSaveOrUpdate(l);
			data = this.betchSaveAppBillItem(data);
			//把审批通过的申请单转换成采购单
			appBillConvertPurchaseBill(data);
		}
		return data;
	}
	
	/**
	 * 把审批通过的申请单转换成采购单
	 * @param data
	 */
	private void appBillConvertPurchaseBill(List<AppBillItem> data){
		List<PurchaseBill> purchaseBills = new ArrayList<PurchaseBill>();
		List<PurchaseItem> PurchaseItems = new ArrayList<PurchaseItem>();
		Map<String,PurchaseBill> PurchaseMap = new HashMap<String,PurchaseBill>();
		if(null!=data && data.size()>0){
			for(AppBillItem item:data){
				if(item.getAppStatus().equals(AppBillStatus.APPROVEDNOT)){
					continue;
				}
				String key = item.getScmcoc().getCode()+"/"+item.getScmcoc().getName();
				PurchaseBill purch = null;
				if(PurchaseMap.get(key)!=null){
					purch = PurchaseMap.get(key);
				}else{
					//创建新采购单表头
					purch = new PurchaseBill();
					Integer serialNo = this.appBillDao.getSerialNo("PurchaseBill");
					if(serialNo==null || serialNo==0){
						serialNo = 1;
					}else if(serialNo>0){
						serialNo+=1;
					}
					purch.setSerialNo(serialNo);//流水号
					
					String purchaseNo = "";
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddmmss");
					purchaseNo = "C"+sdf.format(new Date());
					purch.setPurchaseNo(purchaseNo);//采购单号
					purch.setAppBillNo(item.getHead().getAppNo());//申请单号
					purch.setScmcoc(item.getScmcoc());//供应商
					//先保存采购单表头
					purch = this.purchaseBillDao.saveOrUpdatePurchaseBill(purch);
					purchaseBills.add(purch);
					key = item.getScmcoc().getCode()+"/"+item.getScmcoc().getName();
					PurchaseMap.put(key, purch);
				}
				
				//创建详细信息
				PurchaseItem purchaseItem = new PurchaseItem();
				
				purchaseItem.setMaterial(item.getMaterial());//物料
				purchaseItem.setQty(item.getTotalQty());//采购数量
				purchaseItem.setPrice(item.getPrice());//单价
				purchaseItem.setAmount(item.getAmount());//金额
				purchaseItem.setLinkAppBillItemId(item.getId()); 
				purchaseItem.setPurchaseBill(purch); 
				purchaseItem = (PurchaseItem) this.purchaseBillDao.saveOrUpdateNoCache(purchaseItem);
				
				 
			}
			//purchaseBills = this.purchaseBillDao.betchSavePurchaseBill(purchaseBills);
			//更新采购单表头信息
			updatePurchaseBillInfo(purchaseBills);
		}
	}
	
	/**
	 * 根据采购单表体来更新采购单表头信息
	 * @param purchaseBills
	 */
	private void updatePurchaseBillInfo(List<PurchaseBill> purchaseBills){
		try{
		for(PurchaseBill head:purchaseBills){
			head = this.purchaseBillDao.findPurchaseBillById(head.getId());
			List<PurchaseItem> items = this.purchaseBillDao.findItemById(head.getId());
			if(items!=null && items.size()>0){
				head.setItemNo(items.size());
				Double amount = 0d;
				for(PurchaseItem i:items){
					amount+=i.getAmount();
				}
				head.setTotalAmount(amount);
				this.purchaseBillDao.saveOrUpdatePurchaseBill(head);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<AppBillItem> findItemByIds(String[] ids) {
		return this.appBillDao.findItemByIds(ids);
	}

	public List<AppBillHead> findHeadsByHeadIds(String[] headIds) {
		return this.appBillDao.findHeadsByHeadIds(headIds);
	}

	public void cancelAppBill(String[] appBillItemIds) {
		if(null!=appBillItemIds && !"".equals(appBillItemIds)){
			List<PurchaseItem> list = this.purchaseBillDao.findBillItemByAppBillItemIds(appBillItemIds);
			for(PurchaseItem item:list){
				if(item.getIsBuy()){
					return;
				}
			}
			List<PurchaseBill> heads = getHeadsByPurchaseItem(list);
			//删除表体
			this.purchaseBillDao.deletePurchaseItem(list);
			//删除表头
			deleteHeadNotItem(heads);
		}
		
	}

	private List<PurchaseBill> getHeadsByPurchaseItem(List<PurchaseItem> list){
		return this.purchaseBillDao.getHeadByPurchaseItem(list);
	}
	
	private void deleteHeadNotItem(List<PurchaseBill> list){
		List<PurchaseBill> heads = new ArrayList<PurchaseBill>();
		for(PurchaseBill bill:list){
			List<PurchaseItem> items = this.purchaseBillDao.findItemById(bill.getId());
			if(null==items || items.size()<=0){
				heads.add(bill);
			}
		}
		if(null!=heads && heads.size()>=0){
			this.purchaseBillDao.deleteAll(heads);
		}
	}
}
