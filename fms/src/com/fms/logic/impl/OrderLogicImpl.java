package com.fms.logic.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.fms.commons.ImgExgFlag;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.Employee;
import com.fms.core.entity.Material;
import com.fms.core.entity.OrderHead;
import com.fms.core.entity.OrderItem;
import com.fms.core.entity.Scmcoc;
import com.fms.dao.EmployeeDao;
import com.fms.dao.MaterialDao;
import com.fms.dao.OrderDao;
import com.fms.dao.ScmcocDao;
import com.fms.logic.OrderLogic;
import com.fms.temp.TempOrder;
import com.fms.utils.MathUtils;

public class OrderLogicImpl implements OrderLogic {

	protected OrderDao orderDao;
	protected ScmcocDao scmcocDao;
	protected MaterialDao materialDao;
	protected EmployeeDao employeeDao;

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public ScmcocDao getScmcocDao() {
		return scmcocDao;
	}

	public void setScmcocDao(ScmcocDao scmcocDao) {
		this.scmcocDao = scmcocDao;
	}

	public MaterialDao getMaterialDao() {
		return materialDao;
	}

	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
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
			orderDao.deleteAll(this.orderDao.findHeadByIds(ids));
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

	public Integer getOrderItemSerialNo() {
		Integer serialNo = this.orderDao.getOrderItemSerialNo();
		if (serialNo == null || serialNo == 0) {
			serialNo = 1;
		} else {
			serialNo += 1;
		}
		return serialNo;
	}

	public List<OrderItem> beatchSaveOrUpDataItems(AclUser aclUser, List<OrderItem> items) {
		items = this.orderDao.batchSaveOrUpdate(items);
		Set<String> set = new HashSet<String>();
		for (OrderItem item : items) {
			set.add(item.getOrderHead().getId());
		}
		String[] headIds = new String[set.size()];
		List<String> list = new ArrayList<String>(set);
		for (int i = 0; i < list.size(); i++) {
			headIds[i] = list.get(i);
		}
		items = this.findOrderItemsByHeadId(headIds, null, null, -1, -1);
		refreshOrderHead(items);
		return items;
	}

	/**
	 * 刷新订单表头
	 * 
	 * @param items
	 */
	private void refreshOrderHead(List<OrderItem> items) {
		OrderHead head = null;
		List<OrderHead> heads = new ArrayList<OrderHead>();
		if (!items.isEmpty()) {
			Map<String, OrderHead> headMap = new HashMap<String, OrderHead>();
			for (OrderItem item : items) {
				headMap.put(item.getOrderHead().getId(), item.getOrderHead());
			}

			for (Entry<String, OrderHead> entry : headMap.entrySet()) {
				Double totalAmount = 0d;
				Integer totalItems = 0;
				Double totalQty = 0d;
				items = this.orderDao.findOrderItemsByHead(entry.getValue());
				for (OrderItem item : items) {
					totalAmount += item.getAmount();
					totalItems += 1;
					totalQty += item.getQty();
				}
				entry.getValue().setTotalAmount(totalAmount);
				entry.getValue().setTotalQty(totalQty);
				entry.getValue().setItemQty(totalItems);
				heads.add(entry.getValue());
			}
			this.orderDao.batchSaveOrUpdate(heads);
			/*
			 * Double amount = 0d; Integer totalItems = 0; Double totalQty = 0d;
			 * Map<String, OrderHead> headMap = new HashMap<String,
			 * OrderHead>(); for (OrderItem item : items) { head =
			 * items.get(0).getOrderHead(); if (headMap.get(head.getId()) !=
			 * null) { amount = item.getAmount() == null ? 0d :
			 * MathUtils.add(item.getAmount(),
			 * headMap.get(head.getId()).getTotalAmount()); totalItems =
			 * MathUtils
			 * .add(headMap.get(item.getOrderHead().getId()).getItemQty(), 1);
			 * totalQty = item.getQty() == null ? 0d :
			 * MathUtils.add(item.getQty(),
			 * headMap.get(head.getId()).getTotalQty());
			 * headMap.get(head.getId()).setTotalAmount(amount);
			 * headMap.get(head.getId()).setTotalQty(totalQty);
			 * headMap.get(head.getId()).setItemQty(totalItems); } else {
			 * headMap.put(head.getId(), item.getOrderHead()); amount =
			 * item.getAmount() == null ? 0d : MathUtils.add(item.getAmount(),
			 * headMap.get(head.getId()).getTotalAmount()); totalItems =
			 * MathUtils
			 * .add(headMap.get(item.getOrderHead().getId()).getItemQty(), 1);
			 * totalQty = item.getQty() == null ? 0d :
			 * MathUtils.add(item.getQty(),
			 * headMap.get(head.getId()).getTotalQty());
			 * headMap.get(head.getId()).setTotalAmount(amount);
			 * headMap.get(head.getId()).setTotalQty(totalQty);
			 * headMap.get(head.getId()).setItemQty(totalItems); } }
			 */
		}
	}

