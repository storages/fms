package com.fms.logic.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fms.core.entity.AppBillHead;
import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.PurchaseBill;
import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;
import com.fms.dao.AppBillDao;
import com.fms.dao.PurchaseBillDao;
import com.fms.dao.QuotationDao;
import com.fms.logic.QuotationLogic;

public class QuotationLogicImpl implements QuotationLogic{

	protected QuotationDao quotationDao;
	protected AppBillDao appBillDao;
	protected PurchaseBillDao purchaseBillDao;
	
	
	public List<Quotation> findQuotations(String scmCocName, String hsCode,Date begineffectDate,Date endeffectDate, int index, int length) {
		return quotationDao.findQuotations(scmCocName, hsCode, begineffectDate,endeffectDate, index, length);
	}
	
	public Integer findDataCount(String scmCocName, String hsCode,Date begineffectDate,Date endeffectDate) {
		return quotationDao.findDataCount(scmCocName, hsCode, begineffectDate,endeffectDate);
	}
	
	public QuotationDao getQuotationDao() {
		return quotationDao;
	}
	public void setQuotationDao(QuotationDao quotationDao) {
		this.quotationDao = quotationDao;
	}
	public List<Quotation> saveQuotations(List<Quotation> list){
		Integer serialNo = quotationDao.getSerialNo("Quotation");
		for(Quotation q:list){
			if(q.getSerialNo()==null){
				if(null!=serialNo && serialNo<0){
					throw new RuntimeException("获取流水号时，可能给定的类名不正确!");
				}else if(serialNo==null){
					serialNo = 1;
				}else{
					serialNo+=1;
				}
				q.setSerialNo(serialNo);
			}
		}
		return this.quotationDao.batchSaveOrUpdate(list);
	}
	public List<Scmcoc> findAll(){
		return quotationDao.findAll();
	}
	public Scmcoc findById(String id){
		return quotationDao.findById(id);
	}
	
	public List<Quotation> findQuotationByIds(String [] ids){
		return this.quotationDao.findQuotationByIds(ids);
	}

	public Quotation findQuotationById(String entityName,String id) {
		return (Quotation) this.quotationDao.findEntityById(entityName, id);
	}
	public void delQuotationById(String[] ids){
		this.quotationDao.delQuotationById(ids);
	}

	public int updatePrice(String[] ids) {
		int count = 0;
		if(ids!=null && ids.length>0){
			List<Quotation> quoList = this.findQuotationByIds(ids);
			for(Quotation q:quoList){
				if(q.getPrice()==null || q.getPrice()==0d || q.getEffectDate()==null){
					return -1;
				}
			}
			for(Quotation q:quoList){
				//更新申请单
				count+=updateAppBillItem(q);
				//更新采购单
				count+=updatePurchaseBill(q);
			}
		}
		return count;
	}
	
	/**
	 * 根据报价单的生效日期来查找申请单
	 * @param appDate
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<AppBillItem> findAppBillItem(Quotation q){
		return this.appBillDao.findAppBillItem(q);
	}
	
	/**
	 * 根据报价单的生效日期来更新采购单单价
	 * @param appDate
	 * @return
	 */
	@SuppressWarnings("unused")
	private int updatePurchaseBill(Quotation q){
		List<PurchaseBill> list = this.purchaseBillDao.findPurchaseBill(q);
		if(null!=list && list.size()>0){
			for(PurchaseBill bill:list){
				bill.setPrice(q.getPrice());
				bill.setAmount(bill.getPrice()*bill.getQty());
			}
			this.purchaseBillDao.batchSaveOrUpdate(list);
			return list.size();
		}
		return 0;
	}
	
	/**
	 * 更新申请单表体单价
	 * @param q
	 * @return
	 */
	private int updateAppBillItem(Quotation q){
		List<AppBillItem> itemList = this.findAppBillItem(q);
		List<AppBillItem> newItemList = new ArrayList<AppBillItem>();
		List<AppBillHead> headList = new ArrayList<AppBillHead>();
		if(null!=itemList && itemList.size()>0){
			for(AppBillItem item:itemList){
				item.setPrice(q.getPrice());//改变单价
				item.setAmount(item.getTotalQty()*item.getPrice());//重新计算金额
				headList.add(item.getHead());
			}
			List<AppBillItem> list = this.appBillDao.saveAppBillItem(itemList);
			//更新申请单表头总金额
			for(AppBillHead head:headList){
				newItemList = this.appBillDao.findAppBillItemByHead(head);
				if(null!=newItemList && newItemList.size()>0){
					Double amount = 0d;
					for(AppBillItem item:newItemList){
						amount+=item.getAmount();
					}
					head.setTotalAmount(amount);
				}
			}
			this.appBillDao.batchSaveOrUpdate(headList);
			return itemList.size();
		}
		return 0;
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
	
	
}
