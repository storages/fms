package com.fms.dao;

import java.util.Date;
import java.util.List;

import com.fms.base.dao.BaseDao;
import com.fms.core.entity.AclUser;
import com.fms.core.entity.Material;
import com.fms.core.entity.OrderHead;
import com.fms.core.entity.OrderItem;

/**
 * 订单Dao
 * 
 * @author Administrator
 * 
 */
public interface OrderDao extends BaseDao {

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
	public List<OrderHead> findOrderPageList(String orderNo, String scmCocName, String salesman, Date hBeginPlaceOrderDate, Date hEndPlaceOrderDate, Date hBeginDeliveryDate, Date hEndDeliveryDate,
			int index, int length);

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
	 * 获取订单表头的流水号
	 * 
	 * @param user
	 * @return
	 */
	public int getSerialNo(AclUser user);

	/**
	 * 根据id查找订单表头
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	public OrderHead load(Class clazz, String id);

	/**
	 * 根据id删除订单表头
	 * 
	 * @param aclUser
	 * @param ids
	 */
	public void delOrderHead(AclUser aclUser, String[] ids);

	public List<OrderHead> findHeadByIds(String[] ids);

	/**
	 * 根据订单表头id查询对应的表体
	 * 
	 * @param hids
	 * @return
	 */
	public List<OrderItem> findOrderItemsByHeadId(String[] hids, String hsCode, String hsName, int index, int length);

	/**
	 * 根据订单表头id查询对应的表体的记录数
	 * 
	 * @param hids
	 * @return
	 */
	public int countOrderItemsByHeadId(String[] hids, String hsCode, String hsName);

	/**
	 * 根据id删除订单表体
	 * 
	 * @param aclUser
	 * @param items
	 */
	public void delOrderItem(AclUser aclUser, String[] ids);

	/**
	 * 获取订单表体的流水号
	 * 
	 * @return
	 */
	public Integer getOrderItemSerialNo();

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
	public void delOrderItemByIds(String[] ids);

	/**
	 * 按订单结案状态来查找
	 * 
	 * @param isFinish
	 * @return
	 */
	List<OrderHead> findOrderHeadByStauts(Boolean isFinish);

	/**
	 * 根据订单号查找成品
	 * 
	 * @param orderNo
	 * @return
	 */
	List<Material> findAllExgByOrderNo(String orderNo);

	/**
	 * 根据订单号，物料编码，物料名称查询订单表体
	 * 
	 * @param purchaseNo
	 * @param hsCode
	 * @param hsName
	 * @return
	 */
	public List<OrderItem> findOrderItemMaterialByNo(String orderNo, String hsCode, String hsName);
}
