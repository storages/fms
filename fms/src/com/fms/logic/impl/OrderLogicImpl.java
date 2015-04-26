package com.fms.logic.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.OrderHead;
import com.fms.core.entity.OrderItem;
import com.fms.dao.OrderDao;
import com.fms.logic.OrderLogic;

public class OrderLogicImpl implements OrderLogic {

	protected OrderDao orderDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public List<OrderHead> findOrderPageList(AclUser aclUser, String orderNo, String scmCocName, String salesman, Date hBeginPlaceOrderDate, Date hEndPlaceOrderDate, Date hBeginDeliveryDate,
			Date hEndDeliveryDate, int index, int length) {
		return this.orderDao.findOrderPageList(orderNo, scmCocName, salesman, hBeginPlaceOrderDate, hEndPlaceOrderDate, hBeginDeliveryDate, hEndDeliveryDate, index, length);
	}

	public int findCount(String orderNo, String scmCocName, String salesman, Date hBeginPlaceOrderDate, Date hEndPlaceOrderDate, Date hBeginDeliveryDate, Date hEndDeliveryDate) {
		return this.orderDao.findCount(orderNo, scmCocName, salesman, hBeginPlaceOrderDate, hEndPlaceOrderDate, hBeginDeliveryDate, hEndDeliveryDate);
	}

	public OrderHead addOrder(AclUser aclUser) {
		OrderHead head = new OrderHead();
		Integer ser = this.orderDao.getSerialNo(aclUser);
		head.setCreateDate(new Date());
		head.setSerialNo(ser);
		String orderNo = "OR";
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddmmss");
		orderNo += df.format(new Date());
		head.setOrderNo(orderNo + ser);
		return (OrderHead) this.orderDao.saveOrUpdateNoCache(head);
	}

	public OrderHead load(Class clazz, String id) {
		return (OrderHead) this.orderDao.load(clazz, id);
	}

	public List<OrderHead> beatchSaveOrUpData(AclUser aclUser, List<OrderHead> orderList) {
		return this.orderDao.batchSaveOrUpdate(orderList);
	}

	public void delOrderHead(AclUser aclUser, String[] ids) {
		try {
			// 先删除对应的表体
			List<OrderItem> items = findOrderItemsByHeadId(ids, null, null, -1, -1);
			delOrderItem(aclUser, items);
			// 再删除表头
			this.orderDao.delOrderHead(aclUser, ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<OrderItem> findOrderItemsByHeadId(String[] hids, String hsCode, String hsName, int index, int length) {
		return this.orderDao.findOrderItemsByHeadId(hids, hsCode, hsName, index, length);
	}

	public void delOrderItem(AclUser aclUser, List<OrderItem> items) {
		this.orderDao.deleteAll(items);
	}

	/**
	 * 根据订单表头id查询对应的表体的记录数
	 * 
	 * @param hids
	 * @return
	 */
	public int countOrderItemsByHeadId(String[] hids, String hsCode, String hsName) {
		return this.orderDao.countOrderItemsByHeadId(hids, hsCode, hsName);
	}
}
