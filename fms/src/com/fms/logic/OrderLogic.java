package com.fms.logic;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.OrderHead;

/**
 * 订单Logic
 * 
 * @author Administrator
 * 
 */
public interface OrderLogic {

	/**
	 * 获取订单列表
	 * 
	 * @param orderNo
	 *            订单号
	 * @param scmCocName
	 *            客户名称
	 * @param salesman
	 *            业务员
	 * @param hBeginPlaceOrderDate
	 *            下单日期(开始)
	 * @param hEndPlaceOrderDate
	 *            下单日期(结束)
	 * @param hBeginDeliveryDate
	 *            交货日期(开始)
	 * @param hEndDeliveryDate
	 *            交货日期(结束)
	 * @param index
	 *            第几页
	 * @param length
	 *            显示条数
	 * @return
	 */
	public List<OrderHead> findOrderPageList(AclUser aclUser, String orderNo, String scmCocName, String salesman, Date hBeginPlaceOrderDate, Date hEndPlaceOrderDate, Date hBeginDeliveryDate,
			Date hEndDeliveryDate, int index, int length);

	/**
	 * 获取订单列表记录数
	 * 
	 * @param orderNo
	 *            订单号
	 * @param scmCocName
	 *            客户名称
	 * @param salesman
	 *            业务员
	 * @param hBeginPlaceOrderDate
	 *            下单日期(开始)
	 * @param hEndPlaceOrderDate
	 *            下单日期(结束)
	 * @param hBeginDeliveryDate
	 *            交货日期(开始)
	 * @param hEndDeliveryDate
	 *            交货日期(结束)
	 * @return
	 */
	public int findCount(String orderNo, String scmCocName, String salesman, Date hBeginPlaceOrderDate, Date hEndPlaceOrderDate, Date hBeginDeliveryDate, Date hEndDeliveryDate);

	/**
	 * 新增订单表头
	 * 
	 * @return
	 */
	public OrderHead addOrder(AclUser aclUser);

	/**
	 * 根据id查找订单表头
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	public OrderHead load(Class clazz, String id);

	/**
	 * 批量保存订单表头
	 * 
	 * @param aclUser
	 * @param orderList
	 * @return
	 */
	public List<OrderHead> beatchSaveOrUpData(AclUser aclUser, List<OrderHead> orderList);

	/**
	 * 根据id删除订单表头
	 * 
	 * @param aclUser
	 * @param ids
	 */
	public void delOrderHead(AclUser aclUser, String[] ids);
}
