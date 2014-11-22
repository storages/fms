package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.PurchaseBill;
import com.fms.core.entity.Quotation;
import com.fms.dao.PurchaseBillDao;

public class PurchaseBillDaoImpl extends BaseDaoImpl implements PurchaseBillDao{

	public List<PurchaseBill> findPurchaseBill(Quotation q) {
		String hql = "select a from PurchaseBill a left join fetch a.material m left join fetch a.scmcoc scm " +
				" where a.purchaseDate >= ? " +
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

}
