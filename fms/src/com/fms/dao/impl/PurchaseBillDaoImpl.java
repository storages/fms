package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.Material;
import com.fms.core.entity.PurchaseBill;
import com.fms.core.entity.PurchaseItem;
import com.fms.core.entity.Quotation;
import com.fms.dao.PurchaseBillDao;

public class PurchaseBillDaoImpl extends BaseDaoImpl implements PurchaseBillDao {

	public List<PurchaseBill> findPurchaseBill(Quotation q) {
		String hql = "select b from PurchaseItem a left join  a.purchaseBill b left join  a.material m left join  b.scmcoc scm " + " where a.purchaseDate >= ? " + " and scm.name = ? "
				+ " and m.hsCode = ? " + " and m.hsName = ? " + " and m.model = ? ";
		List list = new ArrayList();
		list.add(q.getEffectDate());
		list.add(q.getScmcoc().getName());
		list.add(q.getMaterial().getHsCode());
		list.add(q.getMaterial().getHsName());
		list.add(q.getMaterial().getModel());
		return this.find(hql, list.toArray());
	}

	public List<PurchaseBill> betchSavePurchaseBill(List<PurchaseBill> data) {
		return this.batchSaveOrUpdate(data);
	}

	public PurchaseBill saveOrUpdatePurchaseBill(PurchaseBill head) {
		return (PurchaseBill) this.saveOrUpdateNoCache(head);
	}

	public List<PurchaseItem> findItemById(String id, String hsCode) {
		List list = new ArrayList();
		String hql = "select a from PurchaseItem a left join fetch a.purchaseBill head left join fetch a.material mat left join fetch mat.unit u where head.id =? ";
		list.add(id);
		if (hsCode != null && !"".equals(hsCode)) {
			hql += "and mat.hsCode like ?   ";
			list.add("%" + hsCode + "%");
		}
		hql += " order by a.purchaseDate";
		return this.find(hql, list.toArray());
	}

	public List<PurchaseItem> findItemByHids(String[] ids) {
		if (null != ids && ids.length > 0) {
			List list = new ArrayList();
			StringBuilder builder = new StringBuilder();
			builder.append("select a from PurchaseItem a left join fetch a.purchaseBill head  where head.id =? ");
			list.add(ids[0]);
			for (int i = 1; i < ids.length; i++) {
				builder.append("or head.id =? ");
				list.add(ids[i]);
			}
			return this.find(builder.toString(), list.toArray());
		}
		return null;
	}

	public PurchaseBill findPurchaseBillById(String id) {
		return (PurchaseBill) this.uniqueResult("from PurchaseBill head left join fetch head.scmcoc scm where head.id=? ", new Object[] { id });
	}

	public List<PurchaseItem> findBillItemByAppNo(String[] appNos) {
		String hql = "select a from PurchaseItem a left join fetch a.purchaseBill h where h.appBillNo = ? ";
		List list = new ArrayList();
		list.add(appNos[0]);
		for (int i = 1; i < appNos.length; i++) {
			hql += " or h.appBillNo = ? ";
			list.add(appNos[i]);
		}
		return this.find(hql, list.toArray());
	}

	public List<PurchaseItem> findBillItemByAppBillItemIds(String[] appBillItemIds) {
		String hql = "select a from PurchaseItem a left join fetch a.purchaseBill h where a.linkAppBillItemId = ? ";
		List list = new ArrayList();
		list.add(appBillItemIds[0]);
		for (int i = 1; i < appBillItemIds.length; i++) {
			hql += " or h.appBillNo = ? ";
			list.add(appBillItemIds[i]);
		}
		return this.find(hql, list.toArray());
	}

	public void deletePurchaseItem(List<PurchaseItem> data) {
		this.deleteAll(data);
	}

	public void deletePurchaseHead(List<PurchaseBill> data) {
		this.deleteAll(data);
	}

	public List<PurchaseBill> getHeadByPurchaseItem(List<PurchaseItem> list) {
		String hql = "select distinct head from PurchaseItem a left join fetch a.purchaseBill head where a = ? ";
		List param = new ArrayList();
		param.add(list.get(0));
		for (int i = 1; i < list.size(); i++) {
			hql += " or a = ? ";
			param.add(list.get(i));
		}
		List<PurchaseBill> result = find(hql, param.toArray());
		return result;
	}

