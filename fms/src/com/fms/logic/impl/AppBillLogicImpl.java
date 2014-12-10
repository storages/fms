package com.fms.logic.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fms.commons.AppBillStatus;
import com.fms.core.entity.AppBillHead;
import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.Material;
import com.fms.core.entity.Quotation;
import com.fms.core.entity.Scmcoc;
import com.fms.dao.AppBillDao;
import com.fms.logic.AppBillLogic;

public class AppBillLogicImpl implements AppBillLogic {

	private AppBillDao appBillDao;

	public List<AppBillItem> findAppBillItem(Quotation q) {
		return appBillDao.findAppBillItem(q);
	}

	public AppBillDao getAppBillDao() {
		return appBillDao;
	}

	public void setAppBillDao(AppBillDao appBillDao) {
		this.appBillDao = appBillDao;
	}

	public AppBillHead saveAppBillHead(AppBillHead head) {
		Integer serialNo = this.appBillDao.getSerialNo("AppBillHead");
		head = this.appBillDao.findHeadById(head.getId());
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

	public List<AppBillItem> findItemByHid(String hid,Date beginappDate,Date endappDate,String appStatus) {
		return this.appBillDao.findItemByHid(hid,beginappDate,endappDate,appStatus);
	}

	public AppBillHead findHeadById(String hid) {
		return this.appBillDao.findHeadById(hid);
	}

	public AppBillItem findItemById(String id) {
		return this.appBillDao.findItemById(id);
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
			for(AppBillItem item:newList){
				num+=item.getTotalQty()==null?0d:item.getTotalQty();
				amount+=item.getAmount()==null?0d:item.getAmount();
			}
			head.setItemQty(newList.size());
			head.setTotalAmount(amount);
			head.setTotalQty(num);
			head.setApprovaledQty(this.countVerifyQty(newList));
			head.setUnApprovalQty(this.countUnVerifyQty(newList));
			
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
		List<AppBillItem> items = this.appBillDao.findAppBillItem(ids);
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
}
