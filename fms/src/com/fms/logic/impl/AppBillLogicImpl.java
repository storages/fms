package com.fms.logic.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fms.core.entity.AppBillHead;
import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.Quotation;
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
		head = this.appBillDao.saveAppBillHead(head);
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

	public List<AppBillItem> findItemByHid(String hid) {
		return this.appBillDao.findItemByHid(hid);
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
		return this.appBillDao.betchSaveAppBillItem(datas);
	}

}