	public int getDataCount(String appBillNo, Date startDate, Date endDate) {
		List params = new ArrayList();
		String hql = "select count(a.id) from PurchaseBill a where 1=1 ";
		if (appBillNo != null && !"".equals(appBillNo.trim())) {
			hql += " and a.appBillNo like ? ";
			params.add("%" + appBillNo + "%");
		}
		if (startDate != null) {
			hql += " and a.createDate >=? ";
			params.add(startDate);
		}
		if (endDate != null) {
			hql += " and a.createDate <=? ";
			params.add(startDate);
		}
		return this.count(hql, params.toArray());
	}

	public List<PurchaseBill> findPurchaseHeads(String purchaseNo, Date startDate, Date endDate, int index, int length) {
		List params = new ArrayList();
		String hql = "from PurchaseBill a left join fetch a.scmcoc b where 1=1 ";
		if (purchaseNo != null && !"".equals(purchaseNo.trim())) {
			hql += " and a.purchaseNo like ? ";
			params.add("%" + purchaseNo + "%");
		}
		if (startDate != null) {
			hql += " and a.purchDate >=? ";
			params.add(startDate);
		}
		if (endDate != null) {
			hql += " and a.purchDate <=? ";
			params.add(startDate);
		}
		hql += " order by a.serialNo asc";
		return this.findPageList(hql, params.toArray(), index, length);
	}

	public PurchaseBill findPurchaseById(String id) {
		return (PurchaseBill) this.findUniqueResult("from PurchaseBill a left join fetch a.scmcoc b left join fetch b.settlement c where a.id =? ", new Object[] { id });
	}

	public List<PurchaseBill> findPurchaseBillByIds(String[] hid) {
		if (null != hid && hid.length > 0) {
			List list = new ArrayList();
			StringBuilder builder = new StringBuilder();
			builder.append("select a from PurchaseBill a  where a.id =? ");
			list.add(hid[0]);
			for (int i = 1; i < hid.length; i++) {
				builder.append("or a.id =? ");
				list.add(hid[i]);
			}
			return this.find(builder.toString(), list.toArray());
		}
		return null;
	}

	public PurchaseItem findPurchaseItemById(String id) {
		String hql = "select a from PurchaseItem a left join fetch a.purchaseBill b left join fetch a.material c left join fetch c.unit d where a.id=?";
		return (PurchaseItem) this.uniqueResult(hql, new Object[] { id });
	}

	public List<PurchaseItem> findItemsByHeads(String[] hid) {
		String hql = "select a from PurchaseItem a left join fetch a.purchaseBill b left join fetch a.material c left join fetch c.unit d left join fetch b.scmcoc e where 1=1 ";
		List params = new ArrayList();
		if (null != hid && hid.length > 0) {
			hql += " and (b.id =? ";
			params.add(hid[0]);
			for (int i = 1; i < hid.length; i++) {
				hql += " or b.id =? ";
				params.add(hid[i]);
			}
			hql += ")";
			return this.find(hql, params.toArray());
		}
		return null;
	}

	public List<PurchaseBill> findPurchaseBill(Boolean isComplete, String purchStatus) {
		String hql = "select a from PurchaseBill a left join fetch a.scmcoc b where a.isComplete=? and a.purchStatus=? ";
		return this.find(hql, new Object[] { isComplete, purchStatus });
	}

	public List<Material> findPurchaseItemMaterialByNo(String purchaseNo, String hsCode, String hsName) {
		List param = new ArrayList();
		String hql = "select mat from PurchaseItem item left join item.purchaseBill bill left join item.material mat left join bill.scmcoc scm left join fetch mat.unit u left join fetch mat.materialType t where bill.purchaseNo =? ";
		param.add(purchaseNo);
		if (StringUtils.isNotBlank(hsCode)) {
			hql += " and mat.hsCode like ? ";
			param.add("%" + hsCode + "%");
		}
		if (StringUtils.isNotBlank(hsName)) {
			hql += " and mat.hsName like ? ";
			param.add("%" + hsName + "%");
		}
		return this.find(hql, param.toArray());
	}
}
