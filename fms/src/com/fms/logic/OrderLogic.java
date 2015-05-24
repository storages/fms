package com.fms.logic;

import java.util.Date;
import java.util.List;

import com.fms.core.entity.AclUser;
import com.fms.core.entity.Material;
import com.fms.core.entity.OrderHead;
import com.fms.core.entity.OrderItem;
import com.fms.temp.TempOrder;

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

	/**
	 * 根据订单表头id查询对应的表体
	 * 
	 * @param hids
	 * @return
	 */
	public List<OrderItem> findOrderItemsByHeadId(String[] hids, String hsCode, String hsName, int index, int length);

	/**
	 * 删除订单表体
	 * 
	 * @param aclUser
	 * @param items
	 */
	public void delOrderItem(AclUser aclUser, List<OrderItem> items);

	/**
	 * 根据订单表头id查询对应的表体的记录数
	 * 
	 * @param hids
	 * @return
	 */
	public int countOrderItemsByHeadId(String[] hids, String hsCode, String hsName);

	/**
	 * 获取订单表体的流水号
	 * 
	 * @return
	 */
	public Integer getOrderItemSerialNo();

	/**
	 * 批量保存订单表体
	 * 
	 * @param aclUser
	 * @param items
	 * @return
	 */
	public List<OrderItem> beatchSaveOrUpDataItems(AclUser aclUser, List<OrderItem> items);

	/**
	 * 根据id查找订单表体
	 * 
	 * @param itemId
	 * @return
	 */
	public OrderItem findOrderItemById(String itemId);

	/**
	 * 根据多个id删除订单表体
	 * 
	 * @param aclUser
	 * @param ids
	 */
	public void delOrderItemByIds(AclUser aclUser, String[] ids);

	/**
	 * 验证 EXCEL导入的数据是否有效
	 * 
	 * @param tempOrders
	 * @return
	 */
	List<TempOrder> doValidata(List<TempOrder> tempOrders);

	/**
	 * 保存正确的Excel导入数据
	 * 
	 * @param aclUser
	 * @param data
	 * @return
	 */
	Boolean doSaveExcelData(AclUser aclUser, List<TempOrder> data);

	/**
	 * 按订单结案状态来查找
	 * 
	 * @param isFinish
	 * @return
	 */
	List<OrderHead> findOrderHeadByStauts(Boolean isFinish);

	/**
	 * 根据订单号，物料编码，物料名称查询订单表体物料信息
	 * 
	 * @param purchaseNo
	 * @param hsCode
	 * @param hsName
	 * @return
	 */
	public List<Material> findOrderItemMaterialByNo(String orderNo, String hsCode, String hsName);

	/**
	 * 根据Id查找订单表头
	 * 
	 * @return
	 */
	public OrderHead findOrderHeadById(String id);
}
