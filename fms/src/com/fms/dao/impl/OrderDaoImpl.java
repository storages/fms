package com.fms.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.fms.base.dao.BaseDaoImpl;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.OrderHead;
import com.fms.core.entity.OrderItem;
import com.fms.dao.OrderDao;
import com.fms.utils.FmsDateUtils;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {

	public List<OrderHead> findOrderPageList(String orderNo, String scmCocName, String salesman, Date hBeginPlaceOrderDate, Date hEndPlaceOrderDate, Date hBeginDeliveryDate, Date hEndDeliveryDate,
			int index, int length) {
		List params = new ArrayList();
		StringBuilder hql = new StringBuilder("SELECT a FROM OrderHead a LEFT JOIN FETCH  a.scmcoc b WHERE 1=1 ");
		if (StringUtils.isNotBlank(orderNo)) {
			hql.append("AND a.orderNo like ? ");
			params.add("%" + orderNo + "%");
		}
		if (StringUtils.isNotBlank(scmCocName)) {
			hql.append("AND b.name like ? ");
			params.add("%" + scmCocName + "%");
		}
		if (StringUtils.isNotBlank(salesman)) {
			hql.append("AND a.salesman like ? ");
			System.out.println("----" + salesman + "---");
			params.add("%" + salesman + "%");
		}
		if (null != hBeginPlaceOrderDate) {
			hql.append("AND a.placeOrderDate >= ? ");
			params.add(FmsDateUtils.getStartDate(hBeginPlaceOrderDate));
		}
		if (null != hEndPlaceOrderDate) {
			hql.append("AND a.placeOrderDate <= ? ");
			params.add(FmsDateUtils.getEndDate(hEndPlaceOrderDate));
		}
		if (null != hBeginDeliveryDate) {
			hql.append("AND a.deliveryDate >= ? ");
			params.add(FmsDateUtils.getStartDate(hBeginDeliveryDate));
		}
		if (null != hEndDeliveryDate) {
			hql.append("AND a.deliveryDate <= ? ");
			params.add(FmsDateUtils.getEndDate(hEndDeliveryDate));
		}
		return this.findPageList(hql.toString(), params.toArray(), index, length);
	}

	public int findCount(String orderNo, String scmCocName, String salesman, Date hBeginPlaceOrderDate, Date hEndPlaceOrderDate, Date hBeginDeliveryDate, Date hEndDeliveryDate) {
		List params = new ArrayList();
		StringBuilder hql = new StringBuilder("SELECT COUNT(a.id) FROM OrderHead a LEFT JOIN  a.scmcoc b WHERE 1=1 ");
		if (StringUtils.isNotBlank(orderNo)) {
			hql.append("AND a.orderNo like ? ");
			params.add("%" + orderNo + "%");
		}
		if (StringUtils.isNotBlank(scmCocName)) {
			hql.append("AND b.name like ? ");
			params.add("%" + scmCocName + "%");
		}
		if (StringUtils.isNotBlank(salesman)) {
			hql.append("AND a.salesman like ? ");
			params.add("%" + salesman + "%");
		}
		if (null != hBeginPlaceOrderDate) {
			hql.append("AND a.placeOrderDate >= ? ");
			params.add(FmsDateUtils.getStartDate(hBeginPlaceOrderDate));
		}
		if (null != hEndPlaceOrderDate) {
			hql.append("AND a.placeOrderDate <= ? ");
			params.add(FmsDateUtils.getEndDate(hEndPlaceOrderDate));
		}
		if (null != hBeginDeliveryDate) {
			hql.append("AND a.deliveryDate >= ? ");
			params.add(FmsDateUtils.getStartDate(hBeginDeliveryDate));
		}
		if (null != hEndDeliveryDate) {
			hql.append("AND a.deliveryDate <= ? ");
			params.add(FmsDateUtils.getEndDate(hEndDeliveryDate));
		}
		return this.count(hql.toString(), params.toArray());
	}

	public int getSerialNo(AclUser user) {
		String hql = "select MAX(a.serialNo) from OrderHead a";
		Integer serialNo = (Integer) this.uniqueResult(hql, null);
		return serialNo == null ? 1 : serialNo + 1;
	}

	public OrderHead load(Class clazz, String id) {
		return (OrderHead) this.uniqueResult("SELECT a FROM " + clazz.getSimpleName() + " a LEFT JOIN a.scmcoc b where a.id = ?", new Object[] { id });
	}

	public void delOrderHead(AclUser aclUser, String[] ids) {
		List params = new ArrayList();
		String hql = "DELETE FROM OrderHead a WHERE a.id =? ";
		params.add(ids[0]);
		if (ids != null && ids.length > 1) {
			for (int i = 1; i < ids.length; i++) {
				hql += " OR a.id=? ";
				params.add(ids[i]);
			}
		}
		this.batchUpdateOrDelete(hql, params.toArray());
	}

	public List<OrderItem> findOrderItemsByHeadId(String[] hids, String hsCode, String hsName, int index, int length) {
		List params = new ArrayList();
		String hql = "SELECT a FROM OrderItem a LEFT JOIN a.orderHead b WHERE (b.id =? ";
		params.add(hids[0]);
		if (hids != null && hids.length > 1) {
			for (int i = 1; i < hids.length; i++) {
				hql += " OR b.id=? ";
				params.add(hids[i]);
			}
		}
		hql += " )";
		if (StringUtils.isNotBlank(hsCode)) {
			hql += " AND a.hsCode =? ";
			params.add(hsCode);
		}
		if (StringUtils.isNotBlank(hsName)) {
			hql += " AND a.hsName =? ";
			params.add(hsName);
		}
		return this.findPageList(hql, params.toArray(), index, length);
	}

	public void delOrderItem(AclUser aclUser, String[] ids) {
		List params = new ArrayList();
		String hql = "DELETE FROM OrderItem a WHERE a.id =? ";
		params.add(ids[0]);
		if (ids != null && ids.length > 1) {
			for (int i = 1; i < ids.length; i++) {
				hql += " OR a.id=? ";
				params.add(ids[i]);
			}
		}
		this.batchUpdateOrDelete(hql, params.toArray());
	}

	public int countOrderItemsByHeadId(String[] hids, String hsCode, String hsName) {
		List params = new ArrayList();
		String hql = "SELECT COUNT(a.id) FROM OrderItem a LEFT JOIN a.orderHead b WHERE (b.id =? ";
		params.add(hids[0]);
		if (hids != null && hids.length > 1) {
			for (int i = 1; i < hids.length; i++) {
				hql += " OR b.id=? ";
				params.add(hids[i]);
			}
		}
		hql += " )";
		if (StringUtils.isNotBlank(hsCode)) {
			hql += " AND a.hsCode =? ";
			params.add(hsCode);
		}
		if (StringUtils.isNotBlank(hsName)) {
			hql += " AND a.hsName =? ";
			params.add(hsName);
		}
		return count(hql, params.toArray());
	}

}