	public OrderItem findOrderItemById(String itemId) {
		return this.orderDao.findOrderItemById(itemId);
	}

	public void delOrderItemByIds(AclUser aclUser, String[] ids) {
		this.orderDao.delOrderItemByIds(ids);
	}

	public List<TempOrder> doValidata(List<TempOrder> tempOrders) {
		Map<String, OrderHead> headMap = new HashMap<String, OrderHead>();
		Map<String, Scmcoc> cocMap = new HashMap<String, Scmcoc>();
		Map<String, Material> materialMap = new HashMap<String, Material>();
		Map<String, Employee> emplMap = new HashMap<String, Employee>();
		List<OrderHead> orderHeads = this.findOrderPageList(null, null, null, null, null, null, null, null, -1, -1);
		List<Scmcoc> scList = this.scmcocDao.findAllScmcoc(true, null, -1, -1);
		List<Material> materials = this.materialDao.findAllMaterialExgs(null, null, null, ImgExgFlag.EXG, -1, -1);
		List<Employee> employees = this.employeeDao.findAllEmpl(null, -1, -1);
		for (OrderHead head : orderHeads) {
			headMap.put(head.getOrderNo(), head);
		}
		for (Scmcoc scmcoc : scList) {
			cocMap.put(scmcoc.getName(), scmcoc);
		}
		for (Material material : materials) {
			materialMap.put(material.getHsCode(), material);
		}
		for (Employee employee : employees) {
			emplMap.put(employee.getName(), employee);
		}
		// 开始验证
		for (TempOrder tempOrder : tempOrders) {
			StringBuilder builder = new StringBuilder();
			if (StringUtils.isBlank(tempOrder.getOrderNo())) {
				builder.append("订单号码不能为空!");
			} else if (headMap.get(tempOrder.getOrderNo()) != null) {
				builder.append("订单号码在系统中已经存在!");
			}
			if (StringUtils.isBlank(tempOrder.getScmCocName())) {
				builder.append("客户全称不能为空!");
			} else if (cocMap.get(tempOrder.getScmCocName()) == null) {
				builder.append("客户全称在系统中不存在!");
			}
			if (StringUtils.isBlank(tempOrder.getHsCode())) {
				builder.append("物料编码不能为空!");
			} else if (materialMap.get(tempOrder.getHsCode()) == null) {
				builder.append("物料编码在系统中不存在!");
			}
			if (StringUtils.isNotBlank(tempOrder.getSalesman()) && emplMap.get(tempOrder.getSalesman()) == null) {
				builder.append("物料编码在系统中不存在!");
			}
			tempOrder.setErrorInfo(builder.toString());
		}
		return tempOrders;
	}

