package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.AppBillHead;
import com.fms.core.entity.AppBillItem;
import com.fms.core.entity.Quotation;
import com.fms.dao.AppBillDao;

public class AppBillDaoImpl extends BaseDaoImpl implements AppBillDao{

	public List<AppBillItem> findAppBillItem(Quotation q){
		String hql = "select item from AppBillItem item left join fetch item.head h left join fetch item.material m left join fetch item.scmcoc scm " +
				" where h.appDate >= ? " +
				" and scm.name = ? " +
				" and m.hsCode = ? " +
				" and m.hsName = ? " +
				" and m.model = ? ";
		List list = new ArrayList();
		list.add(q.getEffectDate());
		list.add(q.getScmcoc().getName());
		list.add(q.getMaterial().getHsCode());
		list.add(q.getMaterial().getHsName());
		list.add(q.getMaterial().getModel());
		return this.find(hql,list.toArray());
	}

	public List<AppBillItem> findAppBillItemByHead(AppBillHead head){
		String hql = "select a from AppBillItem a where a.head = ? ";
		return this.find(hql,head.getId());
	}

	public List<AppBillItem> saveAppBillItem(List<AppBillItem> datas) {
		return this.batchSaveOrUpdate(datas);
	}
}
