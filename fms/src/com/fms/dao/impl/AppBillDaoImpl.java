package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
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


	public List<AppBillHead> betchSaveAppBillHead(List<AppBillHead> datas) {
		return this.batchSaveOrUpdate(datas);
	}

	public List<AppBillItem> betchSaveAppBillItem(List<AppBillItem> datas) {
		return batchSaveOrUpdate(datas);
	}

	public AppBillHead saveAppBillHead(AppBillHead head) {
		return (AppBillHead) this.saveOrUpdateNoCache(head);
	}

	public AppBillItem saveAppBillItem(AppBillItem item) {
		return (AppBillItem) this.saveOrUpdateNoCache(item);
	}

	public Integer findDataCount(String appNo, Date beginappDate,Date endappDate,String appStatus) {
		List params = new ArrayList();
		String hql = "select count(a) from AppBillHead a where 1=1 ";
		if(null!=appNo && !"".equals(appNo)){
			hql+=" and a.appNo like ? ";
			params.add(appNo.trim());
		}
		if(null!=beginappDate){
			hql+=" and a.appDate >=? ";
			params.add(beginappDate);
		}
		if(null!=endappDate){
			hql+=" and a.appDate <=? ";
			params.add(endappDate);
		}
		if(null!=appStatus && !"".equals(appStatus) && !"-1".equals(appStatus)){
			hql+=" and a.appStatus=? ";
			params.add(appStatus);
		}
		return this.count(hql, params.toArray());
	}

	public List<AppBillHead> findAppBillHeads(String appNo, Date beginappDate,Date endappDate,String appStatus, int index, int length) {
		List params = new ArrayList();
		String hql = "select a from AppBillHead a where 1=1 ";
		if(null!=appNo && !"".equals(appNo)){
			hql+=" and a.appNo like ? ";
			params.add(appNo.trim());
		}
		if(null!=beginappDate){
			hql+=" and a.appDate >=? ";
			params.add(beginappDate);
		}
		if(null!=endappDate){
			hql+=" and a.appDate <=? ";
			params.add(endappDate);
		}
		if(null!=appStatus && !"-1".equals(appStatus)){
			hql+=" and a.appStatus=? ";
			params.add(appStatus);
		}
		return this.findPageList(hql, params.toArray(), index, length);
	}
}