	public Boolean doSaveExcelData(AclUser aclUser, List<TempOrder> data) {
		for (TempOrder tb : data) {
			if (tb.getErrorInfo() != null && !"".equals(tb.getErrorInfo())) {
				return false;
			}
		}
		List<OrderItem> itemList = new ArrayList();
		if (null != data && data.size() > 0) {
			itemList = convertData(aclUser, data);
			Set set = new HashSet();
			for (OrderItem item : itemList) {
				set.add(item.getOrderHead());
			}
			this.beatchSaveOrUpData(aclUser, new ArrayList(set));
			this.beatchSaveOrUpDataItems(aclUser, itemList);
		}
		return true;
	}

	/**
	 * 将导入的数据转换成表头表体
	 * 
	 * @param data
	 * @return
	 */
	private List<OrderItem> convertData(AclUser aclUser, List<TempOrder> data) {
		Map<String, OrderHead> headMap = new HashMap<String, OrderHead>();
		List<OrderItem> items = new ArrayList<OrderItem>();
		Map<String, TempOrder> tempMap = new HashMap<String, TempOrder>();
		Map<String, Employee> emplMap = new HashMap<String, Employee>();
		Map<String, Scmcoc> cocMap = new HashMap<String, Scmcoc>();
		Map<String, Material> materialMap = new HashMap<String, Material>();
		List<Scmcoc> scList = this.scmcocDao.findAllScmcoc(true, null, -1, -1);
		List<Employee> employees = this.employeeDao.findAllEmpl(null, -1, -1);
		List<Material> materials = this.materialDao.findAllMaterialExgs(null, null, null, ImgExgFlag.EXG, -1, -1);
		for (Scmcoc scmcoc : scList) {
			cocMap.put(scmcoc.getName(), scmcoc);
		}
		for (Employee employee : employees) {
			emplMap.put(employee.getName(), employee);
		}
		for (Material material : materials) {
			materialMap.put(material.getHsCode(), material);
		}
		Integer serNo = this.orderDao.getSerialNo(aclUser) - 1;
		for (TempOrder temp : data) {
			OrderHead head = null;
			if (headMap.get(temp.getOrderNo()) == null) {
				serNo++;
				head = new OrderHead();
				head.setOrderNo(temp.getOrderNo());
				head.setScmcoc(cocMap.get(temp.getScmCocName()));
				head.setPlaceOrderDate(temp.getPlaceOrderDate());
				head.setDeliveryDate(temp.getDeliveryDate());
				Employee ele = emplMap.get(temp.getSalesman());
				head.setSalesman(ele == null ? null : ele.getName());
				head.setSerialNo(serNo);
				headMap.put(head.getOrderNo(), head);
			} else {
				head = headMap.get(temp.getOrderNo());
				head.setSerialNo(serNo);
			}
			OrderItem item = new OrderItem();
			item.setOrderHead(head);
			item.setSerialNo(this.getOrderItemSerialNo());
			item.setHsCode(temp.getHsCode());
			item.setHsName(materialMap.get(temp.getHsCode()).getHsName());
			item.setHsModel(materialMap.get(temp.getHsCode()).getModel());
			item.setUnit(materialMap.get(temp.getHsCode()).getUnit());
			item.setQty(temp.getQty());
			item.setPrice(temp.getPrice());
			item.setAmount(MathUtils.multiply(item.getQty(), item.getPrice()));
			item.setNote(temp.getNote());
			items.add(item);
		}
		return items;
	}

	public List<OrderHead> findOrderHeadByStauts(Boolean isFinish) {
		return this.orderDao.findOrderHeadByStauts(isFinish);
	}

	public List<Material> findOrderItemMaterialByNo(String orderNo, String hsCode, String hsName) {
		List<OrderItem> olist = this.orderDao.findOrderItemMaterialByNo(orderNo, hsCode, hsName);
		List<Material> mlist = new ArrayList<Material>();
		Set<String> set = new HashSet<String>();
		if (!olist.isEmpty()) {
			for (OrderItem item : olist) {
				set.add(item.getHsCode());
			}
		}
		if (!set.isEmpty()) {
			mlist = materialDao.finsMaterialByHsCode(set.toArray());
		}
		return mlist;
	}

	public OrderHead findOrderHeadById(String id) {
		return this.orderDao.load(OrderHead.class, id);
	}

}
