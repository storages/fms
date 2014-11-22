package com.fms.logic.impl;

import java.util.List;

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

}
