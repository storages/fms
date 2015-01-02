package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.AclUser;
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
		String hql = "select a from AppBillItem a where a.head.id = ? ";
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
		String hql = "select a from AppBillHead a left join fetch a.submitUser u where 1=1 ";
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
	
	public List<AppBillItem> findItemByHid(String hid,Date beginappDate,Date endappDate,String appStatus,AclUser user){
		List list = new ArrayList();
		String hql="select item from AppBillItem item left join fetch item.scmcoc scm left join fetch item.material mat left join fetch mat.unit u where item.head.id = ? ";
		list.add(hid);
		if(null!=beginappDate && !"".equals(beginappDate)){
			hql+=" and item.appDate>=?";
			list.add(beginappDate);
		}
		if(null!=endappDate && !"".equals(endappDate)){
			hql+=" and item.appDate<=?";
			list.add(endappDate);
		}
		if(null!=appStatus && !"".equals(appStatus)){
			if(user.getUserFlag().equals("S")){
				hql+=" and (item.appStatus not is 0 or item.appStatus not is 3 )";
			}else{
			hql+=" and item.appStatus=?";
			}
			list.add(appStatus);
		}
		
		return this.find(hql, list.toArray());
	}

	public AppBillHead findHeadById(String hid) {
		String hql = "select a from AppBillHead a where a.id=?";
		return (AppBillHead) this.uniqueResult(hql, new Object[]{hid});
	}

	public AppBillItem findItemById(String id) {
		String hql="select item from AppBillItem item left join fetch item.scmcoc scm left join fetch item.material mat where item.id = ? ";
		return (AppBillItem) this.uniqueResult(hql, new Object[]{id});
	}
	
	public List<AppBillItem> findItemByIds(String[] ids) {
		List param = new ArrayList();
		String hql="select item from AppBillItem item left join fetch item.scmcoc scm left join fetch item.material mat where 1=1 ";
		hql+=" and (item.id = ? ";
		param.add(ids[0]);
		if(ids.length>1){
			for(int i =1 ;i<ids.length;i++){
				hql+=" or item.id = ? ";
				param.add(ids[i]);
			}
		}
		hql+=")";
		return this.find(hql, param.toArray());
	}

	public void delAppBillItem(String[] ids) {
		String hql = "DELETE FROM AppBillItem a WHERE a.id = ? ";
		List param = new ArrayList();
		param.add(ids[0]);
		for(int i = 1 ; i < ids.length ; i++){
			hql+=" or a.id = ? ";
			param.add(ids[i]);
		}
		this.batchUpdateOrDelete(hql, param.toArray());
	}

	public void deleteItemsByHeadId(String[] hid) {
		if(hid!=null&&hid.length>0){
		String hql = "DELETE FROM AppBillItem a WHERE a.head.id = ? ";
		List param = new ArrayList();
		param.add(hid[0]);
		for(int i = 1 ; i < hid.length ; i++){
			hql+=" or a.head.id = ? ";
			param.add(hid[i]);
		}
		this.batchUpdateOrDelete(hql,param.toArray());
		}
	}
	
	public void deleteAppBillHead(String [] ids){
		if(ids!=null&&ids.length>0){
			String hql = "DELETE FROM AppBillHead a WHERE a.id = ? ";
			List param = new ArrayList();
			param.add(ids[0]);
			for(int i = 1 ; i < ids.length ; i++){
				hql+=" or a.id = ? ";
				param.add(ids[i]);
			}
			this.batchUpdateOrDelete(hql,param.toArray());
		}
	}
	
	public List<AppBillItem> findAppBillItemByHeadIds(String [] ids){
		if(ids!=null&&ids.length>0){
			String hql = "SELECT item FROM AppBillItem item left join fetch item.head h left join fetch item.scmcoc scm left join fetch item.material mat left join fetch mat.unit WHERE h.id = ? ";
			List param = new ArrayList();
			param.add(ids[0]);
			for(int i = 1 ; i < ids.length ; i++){
				hql+=" or h.id = ? ";
				param.add(ids[i]);
			}
			return this.find(hql,param.toArray());
		}
		return null;
	}
	
	public List<AppBillHead> findAppBillHead(String [] ids){
		if(ids!=null&&ids.length>0){
			String hql = "SELECT a FROM AppBillHead a WHERE a.id = ? ";
			List param = new ArrayList();
			param.add(ids[0]);
			for(int i = 1 ; i < ids.length ; i++){
				hql+=" or a.id = ? ";
				param.add(ids[i]);
			}
			return this.find(hql,param.toArray());
		}
		return null;
	}
	
	public AppBillHead findHeadByItemId(String itemId){
		String hql = "select b from AppBillItem a left join a.head b where a.id=? ";
		return (AppBillHead) this.uniqueResult(hql, new Object[]{itemId});
	}
}
